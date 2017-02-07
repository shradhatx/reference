# Our client business

# Business problem

# Technical requirements
Data pipeline evolved over time
Ingest from 100s of website
Attach tag for each data source; initial filters/clean; data+metadata put in kafka; --> reliable data ingestion 
Use spark and transform and put in another topic --> clean data
Build model every day and put the model results in s3.
Use Spark to read data from kafka, apply model get topic and land data into HBase.
Loading ES index with user data aquired from DB USA (30 million).

Separate process reading social handle, name, location from kafka, hit ES dbusa data & match with dbusaID and put in cache ---> also in Hbase dbusaid, social_handles

If no name, location then hit third party (fullcontact) to get info -- hit ES dbusa & match with dbusaID.

Periodic update of cache from Hbase (cache with different handles -fb, twitter,...)



# Benefits
> decreased the work load - instead of going over the pdf text and 250+ categories, the application gives them 5 top categories to look and choose from or reject


## things to say -
> today  I am here to present... for first half of the session I am going to ...and then for the second half I am going to share ....
...now what is ...why does it matter....what should we do.... may be sometime we need to go little bit faster..because the info a day later may not be useful enough...

> we have to do to it without changing the current process
> kinda like flying a plane and change the engine withoud landing

> ecosystem of AWS services
> repertoire of services
> myriad of devices

> get to the meat of the code
> this is what you have to capitalise on

> end to end streaming data, data capture, ingestion, loading at high velocity, processing-filtering-transforming, and redirecting to redshift/es/firehose

> lessons learned

> What I'd like youto take away


##### AWS Knesis 
simple, reliable, scale, real time latency- high performance
Transformative building block
Enabler for 'real time everywhere'

For operational analytics

Capacity in number of shards, to scale add or reduce shard

Ingest: Log4j, Flume, Fluentd, Kinesis Producer
Consume by: Get APIs, Kinesis client library, Lambda, Spark, Storm, EMR

Date ingested is replicated to 3 AZ
Data emitted as ordered events
Multiple producer and consumers
Stream retention from 24hour upto 7 days

The maximum size of a data blob (the data payload before Base64-encoding) is up to 1 MB

Each shard can support up to 1,000 records per second for writes. No limit on number of shards.

Set buffer size, compression and encryption - equivalent to creating window processing


Use kinesis firehose to get large files to S3.  


 


