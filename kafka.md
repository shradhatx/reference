Kafka is a distributed replicated logs for decoupling data pipelines or systems with transformation as well.
Build by linkedin and Confluent distributes kafka 
Release 0.8
Has features of messaging.  Quite performant.
Topic - logical grouping of partitions or logs files.  
Guarantees sequence within partition.  Old data pruned off in the end or you can define compaction
Stream of data organized by offset (incrementing number)
Multiple consumers fetch the data from any offset without contention.

Data durability guarantees from producer perspecive - ack from system that the data is saved.
Configured as per requirement --
  At least leader got the data
  It got to n numbers of partitions
Upstream gets ack only when the configuration criteria is satisfied. There are times when data is there with some node but upstream thinks its not.

Broker is a consumer as well other than having replication responsibility.

Leader broker marks the data as committed after he gets the offset information from other brokers. Only then the data is visible to consumer.

ISR - Insync Replica Set

Leader election when a broker dies.  

Controller - single broker that has special power to orchestrate the cluster to choose leader.

Producer - light weight application.  Steps - metadata request & learns about partitions and leaders of each partition and therefore write intelligently. 

Consumer - two types of consumer - low level and high level. Consumer group with multiple consumers for fast computation reading from different partitions. Partition is read from one and one consumer only.
Simple consumer doesn't have to rebalance when a consumer goes down but high level consumer rebalances.

Performance:  ~500,000 recs per sec -- bound by network limits

Kafka uses kernel copy and writes straight to socket from page cache for realtime copy.  If data is old then off course disk read is involved.

Uses TCP
TCP Vs UDP: two types of IP traffic. TCP is connection oriented while UDP is connectionless. Multiple messages are sent in chunks as packets using UDP.  TCP for high reliability where as UDP is best effort for efficient transmission like games. TCP for http and ftp; UDP for VoIP.
TCP maintains the order of packets whereas UDP doesn't maintain order.


Client library in Python, Go, JavaScript, C and Ruby

First steps: 
check youtube by Joe Stein: Developing Ret-Time Data Pipelines with Apache Kafka time:38:22

See also elodina.net for tools like mirror maker
Recommendations: look at the usecases and see what kafka cannot do. (kafka doesn't do idempotent - but you can build around it). Hard to say which one to choose without specifics.

download kafka
start zookeeper
create kafka topics indicating zookeeper port:2181
start producer connecting to broker on port 9092 and a topic
start consumer that reads from a topic zookeeper at port 2181

google check -self service tools for kafka and es

Mesos - open source. Operating system for data center.
Best to use mesos for running kafka. Automating operational tasks like rolling restart, auto scaling, config change.
If you are not using mesos you are doing static partitioning that results in wasted resources.

With kafka the zookeper is for one DC.  MIrroring for replicating data to other DC but not guarantees durability.
Note: Multi DC quorum possible in Cassandra as consumer tell the producer that it is replicated across clusters in other DC.

In 0.9 consumer is rewritten, completely redesigned with good api. No more dependency on zookeeper on consumer side. Coordinator is managing tasks. 
How do i manage my offset - taken care of
Security, kerberos and SASL is part of it.

Post 1.0 kafka will have connectors like jdbc, s3 etc, transformation etc

Kakfa keeps key-value; key can be metadata, value is the message.


Q: How long kafka keeps the data? 
Configurable: based on time, after x GB, or use compaction(rmove the old messages for same key).

Re-think the data you need to store and the economics.

github.com/mesos/kafka

Youtube: Containerized Data Persistence on Mesos with Kafka, MySQL, Cassandra 

orchestrate the microservices you are building

SAN - money spent on it so important to utilize these
NAS - network attached storage like appliance to upgrade your system
WORM system - write once read many

Static partitioning-
container to isolate the applications say webserver, cassandra etc.
Cons: wasted space because of lack of proper partitioning or under utilization of infrastructure

Mesos: operating system of your system - abstracts the resources of all of the machines as one system.
It allocates resources cpu, ram and disk space. You ask for resources by criteria as you launch framework. Roles are a way to do static reservation for a framework to avoid starvation.
Dynamic reservation 
Persistence resources for storage services- you can mount file system inside sandboxes so data is not lost when you disconnect.
Can scale up or down without stopping.

Parts:
  mesos master
  mesos scheduler + executor = framework
  mesos slaves

Running kafka on mesos helps utilize CPU more effectively since kafka doesn need whole lot of cpu so it can be taken by other frameworks. 

Scheduler provides all the automation like failure scenarios etc.
Executor just runs kafka broker.






