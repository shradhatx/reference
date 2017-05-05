## Elastic 5.x webinar notes
Elastic webinar 5.0
10/20/16
Proven Architecture Patterns

Beats- lightweight data shippers
Kibana- browser based UI viz on fly from data in es
Useful link to setup shield with kibana:   
http://www.slideshare.net/knoldus/introduction-to-shield-and-kibana?qid=e2144172-c5d5-4d13-92a6-199edaba021d&v=&b=&from_search=2

x-pack -way of distributing plugins
xpack-security - formerly shield 
xpack-alerting - formerly Marvel
xpack-graph 
xpack-sharing
xpack-ML

Node: single jvm - not physical but logical
Tips:
To uncompresse fn.7z
  sudo apt-get install p7zip-full
  7za e fn.7z 


With 5.0
Don’t need logstash

Beats — Elastcisearch — Kibana

Ingest node - new node type - data enrichment. We can eliminate logstash enrichment capabilities

Use ingest node pipelines with 5.x

Ingest node: doesn’t include all log stash transformation or output.  It simplifies development and implementation as you don’t need to install log stash. Takes care of back pressure.
Dramatic improvement for ingesting delimiter based input.

Master nodes- control nodes(recommended 3 for high consistency and availability). Hot nodes - designed to perform high volume indexing. Warm nodes- can tolerate lil more response time.   

Trick: Optimize single node and then scale.

Encrypting communication between nodes

Tip: persistent data in kafka - provides a good way to take care of spiky load or burst traffic and back pressure.
(Kafka, Redis, Messaging Queue)

Tip:  To avoid split brain phenomena (due to node failure and cluster split) have a master node

Tip: Two cluster running independently like Hot warm or Hot Cold, Active-Active

Snapshot and restore

========= things to say
Harden the system for production use
Log stash is a server side process and runs on jvm. Requires high processing sometimes overkill for simple ingest as cvs and timestamp 

Cons: Log stash queues are fixed length therefore you may need kafka

Aware of spikes if you are using log stash

What is the best way to get data from RDB?

custom scripts
many users do with custom views in RDB

## Elastic 2.3
es 2.3.4 
(2.4 has bugs - position_increment_gap)

./elasticsearch --cluster.name estest --node.name estest_n1

If it gives heap memory error set the environment variable to 

_JAVA_OPTS="-Xms2g -Xmx2g" 

or _JAVA_OPTS="-Xms512m -Xmx512m” 


GET _cluster/health


ES configuration 

i) check file descriptors
curl localhost:9200/_nodes/stats/process?pretty
 set it to 32 or 64k

ii) virtual memory - increase mmap count
sysctl -w vm.max_map_count=262144

edit /etc/sysctl.conf file and set vm.max_map_count

iii) disable es process swapping
sudo swapoff -a
 edit /etc/fstab and comment any line with word swap

iv) edit config/elasticsearch.yml
bootstrap.memory_lock: true
check it is set correctly
curl http://localhost:9200/_nodes/process?pretty

set path to log, data here
also cluster name and node name

node:
  name: ${HOSTNAME}
node:
  name: ${prompt.text}


$split -b 500000000 npidata_20050523-20160911.csv


input {  
      file {
          path => “fn.csv”
          type => "core2"
          start_position => "beginning"
      }
}

https://www.elastic.co/blog/loading-wikipedia


Interview questions-

* What are your recommendations for scaling up our logging project to handle 200k events per second?
* What are my options for importing data into Elasticsearch from a SQL server?
* How do I reindex my data after changing a field type from text to integer?
* What upgrading best practices should I be aware of in my environment?
* How can I use the Elastic Stack for time series data?
* How can I apply machine learning to my Elasticsearch data?

### Amazon Elasticsearch service(ES 5.1.1)
solution eliminates the heavy lifting of deploying, managing, and scaling solutions. 
Using a managed service also eliminates administrative overhead, like patch management, failure detection, node replacement, backing up, and monitoring.

Security:
Control access based on originating IP or Principal (user or group)
Mix policies to provide application access and kibana access
Use IAM roles

High availability:
Example
4 instance (or node) cluster, spread over 2 AZ, 3 shards, 1 replica

Dedicated master nodes for improving stability of clusters

New with ES 5.1.1
> Scripting with ES painless
 Scripting usefule to change the search result precedence or delete a search output
> ingest pipeline
 add fields, transform fields and grok etc
> New API ex: shrink and rollover index
Ex: an index with 8 shards can be turned into 1 shard index when distributed power is not needed example a week's old data 
POST source_index/_shrink/target_index

Ex: rollover an index based on number of documents or time or both
creates alias automatically
POST log_index/_rollover {"conditions":{"max_docs":100000}}

>Geoip processor plugin
>murmur3(check??) plugin predictable hash for better search

Ingest performance
_bulk API with 10k lines per call 
2.3 Vs 5.1 

with 5.1 m3.medium docs/sec: 3692
with 5.1 r3.large docs/sec: 7221
with 5.1 i2.2xlarge docs/sec: 9676

lucene 6 is better multithreading and using memory. Note 2.3 on lucene 5 doesn't scale as well with bigger machine.

> Tuning elasticsearch

See
https://www.elastic.co/guide/en/elasticsearch/reference/5.2/tune-for-indexing-speed.html#_disable_refresh_and_replicas_for_initial_loads

See:
https://aws.amazon.com/answers/logging/centralized-logging/

In short use these for optimization of indexing or load
> use bulk request
> use multiple threads/workers to send data
> increase refresh interval from 1s to say 30s
> disable refresh and replicas for initial loads
> disable swapping (java process)
> more memory to filesystem cache(that buffers i/o operations) ex: at least 50% of memory to filesystem cache
> use auto generated id (as it doesn't have to look up for duplicate id on a shard)
> use faster hardware (ex SSD) Note: if using ESB on AWS check the IOPS to avaoid throttling
> set indexing buffer size to 512MB per shard( Ex: if JVM is given 10GB give 1GB to index buffer that is good for 2 shards)

See 
https://www.elastic.co/guide/en/elasticsearch/reference/5.2/tune-for-disk-usage.html

In short for tuning disk usage ie storage the tips are -
> For numeric fields, carefully choose doc_values on fields. By default all fields have doc_values.
Note: for a numeric field, if you would not need to filter on a that field disable doc_values for the field.  Ex:

PUT index
{
  "mappings": {
    "type": {
      "properties": {
        "foo": {
          "type": "integer",
          "index": false
        }
      }
    }
  }
}

> for text fields store normalization factors in the index in order to be able to score documents. Diable norms if you do not need scoring

If you do not need to run phrase queries, you can tell elasticsearch to not index positions

PUT index
{
  "mappings": {
    "type": {
      "properties": {
        "foo": {
          "type": "text",
          "norms": false
          "index_options": "freqs"
        }
      }
    }
  }
}

> for text or numeric field : Either use keyword mapping or string mapping instead of default dynamic string mappings which does both

Ex: for ID field use keyword mapping; for body use text

> disable _all
as otherwise all fields are indexed and use significant space
index the fields you search on

> use best compression
> for numeric fields use the smallest numeric type that is sufficient
byte, short, integer, long
floating point as scaled_float
check half_float

* randomize testing
using junit

* Elasticon 2017
numbers - bkd tree
  how to make use of numbers in scoring, vizualing numbers

kibana -
  tile service for geo viz

Heartbeat -
  beat that check services uptime



Metricbeat - collects metrics in your environments and provides out of the box dashboard

In 5.3
To extend it further with filebeat and modules Ex: you can tail a nginx log file 
  $tar xzf filebeat*.tar.gz
  $./filebeat -e -setup -modules=nginx

For system logs such as ssh attempts etc
  $./filebeat -e -setup -modules=system

Kibana- In v 5.4
  new type of way to build visualizations
  how to utilize pipeline aggregation in kibana
  time series viz builder with timelion- gauge viz
  annotations (ex when site goes down, when site goes up)
  color of bars depending on what's going on ex with threshold setup
  top n - ex top 5 processes by cpu or by memory

moving average - for outliers
derivative - slope or change in f(x) with change in x
percentile band
time shifting - current Vs 10 min ago
standard deviation - square root of variance from the mean. used to measure confidence in statistical conclusions.
usecase specific aggregation -
mark down - 

Prelert - anamoly detection
  in addition to ML
  exclusive ML node
    
  usecase: which traffic congestion/event cause the maximum delays in traffic in the city
  how do you extract useful information from the data?  Once that is available it becomes obvious

  -by filtering out irrelevant data (using dsl query in ES)
  -change over time (using kibana) 
  -has this metric changed in the way it normally does (you create a model and estimate the probability of future behavior and use predictions to make decisions)

  usecase: retailer - viz  orders/min is there a change from the usual behaviour?
  Important information -bound of the signal, periodicity 
  Instead of setting threshold understand the behaviour of system
  
  usecase: performance of multiple nodes. The anaolmalies aren't easily visible in viz since avg smoothes over time

  Solution: In x-pack 5.4 there is a ml pack (see in kibana side panel)
   - create a job(s), use index, aggreagtors and split by service/host etc and then find the influencers for anomaly
   - send alert with subset of useful information for someone to look at

  usecase: security 
  Solution: Entity profiling by the status code of what they are doing Ex: count of status code by ip

  It is good to know what happened before the anolamly to various systems, data, service etc.

Check: Selenium & Python framework with data management and reporting capabilities on youtube

Note: AWS pricing model of elasticsearch service  
Service charge by instance size in each region Ex: r3, i2/4 node 
Plus data transfer fee. 
No cost of txn copy to different zones for providing services such as zone awareness and hig avavailability.
Master node has low load so a smaller node is sufficient like m3 medium for prod or t2 for dev

For ES recommended SSD for storage other choice is EBS(icomes with choice of magnetic, SSD or PIOPS) but recommended is general purpose SSD as PIOPS do not provide any extra advantage.
For ES on t2, m3,r3, i2 you can use  instant storage

For logstash disc serves as memory queue so good to use SSD.

Q: number of master node on AWS
A: recommended odd number of dedicated master node. 3 is sufficient unless your cluster is large.
you do not necessarily need dedicated master node. If not dedicated than first data node also acts as master node.  

Q: number of data nodes
A: AWS right now the max data nodes are 20

Q: you can run ES perfectly well with one node for test environment (if you want zone awareness you need 2)

Q: usecase: use ES only when they have problem
keep logs on s3.  Have cloudformation setup, spin up on demand when you see an issue
 
Q: elastic cache (redis or memcache) helps by caching the answers of common search for heavy search environmeny.  The search expires after certain time.
 

See - Elasticsearch on AWS March 30, 2017 - creating 3 nodes ES cluster ES 2.3.3
https://www.elastic.co/blog/running-elasticsearch-on-aws
(see the benchmark your solution link in the article as well)
1)
Amazon Linux AMI 2016.03.0 (HVM), SSD (Free)
Instance type: m3.2xlarge (provides 8vCPUs, 30GB memory, 2x80 GB SSD with high n/w perf)
Number of instances: 3
Select "Enable termnation protection"
Add storage: 8GB General purpose SSD (IOPS 100/3000), Delete on termination:checked, Not Encrypted
Security Group: tcp 9200 for REST API access, 9300 for internal cluster comm
Allow SSH connection on port 22

Launch EC2 instance that is setup
On each EC2

2) ssh into the EC2 instance
$chmod 400 fn.pem
$ ssh -v -i /pathto/fn.pem ec2-user@[ec2hostname]

Install the ES RPM package
$sudo rpm -i https://download.elastic.co/elasticsearch/release/org/elasticsearch/distribution/rpm/elasticsearch/2.3.3/elasticsearch-2.3.3.rpm

Register Elasticsearch as a system service.
$sudo chkconfig --add elasticsearch

Install aws cloud plugin
$cd /usr/share/elasticsearch/
$sudo bin/plugin install cloud-aws

$sudo vi /etc/sysconfig/elasticsearch

ES_HEAP_SIZE=15g
MAX_LOCKED_MEMORY=unlimited

Note: ES_HEAP_SIZE is recommended to be half of EC2 memory but not more than 32GB

$sudo vi /etc/elasticsearch/elasticsearch.yml
cluster.name:esonaws   --note: where esonaws is the name give to EC2 instance at time of setup
bootstrap.mlockall: true 
discovery.zen.ping.unicast.hosts: [_ip_address_,…] 
network.host: [_ip_address_]

$sudo service elasticsearch start

verify by -
$curl localhost:9200/_cluster/health?pretty

If all good see the status as green and number of nodes as 3

For error logs see /var/log/elasticsearch


#### comparing performance of logstash and ingest node

Steps: push logs with filebeat directly to elasticsearch.
Use AWS C3.large(2 vCPU) for filebeat and c3.xlarge(4 vCPU) for elastcisearch.
Install SPM to monitor Elasticsearch performance

Network is a bottleneck, CPU is not 100% utilized
Thruput observed: 12K docs/s for raw logs
Thruput observed: 4K docs/s for json logs as network saturation as json log is bigger in size.

insys group - work for Stan 


















  
  
 






 








