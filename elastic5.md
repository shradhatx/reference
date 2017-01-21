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

Node: single jvm - not physical but logical

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

Tip: Persistence variable length messaging queue such as kafka - provides a good way to take care of spiky load or burst traffic and back pressure.
(Kafka, Redis, Messaging Queue)

Tip:  To avoid split brain phenomena (due to node failure and cluster split)
Two cluster running independently like Hot warm or Hot Cold, Active-Active

Snapshot and restore

a) Multi Data Center, Queue Replication
Using kafka for replication. Eventual consistency or second data center
Check - blog post 
Check - Beats community

b) Multi Data Center, Remote Queue Processing
for the cases when there is no other system of kafka messages

========= things to say
Harden the system for production use
Log stash is a server side process and runs on jvm. Requires high processing sometimes overkill for simple ingest as cvs and timestamp 

Enterprise search- less about scale, more about complexity
ex: embedded search

Kibana for analytics
Navigation, suggestion, document preview are not kibana features

Have a custom interface for users-
(any interface you use in day to day life)

Log stash is now in java - knobs and dials can be configured easily.
You can use python to use bulk loads- 
python - multiple documents in one simple txn
python multiple cores

Cons: Log stash queues are fixed length therefore you may need kafka

Aware of spikes if you are using log stash

What is the best way to get data from RDB?

custom scripts
many users do with custom views in RDB

www.elastic.co/community/meetups

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

