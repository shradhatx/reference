#### todo
Revolution retail:
Rocky Massey Dir IT
Mark Levenick CEO
Sandra Hayes VP Opn

DW
NoSQL comparison: CAP consistency or availability what is important
    HBase- good for realtime random access since it know what Region Server data resides in. 
    Can have Primary and secondary indexes. Shared table for multi-tenants.
    Cassandra- ring architecture, tunable consistency.

Spark 2.1.0  & MLib recommendation algo
  RDD (resilient distributed datasets have actions and transformation)
  Transformations:
  map(returns a new RDD by running a function on each element) and flatMap(each input item can be mapped to 0 or more output items)
  coalesce(numPartitions): Decrease the number of partitions in the RDD to numPartitions. 
      Useful for running operations more efficiently after filtering down a large dataset.
  join returns a dataset of (K, (V, W)) pairs 
  cogroup returns returns a dataset of (K, (Iterable<V>, Iterable<W>)) tuples.
  repartition(numPartitions): Reshuffle the data in the RDD randomly to create either more or fewer partitions
  Actions:
  reduce(func): Aggregate the elements of the dataset using a function func (which takes two arguments and returns one). 
  collect(): Return all the elements of the dataset as an array at the driver program. 
  closure
  shuffle: redistributes data - a costly opertion coz it involves disk i/o, data serialization, network i/o, heap memory intensive  - certain spark operations can trigger shuffle Ex: groupByKey, reduceByKey, cogroup and join
  cache: persist RDD in memory, cache is fault tolerant. Drops data in LRU fashion.
  broadcast variables: read only shared variables. SparkContext.broadcast(v)
    Ex: val broadcastVar = sc.broadcast(Array(1, 2, 3))
        broadcastVar.value

  accumulators: like counters. Tasks running on a cluster can then add to it using the add method. However, they cannot read its value. Only the driver program can read the accumulator’s value, using its value method.
  spark-submit to deploy to cluster

MlLib: classification, regression, clustering, and collaborative filtering(for recommendations)
     Collaborative filtering: user-item association matrix. Uses ALS(alternating Least Squares) alogorithm.
     The model tries to find latent factors(implicit or explicit)to predict the expected preference of a user for an item.

AWS cert notes- Adv:low capex, opex, scalability, agility, resiliency-DR, replication-robust
    

Architecture tips

Query optimization
  Joins- hash, sort-merge
  Read optimization - serial scan, range scan, small scan

ESB
 connectivity layer to share data between systems like CRM,ERP, SCM, BPM

Avro and 

IoT: Time series: sequence of observation ordered by time. 2parts- systematic (function of time) & stochastic (noise or variation or fluctuations - seasonal )
Ontology- Data characteristic and catalog- interrelationship between entities in a domain. sharing info in common vocabulary

Interview smooth talk
Situational questions

* Datawarehouse
Handling 3 V’s of big data: volume, variety and velocity.
Big data provides new capabilities.
Current infrastructure is expensive and limited in handling 3 V’s. 
Drawbacks-
- Sampling Vs ML
- Time to act

* Decisions to be made:
i) Big Bang Vs Evolutionary approach. Pace: plan a transitional
architecture to incrementally introduce spark element.
ii) Look at what DW capabilities you want, advance analytics
etc. Federated tool for data discovery.
a)How much data and how much to structure. Or you use the
unstructured data and mix with structure data to derive insight.
b) Culture: Experimentation of data. Think compliance.
Democratize access.
Data lake: massive data, easily accessible, raw format.
Data Lake modularization- analysis purpose, experimentation, self service BI.
Ingest and land the data to data lake.
c) Cost performance analysis of workload. Lower cost of transformation.
d) Use data lake as adjunct capability.
e) Explore new opportunities in addition to EDW.
Enterprise data - CRM, HR systems, Operation system.
Add - audit, governance.
Checklist-
- Ingest data and scale
- add analytical muscle
- EDW & Data lake in unison
- Enterprise capability in the lake
Step 1: 3 months Landing and Ingestion —> Data Lake
Streaming data from internal systems
External data
Step 2: Start experiments - analytical workload
- batch/mini batch
- latency, conservation/storage, security, deployment, access.


1. Why are you looking to leave your current role (OR Why did you leave this position?)
I am working as an ELK (also known as Elastic stack) consultant and my role is primarily focused on providing prototypes that are mainly to do with solving the sticky business issue utilizing Elastic capabilities.  
Although I like what I am doing and gives me the opportunity to expand my depth of knowledge in Lucene and search technologies I like to have an insight into business problem and end to end solution.
Also, my family is in Allen and I like to find work in Dallas area.

2. What Job responsibilities and duties do you excel at?
I read the job responsibilities of Advanced Analytics Manager and it absolutely resonated with what excites me everyday
Bias towards action. You believe that speed and quality aren’t mutually exclusive. 
Design and business vision. You help your team understand requirements beyond the written word. Even in the absence of a PM or a designer, you show great attention to the design and product aspect of anything your team ships.
I have been in the role as PM and absolutely understand the triple constraints and how to navigate the urgency of delivery while keeping an eye on scope and quality -- communication is the key and iterative delivery is the way to successful deployment and customer satisfaction.

3. What technical skills and knowledge areas are your strongest?
I have used many technologies in Hadoop ecosystem listed here in my competency order-
Elastic stack or ELK for real time big data load (ES Ingest) , transform (Logstash) or , store (Elasticsearch) and visualization (Kibana)
Spark- for data analysis with jupyter in Python and Scala

4. Experience with Big Data (Hadoop), NoSQL software architectures and Restful APIs. Please explain.
I have used few data stores for that fall under NoSQL category - Cassandra, Hbase, MongoDB and Elasticsearch.
Elasticsearch provides its own REST interface. 
I have used Python Flask for REST services and integrated with Elasticsearch and HBase.

5. Define product strategy and roadmap for Customer Insight driven Personalization capabilities in collaboration with multiple stakeholders across 

Product strategy based on vision with clear goals for iterative delivery of prioritized features and expectation.  A roadmap of execution along with assessment of impact on each organization is shared with stakeholders across business units.   

Personalization capabilities or recommendations based on 360 view of customer. Machine Learning model and data driven insight based on customer behavior with roadmap for improving the relevancy of results with feedback loop.

6. Experience working on customer insights and analytics across retail, digital marketing or other business to consumer. Please explain 

I have not worked in retail or digital marketing. I am familiar with recommendation algorithms and Machine Learning.

