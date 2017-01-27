## Spark: data bricks webinar about spark and spark on cloud

https://www.brighttalk.com/webcast/12891/230091?utm_campaign=Webinar&utm_medium=email&_hsenc=p2ANqtz-8T1sjntbZP3y1M5OXBYtquQaMAz-nIr6zUBZwrNG8FbtktIIV_eR5xR3cPCTcDtmpJEMCUkh9zye7Lb2brMuYTU2D64Q&_hsmi=37339817&utm_content=37339817&utm_source=hs_email&hsCtaTracking=57810cfb-9866-47e2-b753-aa768de50789%7C7994147a-a241-4045-aa5f-f228c548bcca

## MR
Paraquet files
FiloDB by Tuplejump
MR has been the work horse of Hadoop since inception. MR-batch oriented etl. Resource mgr yarn and impala use non MR architecture with SQL capability.
￼Spark is another choice for general purpose computation. MR implemented in this is faster and easy to maintain. MR takes k-v as input and gives k-v as output. In spark you work with RDD (of strings or anything else) and now Data Frames(schema aware). Single pass computation.
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
2. Kafka - more than queue - it is partitioned distributed commit log on disk. messages by topic. multi pub-sub . Messages stay for a while after they are consumed


