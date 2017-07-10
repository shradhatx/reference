package com.digitalglobe.dataservice;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.text.SimpleDateFormat;

import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

import com.digitalglobe.util.JavaSparkSessionSingleton;

public final class DGAggregate {
	
	static private SparkSession spark;
	static private Properties dbProperties;
	static private String jdbcUrl;
	
	public static void main(String[] args) throws Exception {
		  
		  //Disables additional detailed log messages.
		  Logger.getLogger("org").setLevel(Level.OFF);
		  Logger.getLogger("akka").setLevel(Level.OFF);
		  Logger.getLogger("com").setLevel(Level.OFF);
	    
	    Properties consumerProperties = new Properties();
	    consumerProperties.load(new FileInputStream(new File("DGConsumer.properties")));

	    String s3_path = consumerProperties.getProperty("s3.path");
	    String s3_analytics_folder = consumerProperties.getProperty("s3_analytics_folder");
	    String s3_analytics_detail_folder = consumerProperties.getProperty("s3_analytics_detail_folder");
	    String s3_usage_detail_folder = consumerProperties.getProperty("s3_usage_detail_folder");
	    String s3_usage_aggregated_folder = consumerProperties.getProperty("s3_usage_aggregated_folder");
	    String start_date = consumerProperties.getProperty("start_date");
	    String end_date = consumerProperties.getProperty("end_date");
	   
	    System.out.println("Usage detail folder on S3: " + s3_path + s3_usage_detail_folder);
	    System.out.println("Aggregated usage folder on S3: " + s3_path + s3_usage_aggregated_folder);
	    // Get AWS Properties
	    Properties awsProperties = new Properties();
	    awsProperties.load(new FileInputStream(new File("aws_s3.properties")));
	    String myAccessKey = awsProperties.getProperty("AWSAccessKey");
	    String mySecretKey = awsProperties.getProperty("AWSSecretKey");
	    
	    spark = SparkSession
	    		  .builder()
	    		  .appName("DGAggregate")
	    		  //.master("local[4]")
	    		  .config("spark.hadoop.mapreduce.fileoutputcommitter.algorithm.version", "2")
	    		  .config("spark.speculation", "false")
	    		  .config("spark.local.dir", "/tmp")
	    		  .getOrCreate();
	    
	    SparkConf sparkConf = spark.sparkContext().getConf();
	    sparkConf.set("spark.hadoop.mapreduce.fileoutputcommitter.algorithm.version", "2");
	    sparkConf.set("spark.speculation", "false");
	    sparkConf.set("spark.sql.parquet.writeLegacyFormat", "true");
	    SQLContext sqlContext = spark.sqlContext();
	    
	    SparkSession spark = JavaSparkSessionSingleton.getInstance(sparkConf);

	    Configuration hadoopConf=spark.sparkContext().hadoopConfiguration();
	    hadoopConf.set("fs.s3.impl", "org.apache.hadoop.fs.s3native.NativeS3FileSystem");
	    
	    hadoopConf.set("fs.s3.awsAccessKeyId",myAccessKey);
	    hadoopConf.set("fs.s3.awsSecretAccessKey",mySecretKey);
	    
	    //Get PostgreSQL DB Properties
	    dbProperties = new Properties();
	    dbProperties.load(new FileInputStream(new File("db.properties")));
	    jdbcUrl = dbProperties.getProperty("jdbcUrl");

	    //Read ancillary files to join with analytics 
	    System.out.println("Job Started at " + new Date());
	    Dataset<Row> gsd_refDF = sqlContext.read().parquet(s3_path + "dwprod_rw/gsd_zoom_conversion.parquet");
	    //gsd_refDF.count();
	    gsd_refDF.createOrReplaceTempView("gsd_ref");   
	    gsd_refDF.select("min_gsd_nmbr","max_gsd_nmbr","zoom_level_nmbr").show(5);
	    
	    Dataset<Row> service_typeDF = sqlContext.read().parquet(s3_path + "dwprod_rw/d_service_type.parquet");
	    service_typeDF.select("service_type_id", "service_type_dsc","service_type_nm").show(10);
	    service_typeDF.createOrReplaceTempView("d_service_type");

	    Dataset<Row> member_user = spark.read().parquet(s3_path + "dgcs_comm_member_ds/member_user.parquet");
	    Dataset<Row> member_userDF = member_user.withColumnRenamed("id", "mu_id").withColumnRenamed("user_name", "mu_user_name")
	    		.withColumnRenamed("organization_id", "mu_organization_id");
	    		member_userDF.select("mu_id","mu_user_name", "mu_organization_id");
	    		member_userDF.createOrReplaceTempView("member_user");
	    		//member_userDF.printSchema();
	    
	    Dataset<Row> member_account = spark.read().parquet(s3_path + "dgcs_comm_member_ds/member_account.parquet");
	    Dataset<Row> member_accountDF = member_account.withColumnRenamed("id", "m_id").withColumnRenamed("organization_id", "m_organization_id");
	    member_accountDF.createOrReplaceTempView("member_account");
	    //member_accountDF.count();
	    member_accountDF.select("m_id", "account_name","connect_id", "m_organization_id").show(5);
	   
	    Dataset<Row> organizationDF = spark.read().parquet(s3_path + "dgcs_comm_member_ds/organization.parquet");
	    Dataset<Row> org1DF = organizationDF.withColumnRenamed("organization_name","org1_organization_name")
	    .withColumnRenamed("parent_organization_id","org1_parent_organization_id")
	    .withColumnRenamed("id","org1_id");
	    //org1DF.printSchema()
	    org1DF.select("org1_id","org1_organization_name","org1_parent_organization_id");
	    org1DF.createOrReplaceTempView("org1");
	    
	    
	    System.out.println("Completed reading ancillary files " + new Date());
	    Dataset<Row> gcs_analytics = sqlContext.read().parquet(s3_path + s3_analytics_folder );
	    
	    
	    //Dataset<Row> batch_analytics = gcs_analytics.select("*").filter("request_received_date >= '2017-05-03 00:00:00' and request_received_date < '2017-05-10 00:00:00'");
	    /****************/
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    Date startDate = formatter.parse(start_date);
	    Date endDate = formatter.parse(end_date);

	    LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	    for (LocalDate process_date = start; !process_date.isAfter(end); process_date = process_date.plusDays(1)) {
	        
	        System.out.println("Processing date: " + process_date);
	        System.out.println("Started at " + new Date());
	        String batch_start_date = process_date.toString() + " 00:00:00";
	        String batch_end_date = process_date.plusDays(1).toString() + " 00:00:00";
	        String strfilter = "request_received_date >= '"+ batch_start_date + "' and request_received_date < '" + batch_end_date + "'";
	        System.out.println("Batch start date: " + batch_start_date);
	        System.out.println("Batch end date: " + batch_end_date);
	        System.out.println("strfilter: " + strfilter);
	        
	        //Dataset<Row> batch_analytics = gcs_analytics.filter($("request_received_date") > string_process_date);
	        //Dataset<Row> batch_analytics = gcs_analytics.filter("request_received_date.equals(string_process_date)");
	    	//Dataset<Row> batch_analytics = gcs_analytics.select("*").filter("request_received_date.equals(string_process_date)");
	    	
	    /**********/
	    
	    	Dataset<Row> gcs_analytics_detail = sqlContext.read().parquet(s3_path + s3_analytics_detail_folder);
	    	//gcs_analytics_detail.printSchema();
	    	Dataset<Row> analytics_detailDF = gcs_analytics_detail.withColumnRenamed("id", "ad_id").withColumnRenamed("load_date", "ad_load_date").withColumnRenamed("service_type", "ad_service_type");
	    	analytics_detailDF.select("ad_service_type", "area_sqkm","pixel_count", "contract_id", "image_size", "data_layer","feature_id").show(5);
	    
	    	//Dataset<Row> batch_analytics = gcs_analytics.select("*")
	    	//		.filter("request_received_date >= '2017-05-04 00:00:00' and request_received_date < '2017-05-05 00:00:00'");
	    	
	    	Dataset<Row> batch_analytics = gcs_analytics.select("*")
	    			.filter(strfilter);
	    	   //batch_analytics.count();
		    Dataset<Row> batch_analyticsDF = batch_analytics.withColumnRenamed("id", "a_id").withColumnRenamed("load_date", "a_load_date")
		    		.withColumnRenamed("service_type", "a_service_type").withColumnRenamed("user_name", "a_user_name");

	    	Dataset<Row> analyticsCombinedDF = batch_analyticsDF.join(analytics_detailDF, batch_analyticsDF.col("a_id")
	    		.equalTo(analytics_detailDF.col("analytics_id")), "left_outer");

	    	analyticsCombinedDF.createOrReplaceTempView("analyticsCombinedDF");
	    	//analyticsCombinedDF.printSchema();
	    
	    	String sql1 = "select "
  			  + "cast(regexp_replace(substring(request_received_date, 0, 10), '-', '') as int) as request_date_id, "
  			  + "request_received_date, "
  			  + "created_date, "
  			  + "create_dttm, "
  			  + "analytics_id, "
  			  + "a_id, "
  			  + "contract_id, "
  			  + "account_name, "
  			  + "a_user_name, "
  			  + "mu_user_name, "
  			  + "cast(zoom_level_nmbr as int), "
  			  + "a_service_type, "
  			  + "service_type_dsc, service_type_nm, " 
  			  + "cast(org1_id as int), "
  			  + "cast(org1_parent_organization_id as int), "
  			  + "area_sqkm, "
  			  + "pixel_count, "
  			  + "image_size, "
  			  + "data_layer, "
  			  + "response_code, "
  			  + "result_code, "
  			  + "centroid_lat, "
   			  + "centroid_long, "
			  + "centroid, "
			  + "centroid_text, "
			  + "request_bbox "
			  + "from analyticsCombinedDF "
			  + "left join gsd_ref on round(cast(request_gsd as decimal(38,16)), 9) between min_gsd_nmbr and max_gsd_nmbr " 
			  + "left join d_service_type on trim(a_service_type) = trim(service_type_dsc) "
			  + "left join member_account on trim(member_guid) = trim(connect_id) "
			  + "left join member_user  on trim(a_user_name) = trim(mu_user_name) "
			  + "left join org1 on cast(mu_organization_id as int) = cast(org1_id as int) "  
              ;  
	    	Dataset<Row> sql1_df = sqlContext.sql(sql1); 
	    
	    	sql1_df.createOrReplaceTempView("analytics1"); 
	    	//sql1_df.printSchema()
	    	sql1_df.show(5);

	    	sqlContext.setConf("spark.sql.parquet.writeLegacyFormat", "true");
	    	System.out.println("Writing usage detail to S3 " + s3_path + s3_usage_detail_folder + " " + new Date());
	    	sql1_df.write().mode("append").parquet(s3_path + s3_usage_detail_folder);
	    
	    	Dataset<Row> aggregatedDF = sql1_df.groupBy("request_date_id", "org1_id","service_type_nm","account_name","a_user_name")
    			.agg(
    					functions.sum("area_sqkm").as("sum_area_sqkm"), 
    					functions.sum("image_size").as("sum_image_size"), 
    					functions.sum("pixel_count").as("sum_pixel_count"),
    					functions.count("a_id").as("analytics_detail_count")
    					);
	    	aggregatedDF.show(5);
	    	aggregatedDF.count();
	    
	    	//Write aggregated data set to s3  
	    	System.out.println("Writing aggregated usage to S3 " + s3_path + s3_usage_aggregated_folder + " " + new Date());
	    	sqlContext.setConf("spark.sql.parquet.writeLegacyFormat", "true");
	    	aggregatedDF.write().mode("append").parquet(s3_path + s3_usage_aggregated_folder);
	    	System.out.println("Aggregations written to S3 " + new Date());
	    	

	    } 
	    
	    
	    //Write aggregated data set to postgres
	    //aggregatedDF = columnnames_tolower(aggregatedDF);
	    
	    //System.out.println("Postgres JDBCURL : " +  jdbcUrl);
	    //aggregatedDF.write().mode("append").jdbc(jdbcUrl, "aggregatedUsage", dbProperties);
		//System.out.println("JDBC write completed");
	    
	    System.out.println("Job Completed at " + new Date());
	    spark.close();
	    
}
    
}
