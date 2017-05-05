#### MUST READ http://spark.apache.org/docs/latest/programming-guide.html

#### Spark: data bricks webinar about spark and spark on cloud

https://www.brighttalk.com/webcast/12891/230091?utm_campaign=Webinar&utm_medium=email&_hsenc=p2ANqtz-8T1sjntbZP3y1M5OXBYtquQaMAz-nIr6zUBZwrNG8FbtktIIV_eR5xR3cPCTcDtmpJEMCUkh9zye7Lb2brMuYTU2D64Q&_hsmi=37339817&utm_content=37339817&utm_source=hs_email&hsCtaTracking=57810cfb-9866-47e2-b753-aa768de50789%7C7994147a-a241-4045-aa5f-f228c548bcca

#### My job responsibilities:
At Ignited2k- Predictive analytics and lead generation by integrating consumer data

Architect and design a workable solution for problem at hand
workflow
data flow
Participated in backend data architecture and APIs

Ingest data from different sources and land it on kafka.  Another job to filter, transform & enrich data

Data pipeline:
Enrich data- initial filters, append metadata
topic (LDA)
geo location (location from Ip)
> analysis using Spark  ML
  check: https://devpost.com/software/spark-mllib-twitter-sentiment-analysis
  twitter OAuth for retrieving tweets using Twitter Streaming API

>Sentiment using python
  tokenize
  sentiment value of each word(ngram) and lexicon model to get the sentiment of whole sentence
  pip install textblob (textblob gives polarity of text)
  import tweepy
  check youtube (twitter sentiment analysis by Siraj Raval)

> Sentiment analysis - alternative approach
  ML Naive Bayes for classification: cons: makes assumptions that features are independent
; probability as 0 and 1 and not discreet or continuous. Pros: NB offers simplicity as a viable cheap baseline.

  Training data using 1.6 million tweet training data available by Sentiment140
  Compared with Stanford CoreNLP sentiment prediction
  Sentiments: Positive, Negative, Neutral
  Only english tweets
  Only tweets with location, other discarded because of business use case

  Result of tweets is published to Redis which is subscribed by front-end webapp for viz
  D3.js - datamaps used for viz, hover over to see additional details on tweets
  )


load data to ES and Hbase

Build consumer database -
call REST calls (Ex: full contact to attach social handle to person)
demography information


#### MR
MR has been the work horse of Hadoop since inception. MR-batch oriented etl. Resource mgr yarn and impala use non MR architecture with SQL capability.
Spark is another choice for general purpose computation. MR implemented in this is faster and easy to maintain.  RDD and Data Frames(schema aware). Single pass computation.

i) code to map line length to number of lines with the length in a file- lines.map(line => (line.length, 1))
val lengthCounts = lines.map(line => (line.length, 1)).reduceByKey(_ + _)

ii) get the count of words that begin with uppercase- lines.flatMap(
_.split(" ").filter(word => Character.isUpperCase(word(0))).map(word => (word,1)) )

iii) to get the output of count of word in all uppercase
... .groupByKey().map { case (word,ones) => (word.toUpperCase, ones.sum) }
or better still
... .reduceByKey(_ + _).map { case (word,total) => (word.toUpperCase,total) }
Stinger - performant hive

i) use apache tez engine instead of MR: put the following lines before every query [code language=”SQL”]
set hive.execution.engine=tez;
[/code]
ii) use ORC tables instead of text files. ORC supports compressed storage
[code language=”SQL”]
CREATE TABLE A_ORC (
customerID int, name string, age int, address string
) STORED AS ORC tblproperties (“orc.compress" = “SNAPPY”);
INSERT INTO TABLE A_ORC SELECT * FROM A;
ii) use vectorization: improves aggregation, joins, filter by performing in batches of 1024 rows
[code language=”SQL”]
set hive.vectorized.execution.enabled = true;
set hive.vectorized.execution.reduce.enabled = true;
[/code]

iv) cost based optimization (rather than physical and logical): takes into account degree of parallelism, order of join
[code language=”SQL”]
set hive.cbo.enable=true;
set hive.compute.query.using.stats=true;
set hive.stats.fetch.column.stats=true; set hive.stats.fetch.partition.stats=true; [/code]
[code language=”SQL”]
analyze table tweets compute statistics for columns;
[/code]
v) write better sql:
[code language=”SQL”]
SELECT * FROM
(SELECT *, RANK() over (partition by sessionID, order by timestamp desc) as rank
FROM clicks) ranked_clicks
WHERE ranked_clicks.rank=1;
[/code]
the above uses Hive’s OLAP functionality over and rank. The query is much faster than -
[code language=”SQL”]
SELECT clicks.* FROM clicks inner join
(select sessionID, max(timestamp) as max_ts from clicks group by sessionID) latest
ON clicks.sessionID = latest.sessionID and clicks.timestamp = latest.max_ts;
[/code]
Table: [code language=”SQL”]
CREATE TABLE clicks (
timestamp date, sessionID string, url string, source_ip string
) STORED as ORC tblproperties (“orc.compress” = “SNAPPY”); [/code]
MR Vs Hive Vs Pig
MR: compiled language - more control. make use of application programming interface Pig Latin: scripting language - from yahoo - procedural code not declarative. Can be extended using UDF in python, java
Hive: from fb: sql like

## Hbase (cloudera CDH5)/Phoenix/Mongodb/Elasticsearch
Hadoop: Relationship between split size and block size
In HDFS architecture there is a concept of blocks. A typical block size used by HDFS is 64 MB. When we place a large file into HDFS it chopped up into 64 MB chunks(based on default configuration of blocks), Suppose you have a file of 1GB and you want to place that file in HDFS,then there will be 1GB/64MB = 16 blocks and these block will be distribute across the DataNodes. These blocks/chunk will reside on a different DataNode based on your cluster configuration.
Difference between block size and split size.
Split is logical split of your data, basically used during data processing using Map/ Reduce program or other processing techniques. Split size is user defined and you can choose your split size based on your data(How much data you are processing).
Split is basically used to control number of Mappers in Map/Reduce program. If you have not defined any input split size in Map/Reduce program then default HDFS block split will be considered as input split.
Example:
Suppose you have a file of 100MB and HDFS default block configuration is 64MB then it will chopped in 2 and occupy 2 blocks. Now you have a Map/Reduce program to process this data but you have not specified any input split then based on the number of blocks(2 block) input split will be considered for the Map/Reduce processing and 2 mapper will get assigned for this job.
But suppose,you have specified the split size(say 100MB) in your Map/Reduce program then both blocks(2 block) will be considered as a single split for the Map/ Reduce processing and 1 Mapper will get assigned for this job.
Suppose,you have specified the split size(say 25MB) in your Map/Reduce program then there will be 4 input split for the Map/Reduce program and 4 Mapper will get assigned for the job.

## Spark SQL - Pros: fast in memory processing;
Cons:Not suitable for long running jobs over gigantic amount of data.
Hive on top of hadoop cluster - good for batch processing and OLAP type use cases

1. JSON (Java script object notation) advantage over xml:
json: lightweight, easy to handle with java script, more flexible- can contain string, int, list, arrays. Better data exchange format.
xml -nodes and elements that needs to be parsed to int, string etc.
2. Kafka - more than queue - it is replicated distributed commit log on disk. messages by topic. multi pub-sub . Messages stay for a while after they are consumed based on configuration - by time, compaction 

== start spark shell
cd spark*
./bin/spark-shell —master local[2]
scala>sc.version
check http://localhost:4040

##### run program on AWS
//spark application starts on 2 node cluster
$./bin/spark-shell —master spark://ec2….amazonaws.com:7077
./bin/spark-submit —master spark://host:7077 —executor-memory 10g my_script.py

//possible values for master flag
mesos://host:5050      //meso is general purpose cluster manager
yarn
local
local[*]
local[N]    //N cores
./bin/spark-submit —name “MyApp” —master local[2]  myApp.jar
./bin/spark-submit --properties-file test.conf --class ReadSample target/scala-2.10/readsample-project_2.10-1.0.jar
== to stop
./sbin/stop-all.sh 
== to start master and worker
cd ~/spark*
./sbin/start-master.sh

//check urls at url-> localhost:8080
// master is at spark://Shradhas-MacBook-Pro.local:7077
//start a worker
// ./bin/spark-class org.apache.spark.deploy.worker.Worker <spark://IP:PORT>
./bin/spark-class org.apache.spark.deploy.worker.Worker spark://Shradhas-MacBook-Pro.local:7077
//check url > localhost:8080/
 
===========  tested
cd spark*
./bin/spark-shell 
scala>sc.version
scala>sc.setLogLevel("INFO")
1.
val sqlContext = new org.apache.spark.sql.SQLContext(sc)
val df = sqlContext.jsonFile(“./examples/src/main/resources/people.json”)
df.show()
df.printSchema()
df.select(“name”).show()
df.select(“name”, df.age + 1).show()   //error since age is blank for some

val df1 = sqlContext.read.json("./examples/src/main/resources/people.json”)
 

2.
>val lines = sc.textFile("README.md”)
>val lineLengths = lines.map(s => s.length)
>val totalLength = lineLengths.reduce((a, b) => a + b)
3264
> val words = lines.flatMap(_.split(“ “))
>val pairs = words.map(s => (s,1))
>val counts = pairs.reduceByKey(_ + _) 
>counts.print()  //error
 
=== test: read option
sqlContext.read.format(“com.databricks.spark.jira”).option(“url”,”https://…”)
.option(“user”,”sbhalla”)
.option(“query”, “””
|project = …
.load()
 

	1.	//read from hfs

val lines = sc.textFile(“hfs://….”)
val errors= lines.filter(_.startswith(“ERROR”))
val messages = errors.map(_.split(‘\t’)(2))
messages.cache()
messages.filter(_.contains(“foo”)).count

=== test: write to hive

df.write.format(“parquet”).mode(“append”).partitionBy(“year).saveAsTable(“fasterData”)

== test: read from hive
sqlCtx - new HiveContext(sc)
results = sqlCtx.sql(“SELECT * FROM people”)
names = results.map(lambda p: p.name)
Note: cannot delete row(s) from Hive Table since it is not RDBMS. Hadoop is for batch processing.

=== test: connect tableau to DF


=== streaming 
ssc = new StreamingContext


ssc.checkpoint

ssc.start()

=== udf
// creating UDF is very easy
def function(x)
   #some magic code
// Register the function with return data type by -
sqlContext.registerFunction(“name”, function, IntegerType())


=== check
url -> host:7080
master at < ..>:7077


====
use only 75% of machine memory for spark; minimum executor heap size should be 8GB; Max executor heap size depends…maybe 40GB (watch Garbage Collection);
Mem usage affected by storage  and serialization format.

Persistence: memory_only, memory_and_disk, memory_only_ser, disk_only, off_heap (tachyon)
Driver Program (ex: spark-shell) —> worker machines(many)
RDD distributed over worker machines
RDD can be created either by parallelizing a collection or reading data from S3, Cassandra, HDFS

Default mem allocation in jvm: cached RDD(60%), shuffle mem(20%), user program(20%)

cache refernce (0.5 ns) Vs disk seek (10,000,000 ns)

Read 1MB from memory: 250,000 ns
Read 1MB from network: 10,000,000 ns (1Gbps network) 
Read 1MB from disk: 30,000,000 ns

Spark Pro: M-R is a one pass computation and avoids multiple i/o
cons: no efficient primitives for data sharing

=== Architecture Hadoop
NameNode (NN)
JobTracker (JT)

DataNode
TaskTracker

=== Architecture Spark
Choose cluster manager: stand alone; one comes with datastax; yarn if using hadoop; mesos

Client or Driver Program --> Master Node (Yarn- Resource Manager; hdfs) —assigns —>> Worker Node (spins Executor) —communicates with—>> Driver Program

Deploy spark - Local/Yarn/mesos
==========================

// parallelize
val wordsRDD = sc.parallelize(List(“fish”, “cats”, “dogs”))

// reading from local file
val linesDDD = sc.textFile(“/path/to.README.md”)

.filter(f(x))  or .map()
.coalesce(2)  and .saveToCassandra() or .cache()
.collect()  or .count()


DataFrame: distributed collection of data organized into named columns


API in python or java

from pyspark.sql import SQLContext
sqlContext = SQLContext(sc)

Two methods for converting existing RDDs into data frames

	1.	Use reflection   //more concise
	2.	Use program to construct a schema and then apply it to existing RDD  //more verbose

Using reflection:
from pyspark.sql import SQLContext, Row
sqlContext = SQLContext(sc)
lines = sc.textFile(“examples/fn.txt”)
parts = lines.map(lambda l: l.split(“,”))
# convert each line parts to a Row
people = parts.map(lambda p: Row(name=p[0], age=int(p[1])))

# infer the schema, and register the DataFrame as a table
schemaPeople = sqlContext.inferSchema(people)
schemaPeople.registerTempTable(“people”)

# SQL can be run on table
teenagers = sqlContext.sql(“SELECT name FROM people WHERE age >=13 AND age <= 19”)

 # results of SQL queries are RDDs
teenNames = teenagers.map(lambda p: “Name: “ + p.name)
for teenName in teenNames.collect():
     print teenName


Create DataFrame Programmatically in 3 steps
i) create an RDD of tuples or lists from original RDD
ii) create the schema represented by a StructType
iii) Apply the schema to RDD via createDataFrame method of SQLContext

# construct from Hive
users = sc.table(“users”)
young = users.filter(users.age < 21)
young.groupBy(“gender”).count()

#  from json file in S3
logs = sc.load(“s3n://path/to/data.json”, “json”)

# Join
young.join(logs, logs.userId == users.userId, “left_outer”)


==
sc.textFile(“blog.txt”)
.cache()
.flatMap { line => line.split(“ “) }
.map { word => (word, 1) }
.reduceByKey { case (count1, count2) => count1 + count2 }
.collect()


==== Broadcast & accumulators
val broadcastV = sc.broadcast(Array(1,2,3))
broadcastV.value

val accum = sc.acculmulator(0)
sc.parallelize(Array(1,2,3,4)).foreach(x => accum += x)
accum.value


======= Spark Streaming
TwitterUtils.createStream(…)
.filter)_.getText.contains(“Spark”))
.countByWindow(Seconds(5))

Processing latency as low as 1 sec
Exactly once semantics no matter what fails

from pyspark import SparkContext
from pyspark.streaming import SteamingContext
sc.SparkContext(“local[2]”, “NetworkWordCount”)
ssc = StreamingContext(sc,5)
linesDStream = ssc.socketTextStream(“localhost”,9999)
wordsDStream = linesDStream.flatMap(lambda line: line.split(“ “)).
pairDStream = wordsDStream.map(lambda word: (word,1))
wordCountDStream = pairsDStream.reduceByKey(lambda x, y: x+y)
wordCountsDStream.print()

ssc.start
ssc.awaitTermination()


//try - send text on port 9999 on terminal window 1
$nc - lk 9999
hello hello world

//try - on terminal 2
$./network_wordcount.py localhost 9999
(hello, 2)
(world, 1)

=== Connect to kafka

//put the link in SBT or maven proc definition

//program
import org.apache.spark.streaming.kafka._
val kafkastream = kafkautils.createStream


* Spark 
  RDD (resilient distributed datasets have actions and transformation)
  > Transformations:
  . map(returns a new RDD by running a function on each element) and flatMap applies function to each element and the output is flattened (each input item can be mapped to 0 or more output items)
  . coalesce(numPartitions): Decrease the number of partitions in the RDD to numPartitions. 
      Useful for running operations more efficiently after filtering down a large dataset.
  . join returns a dataset of (K, (V, W)) pairs 
  . cogroup returns a dataset of (K, (Iterable<V>, Iterable<W>)) tuples.
  . repartition(numPartitions): Reshuffle the data in the RDD randomly to create either more or fewer partitions
  > Actions:
  . reduce(func): Aggregate the elements of the dataset using a function func (which takes two arguments and returns one). 
  . collect(): Return all the elements of the dataset as an array at the driver program. 
  > closure
  . shuffle: redistributes data - a costly opertion coz it involves disk i/o, data serialization, network i/o, heap memory intensive  - certain spark operations can trigger shuffle Ex: groupByKey, reduceByKey, cogroup and join
  . cache: persist RDD in memory, cache is fault tolerant. Drops data in LRU fashion.

  > broadcast variables: read only shared variables. SparkContext.broadcast(v)
    Ex: val broadcastVar = sc.broadcast(Array(1, 2, 3))
        broadcastVar.value

  > accumulators: like counters. Tasks running on a cluster can then add to it using the add method. However, they cannot read its value. Only the driver program can read the accumulator’s value, using its value method.
  spark-submit to deploy to cluster

MlLib: classification, regression, clustering, and collaborative filtering(for recommendations)
     Collaborative filtering: user-item association matrix. Uses ALS(alternating Least Squares) alogorithm.
     The model tries to find latent factors(implicit or explicit)to predict the expected preference of a user for an item.

AWS cert notes- Adv:low capex, opex, scalability, agility, resiliency-DR, durability by replication-robust

IoT: Time series: sequence of observation ordered by time. 2parts- systematic (function of time) & stochastic (noise or variation or fluctuations - seasonal )
Ontology- Data characteristic and catalog- interrelationship between entities in a domain. sharing info in common vocabulary
 
Questions:
1. Why are you looking to leave your current role (OR Why did you leave this position?)
I am working as an ELK consultant and my role is primarily focused on providing prototypes that are mainly to do with solving the sticky business issue utilizing Elastic capabilities.  
Although I like what I am doing and gives me the opportunity to expand my depth of knowledge in Lucene and search technologies I like to have an insight into business problem and end to end solution.
I like to keep relevant on other areas.
Also, my family is in Allen and I like to find work in Dallas area.

2. What Job responsibilities and duties do you excel at?
Technology, Solution Architecture and dont mind rolling up my sleeves and solving problem myself.

I read the job responsibilities and it absolutely resonated with what excites me everyday.
Bias towards action. I believe that speed and quality aren’t mutually exclusive. 
Design and business vision. I help my team understand requirements beyond the written word. Even in the absence of a PM or a designer, I show great attention to the design and product aspect of anything my team ships.

I have been in the role as PM and absolutely understand the triple constraints and how to navigate the urgency of delivery while keeping an eye on scope and quality -- communication is the key and iterative delivery is the way to successful deployment and customer satisfaction.

3. What technical skills and knowledge areas are your strongest?
I have used many technologies in Hadoop ecosystem listed here in my competency order-
Elastic stack or ELK for real time big data load (ES Ingest) , transform (Logstash) or , store (Elasticsearch) and visualization (Kibana)
Spark- for data analysis with jupyter in Python and Scala Now getting interested in tensorFlow

4. Experience with Big Data (Hadoop), NoSQL software architectures and Restful APIs. Please explain.
I have used few data stores for that fall under NoSQL category - Cassandra, Hbase, MongoDB and Elasticsearch.
Elasticsearch provides its own REST interface. 
I have used Python Flask for REST services and integrated with Elasticsearch and HBase.

5. Define product strategy and roadmap for Customer Insight driven Personalization capabilities in collaboration with multiple stakeholders across 

Product strategy based on vision with clear goals for iterative delivery of prioritized features and expectation.  A roadmap of execution along with assessment of impact on each organization is shared with stakeholders across business units.   

Personalization capabilities or recommendations based on 360 view of customer. Machine Learning model and data driven insight based on customer behavior with roadmap for improving the relevancy of results with feedback loop.

6. Experience working on vizualition and data insights 

I have not worked in retail or digital marketing. I am familiar with recommendation algorithms and Machine Learning.

union
cogroup
join
countByvalue

saveAsTextFile
saveAsHadoopFIle

=== ML
http://stanford.edu/~rezab/dao
http://stanford.edu/~rezab/slides/sparksummit2015


==== example spark code ===
Average

var data = sc.parallelize(Seq(("A", 2), ("A", 4), ("B", 2), ("Z", 0), ("B", 10)))

// data: org.apache.spark.rdd.RDD[(java.lang.String, Int)] = ParallelCollectionRDD[31] at parallelize at <console>:12




val avgValue = data.mapValues((_, 1)

        .reduceByKey((x, y) => (x._1 + y._1, x._2 + y._2))

        .mapValues{ case (sum, count) => (1.0 * sum) / count }

        .collectAsMap()

// avgValue: scala.collection.Map[java.lang.String,Double] = Map(Z -> 0.0, B -> 6.0, A -> 3.0)

* useful urls
for spark hadling s3 files in hadoop2.6.0 and later
http://stackoverflow.com/questions/30851244/spark-read-file-from-s3-using-sc-textfile-s3n


http://stackoverflow.com/questions/30851244/spark-read-file-from-s3-using-sc-textfile-s3n


* Spark ML
Classification: logistic regression, naive Bayes,...
Regression: generalized linear regression, survival regression,...
Decision trees, random forests, and gradient-boosted trees
Recommendation: alternating least squares (ALS)
Clustering: K-means, Gaussian mixtures (GMMs),...
Topic modeling: latent Dirichlet allocation (LDA)
Frequent itemsets, association rules, and sequential pattern mining


In spark 2.0 data frame based api.  Adv: spark datasources, sql queries, tungsten optimization

org.apache.spark.ml

Dataframe (Columns in a DataFrame are named unlike RDD) 

>Transformer (algorithm that transforms a DataFrame with features into a DataFrame with predictions or transform a text colum to feature vectors etc.)
>Estimator (algorithm which can be 'fit' on a DataFrame to produce a Transformer or train on data)
>Pipeline (chain of multiple Transformers and Estimators together to specify an ML workflow.)

Example: a learning algorithm such as LogisticRegression is an Estimator, and calling fit() trains a LogisticRegressionModel, which is a Model and hence a Transformer.

Transformer.transform()s and Estimator.fit()s are both stateless

To train a model-
Rawtext --Tokenizer.transform()-> Words --HashingTF.transform()--> Feature vectors --LogisticRegression.fit()--> Logistic Regression Model

To test a model-
Rawtext --Tokenizer.transform()-> Words --HashingTF.transform()--> Feature vectors --Logistic Regression Model.transform()--> Predictions 

DAG pipeline: Directed Acyclic Graph
A Pipeline’s stages are specified as an ordered array. 
Type checking is done at runtime using the DataFrame schema

>Parameters: A ParamMap is a set of (parameter, value) pairs.

parameters are passed as ParamMap(lr1.maxIter -> 10, lr2.maxIter -> 20)

val pipeline = new Pipeline()
  .setStages(Array(tokenizer, hashingTF, lr))

val model = pipeline.fit(training)

You can save a model or a pipeline to disk
pipeline.save("/tmp/unfit-lr-model")
model.save("/tmp/spark-logistic-regression-model")

Load it back by-
val sameModel = PipelineModel.load("/tmp/spark-logistic-regression-model")


Ex: model.transform(test)

See code sample at https://spark.apache.org/docs/1.6.1/ml-guide.html

To tune a model (ie selecting right parameters for a given task):
spark.ml supports model selection or tuning using the CrossValidator class.  Crossvalidator splits the data into n folds ie n=3 generates 3 training and test datasets pairs that uses 2/3 data for training and 1/3 for testing.  Crossvalidator iterates over the set of ParamMaps to train the given Estimator and evalautes using the given Evaluator.  
Ex: RegressionEvalautor for regression problem

The ParamMap which produces the best evaluation metric (averaged over the k folds) is selected as the best model.

CrossValidator can select from a grid of parameters. Use ParamGridBuilder utility to construct the parameter grid.
This method of choosing parameters is more statistically sound than heuristic hand-tuning.

Note: TrainValidationSplit is another method used for tuning.  Difference - it doesn;t use k-fold but goes through only once. Adv- less expensive Cons:results not as reliable when the training dataset is not sufficiently large.


// Evaluate clustering model by computing Within Set Sum of Squared Errors
val WSSSE = clusters.computeCost(parsedData)
println("Within Set Sum of Squared Errors = " + WSSSE)

See code examples/src/main/scala/org/apache/spark/examples/mllib/KMeansExample.scala


Q: differenece between vector and list
A: Standard ML lib provides vectors. Vector differ from list in their access properties. A vector is a random access data structure, whereas list is strictly sequential access.


* My job responsibilities:

Architect and design a workable solution
workflow
data flow
Participated in backend data architecture and APIs

Ingest data from different sources and land it on kafka.  Another job to filter, transform & enrich data 

Data pipeline:  
Enrich data- initial filters, append metadata
topic (LDA)
geo location (location from Ip)
>Sentiment analysis using Spark  ML
  check: https://devpost.com/software/spark-mllib-twitter-sentiment-analysis
  twitter OAuth for retrieving tweets using Twitter Streaming API

>Sentiment using python
  tokenize
  sentiment value of each word(ngram) and lexicon model to get the sentiment of whole sentence
  pip install textblob (textblob gives polarity of text)
  import tweepy
  check youtube (twitter sentiment analysis by Siraj Raval)

> Sentiment analysis - alternative approach
  ML Naive Bayes for classification: cons: makes assumptions that features are independent
; probability as 0 and 1 and not discreet or continuous. Pros: NB offers simplicity as a viable cheap baseline.

  Training data using 1.6 million tweet training data available by Sentiment140
  Compared with Stanford CoreNLP sentiment prediction
  Sentiments: Positive, Negative, Neutral
  Only english tweets
  Only tweets with location, other discarded because of business use case 

  Result of tweets is published to Redis which is subscribed by front-end webapp for viz
  D3.js - datamaps used for viz, hover over to see additional details on tweets
  )

   
load data to ES and Hbase

Build consumer database -
call REST calls (Ex: full contact to attach social handle to person)
demography information

Access/Search -
build search db (shards, mapping, DSL queries)

REST - using python flask (api read from ES and Hbase) for renderng the front-end

Docker- docker image with dependencies


Q:How to fine tune and test model?
Q: If you have to analyze data - what language and method do you use?
Python/panda to read data from files: quick to desc data,  unique values, data quality, missing data, mean/avg, data types. Plot easily - ex residual plot etc for viz data errors using matplotlib
  statefarm = pd.read_csv("DataforCleaningandModeling.csv")
  print(statefarm.head(5))
  print(statefarm.describe()

ES
Spark

Sensitivity (or Recall): Percentage of positives that are correctly identified
Precision: out of total positive predictions what percentage were correct
Specificity - percentage of negatives that are correctly identified as negative.


Recall = TP/(TP+FN)
Precision = TP/(TP+FP)

Clustering: unsupervised learning problem -  group data based on some notion of similarity.
Often used for exploratory analysis and/or as a component of a hierarchical supervised learning pipeline.

Spark ML has following clustering models:
K-means (predefined number of clusters)
LA
Gaussian mixture
PIC
streaming k-means

LDA:topic model which infers topics from collection of text documents
Features are vector of words count . LDA takes a collection of documents as vectors of word counts and parameters like-
k: number of topics
optimizer: 
topicConcentration:
maxIterations: 50-100 iterations give a reasonably good results
 
// Load and parse the data
val data = sc.textFile("data/mllib/sample_lda_data.txt")
val parsedData = data.map(s => Vectors.dense(s.trim.split(' ').map(_.toDouble)))
// Index documents with unique IDs
val corpus = parsedData.zipWithIndex.map(_.swap).cache()

// Cluster the documents into three topics using LDA
val ldaModel = new LDA().setK(3).run(corpus)

// Output topics. Each is a distribution over words (matching word count vectors)
println("Learned topics (as distributions over vocab of " + ldaModel.vocabSize + " words):")
val topics = ldaModel.topicsMatrix
for (topic <- Range(0, 3)) {
  print("Topic " + topic + ":")
  for (word <- Range(0, ldaModel.vocabSize)) { print(" " + topics(word, topic)); }
  println()
}


### Recommendation system
> collaborative system
> content based system

python has a library called lightfm that uses hybrid approach using warp ( Weighted Approximate Rank Pairwise) as loss(measures the difference between predicted and actual) uses the gradient decent

 
> MovieLens data set is training data set - a csv with movie ratings given by users

Q: Difference between Decision Tree Vs Ensemble or Random Forest
DT - tree like graph consist of nodes (or leafes) and set of decisions(branches)

Cons: information gain is biased depending on level in attributes
complex- coz of interdependency of variables

RF is combining multiple DT as in forest of trees 
Overfitting - mitigated by use of pruning

Q: Advantages of RF over Linear Regression
Both are popular family of classification and Regression methods
Random Forest is able to discover more complex dependencies at the cost of more time for fitting.

Q: what is entropy?
Refers to disorder

Q: What is Gini index?
Measure of statistical dispersion or inequality

Q: MSE mean squared Error and RMSE Root MSE 
Measures the average of squares of deviation. Value closer to zero are better.



Another good url for Spark ML pipeline: https://www.infoq.com/articles/apache-sparkml-data-pipelines


CPU cache: holds data that is referenced often automatically.

shuffle: redistributes data - a costly opertion coz it involves disk i/o, data serialization, network i/o, heap memory intensive. certain spark operations can trigger shuffle Ex: groupByKey, reduceByKey, cogroup and joi

Window types: Sliding window(continuously overlapping), tumbling window(window do not overlap), custom window(start or stop window at particular event occurance)

