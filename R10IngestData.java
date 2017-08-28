package com.pilotflyingj;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
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

public final class R10IngestData {
	static private SparkSession spark;
	static private String jdbcUrl;
	static private Properties loadProperties;
	static private boolean firstRun;
	
	  /**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		String applicationName;
		String propertiesFile;
		
		if (args.length != 2) {
			System.err.println("Usage: R10_Ingest_Data <application name> <properties file>");
			System.exit(1);
		}
		applicationName = args[0];
		propertiesFile = args[1];
		
		Logger.getLogger("org").setLevel(Level.OFF);
		Logger.getLogger("akka").setLevel(Level.OFF);

	    //StreamingExamples.setStreamingLogLevels();
	    spark = SparkSession
	    		  .builder()
	    		  .appName(applicationName)
	    		  //.master("local[4]")
	    		  .config("spark.hadoop.mapreduce.fileoutputcommitter.algorithm.version", "2")
	    		  .config("spark.speculation", "false")
	    		  //.config("spark.local.dir", "/tmp")
	    		  .config("spark.sql.parquet.writeLegacyFormat", "true")
	    		  .getOrCreate();
	    

	    // Get AWS Properties
	    Properties awsProperties = new Properties();
	    awsProperties.load(new FileInputStream(new File("aws_s3.properties")));
	    String myAccessKey = awsProperties.getProperty("AWSAccessKey");
	    String mySecretKey = awsProperties.getProperty("AWSSecretKey");
	    
	    SparkConf sparkConf = spark.sparkContext().getConf();
	    sparkConf.set("spark.hadoop.mapreduce.fileoutputcommitter.algorithm.version", "2");
	    sparkConf.set("spark.speculation", "false");


	    Configuration hadoopConf=spark.sparkContext().hadoopConfiguration();
	    ////hadoopConf.set("fs.s3.impl", "org.apache.hadoop.fs.s3native.NativeS3FileSystem");
	    //hadoopConf.set("fs.s3.impl", "com.amazon.ws.emr.hadoop.fs.EmrFileSystem");
	    hadoopConf.set("fs.s3a.awsAccessKeyId",myAccessKey);
	    hadoopConf.set("fs.s3a.awsSecretAccessKey",mySecretKey);
	     
	    //Get Load Properties
	    Properties loadProperties = new Properties();
	    loadProperties.load(new FileInputStream(new File(propertiesFile))); 
	    String source_table = loadProperties.getProperty("source_table");
	    String dest_table = loadProperties.getProperty("dest_table");
	    String s3_dest_table = loadProperties.getProperty("s3_dest_table");
	    String partitionColumn = loadProperties.getProperty("partitionColumn");
	    String numPartitions = loadProperties.getProperty("numPartitions");
	  
	    long no_of_records = Long.parseLong(loadProperties.getProperty("no_of_records"));
	    long lowerBound = Long.parseLong(loadProperties.getProperty("lowerBound"));
	    long upperBound = Long.parseLong(loadProperties.getProperty("upperBound"));
	    String firstRunMode = loadProperties.getProperty("firstRunMode"); // 'overwrite' - all old data overwritten, or 'append' - just add to existing data

	    System.out.println("Job Started at " + new Date());

	    String query = "";
	    
	    firstRun = true;
	    String mode = firstRunMode;
	    
	    for (long batch_no = 0; lowerBound + (batch_no * no_of_records) <= upperBound; batch_no++) {
	    	long minId = lowerBound + (batch_no * no_of_records);
	    	long maxId = lowerBound + (batch_no * no_of_records) + no_of_records;
	    	if (maxId > upperBound)
	    		maxId = upperBound;

	        if (firstRun) {
	        	firstRun = false;
	        } else {
	        	mode = "append";
	        }

	        query = "(SELECT /*+ no_parallel(a) */ * from " + source_table + " a where " + partitionColumn + " >= " + minId + " and " + partitionColumn + " < " + maxId + ")";
	        
	        System.out.println("");
			System.out.println("Batch: " + batch_no + " Started at " + new Date() + ", minId: " + minId + ", maxId: " + maxId + ", mode: " + mode);
			System.out.println("Number of records: " + (maxId - minId));
			loadProperties.setProperty("lowerBound", Long.toString(minId));
		        loadProperties.setProperty("upperBound", Long.toString(maxId));

		    Dataset<Row> DF = fetch_data(query, s3_dest_table, dest_table, mode);
		    
		    System.out.println("Batch: " + batch_no + " Completed at " + new Date() + ", minId: " + minId + ", maxId: " + maxId + ", mode: " + mode);

	    }
	    	  
		System.out.println("Job Completed at " + new Date());

	    spark.close();
	  }

	private static Dataset<Row>  fetch_data(String sourceTable, String S3Path, String destTable, String mode) {

		//System.out.println("Processing : " + sourceTable);
		//System.out.println("upperBound = " + loadProperties.getProperty("upperBound"));
		//System.out.println(Arrays.asList(loadProperties));
		
	    Dataset<Row> df = spark.read().jdbc(jdbcUrl, sourceTable, loadProperties);
	    //System.out.println("Finished JDBC Read");
	    //df.cache();
	    //System.out.println("Finished Cache");
	    System.out.println("DF count : " + df.count());

		if (firstRun) {
			df.printSchema();
			df.show(5, false);
		}
		
		if (!S3Path.equals("")) {
			System.out.println("Writing to S3 : " + "s3a://test/" + S3Path);
			df.write().mode(mode).format("parquet").save("s3a://test/" + S3Path);
			System.out.println("S3 write completed");
		}
		if (!destTable.equals("")) {
			System.out.println("Destination JDBCURL : " +  jdbcUrl);
			System.out.println(destTable + " " + new Date());    
			df.write().mode(mode).jdbc(jdbcUrl, destTable, loadProperties);
			System.out.println("destination write completed");
		}
		
		return df;
	}


	
}