# Elastic Stack - reference material
Elasticsearch - 
* distributed, scalable and highly available(no single point of failure)
* simple yet sophisticated RESTful API
* Efficient access, Real-time full text search, structured search and analytical capabilities
* Ability to easily combine Geolocation with search and analytics 
* Spark ES connector (get or put data in v efficient manner)

Write benchmarks: 250K event writes/sec with a 5 node cluster

> ES advantage over other stores-
 efficient access
 advance search capabilities
 - boosting, scoring, relevancy, significant terms, geo-distance, aggregation

Lucene uses algorithm HyperLogLog++. Uses only kb of memory for billion distinct terms.
Check youtube: [Alex Brasetvik](http://found.no/foundation)

# Things to keep in mind when designing
* split brain (must have enough nodes to protect data loss in case of n/w failure) 
* hot spots (caused by poor sharding)
* back pressure, crash coz of outOfmemory error without warning
* Sharding- depending on data & use case: Shard by date or day-range allows easy delete, range-based, hash-based and location-aware sharding or custom sharding using routing string.

Security: Till 1.x security is critical - carefully set user rights. Scripts used in agg can expose your data. ES: is lenient and trust the users so adept these to your need.
Avoid Split brain situation: subclusters believing they are autonomous. Network issue may result in irrecoverable data loss. Have enough nodes if network fails to get majority/quorum, minimum master node = n/2 +1
Network failures: node failure, zone failure can happen. For mission critical system have the high availability setup
Things to remember on client side:
-Any request should be idempotent (or retrial so it doesn’t result in duplicates)- no side
effect.
-For http interface: client library should use connection pool.
-Use bulk interface as is efficient.
-Use msearch for multiple request.

Summary: mapping, sharding, watch memory, memory profile of queries, indempotent request, avoid split brain scenarios(more nodes n/2-1 master nodes).



# Changes in 2.x 
* pipeline aggregation (on bucket)
* better memory usage
* index compression
* doc values that rollover to disk
* methods to safeguard production system

# Monitoring
Marvel, Watcher or GET _cat/health, x-pack
Q. How do you know if a shard is bad?
Watcher - for monitoring application and Grid
Q. Issues that you run into when setting up shards? Elastic search in production. Issues running lots of clusters
Memory is important for predictable performance and cluster reliability. Used in es for filter cache, field value cache, building indexes etc. If memory is less (for agg queries) you get out-of-memory error or cluster state corrupts and requires restart. So you don’t want any memory error in prod.
Monitoring resource usage - monitor cache (gradual deterioration of perf) and heap usage (sudden event happens when this goes low)
Important to know the memory profile of searches. Memory usage may grow faster as your data grows larger.
Memory efficient query is a good idea.

When is data available 
200 Ok
201 Created ie changes are synched on all active shards
"Refresh" makes data available for search, refresh interval 1s or when buffer is full or on demand.
refresh_wait_for is configurable
consisency setting


Indexing request goes to coordinator node, "write consistency" one by default or quorum 
Search request uses LB and involves replicas & then scatter/gather

Shards(horizontal split) in ES - is for colocation of data

ES creates 5 shards and 1 replica by default
Shards can't be split across nodes.
ES index in two shards is same as two ES index with one shard each.

You can add nodes but not shards when in production.

Master node, data nodes, client nodes in Zone A, B and C
Shard routing table has cluster state. Use of 'routing key' 

Index size between 1 and 16 times the heap size.

Page cache - for term dict
Field cache - for sorting field values
Filter cache for filtering 

Inverted index building requires lots of memory, built in segments that are flushed to storage.

# Migrating to new version
Rolling restart Vs cluster restart

> Difference between sharding and partition? 
Sharding-horizontal split to keeping the related data together Vs partition-splitting the data vertically ex functional grouping  

# Sizing

# ES and Spark streaming
youtube: by @costinl (Costin Leau)
jsfiddle - for quick viz
sc.esRDSS — takes any RDD and indexes it in es. Read returns Scala collections.
Load the structure of data frame directly to es.
Load the schema from file, from memory or another system.
val sql = new SQL.Context...
table = sql.sql(“CREATE TEMPORARY TABLES ... USING org.elastcisearch.spark.sql OPTIONS(resource = “...”) “)
later ver (sparks moves filtering to the source of data ie es shard therefore faster) df = sql.load
ES: replication itself can be changed at runtime. typical: 3 partition, 1 replication.

## Do's and don'ts
- Set Mapping carefully or you have to reindex that is expensive. use dynamic mapping carefully for large record with excessive fields
- determine what fields need to be analyzed — to optimize space
- determine sharding since shards cannot be split.
- _all: by default es stores and analyze all fields - disable fields you do not need
- _source :by default es stores fields so do not use store specifically or else it will be saved twice
- understand load time script to modify data
- no update on large index as any change will reindex
- different strategy when designing for scale - shard by date or day-range; use ‘routing’ - redefine shard key by some string provided by you. Use of alias, multi- index search; measure the impact of distributed search.
Determine if your doc access be partitioned in a natural way?

## ES in production
To create highly available ES cluster:
Node types in es: master, data only node, client node(forward
the request to appropriate shard, sometimes needed for quorum)
a) To avoid Split brain issue during network splitting:
minimum_master_nodes = n/2 + 1  (where n= number of master
eligible nodes)
b) Some properties settings to help during recovery-
recovery_after_nodes
recovery_expected_nodes
cluster_routing_allocation.node_concurrent_recoveries
index_unassigned.node_left_delayed_timeout
index.priority (sets the priority of important indexes to be
recovered faster during recovery)
Two types of shards in es: Primary & Replicas
Each index has #primary specified during creation. Replicas can
be changed (using curl command).
Replicas may go in unassigned state if number of nodes are less
- state yellow.
indices.memory.index_buffer_size  : set maximum indexing buffer
size per shard.
To get higher indexing throughput — put more RAM for es for
indexation.
For time based data: use time based indices hourly, daily,
monthly.  create aliases - Ex: use daily index for indexing and
weekly for searching.
Multiple view : hot & cold nodes
ssd have lots of cpu used for hot nodes  — search for more
recently data. You can give it more memory.
ES can move from hot nodes to cold nodes by simple api calls.
Allows for handling more throughput of data.
Use routing for sharding when data is user based: round robin is
used to distribute data. Routing is also read efficient.
Query uses scatter and gather.
When routing is used we know where data is put so it is
efficient.

Q&A
Q. How Cardinality aggregation works in es 2.x? Data structure that power lucent queries.
Inverted index in lucene stores term dict with doc freq, posting list. Lucent uses ‘advance’ (does skipping to the id that is equal or greater than term searched) and ‘next’. Once doc id list is known it looks up field data to do aggregation. Field data lives on JVM Heap, put lazily at the time of query.
doc_value now used in es 2.x are computed at the time of indexing and resides on disk.
Regex queries - are slower than term
Fuzzy query - term at a distance of x
match query is not in lucene
Q. Why delete in es doesn’t immediately shrink the index?
ES: Comprise of cluster of nodes and within node multiple lucene index. With index there are segments (like mini index) and within segment there is data inverted index, stored fields, doc value etc.
Segments are immutable. When you perform delete it only marks the doc for deletion.
Every 1s when buffer is refreshed (you can adjust the refreshed interval) the deleted documents get deleted. Added segment is visible to search. Older data is still there in warm cache.
Query request comes to one shard — it becomes the coordinator. Coordinator sends request to other shards and gets the top 10 results.
Q. Best practice to allocate memory between jvm and es?
As you add memory to Heap space, jvm garbage collection becomes expensive. If it grows beyond 32G jvm can no longer use pointer compression. So you need to add more nodes to same physical machine. But ES is meant for scaling out not up. If you add another node to physical machine then route replicas correctly so not to have single point of failure.
Q. Best practices for sharding
Sharding by day or multiple day allows us to delete complete index. Cannot split a
shard into 2 when data grows. You can easily add nodes to scale. So plan ahead. You have to allot big shard to node on more powerful machines.
Q. Algorithms in ES: priority queues, finite state machines, bit twiddling hacks
Q. Facebook usecase
Help document/search-> powered by es. Threat Exchange ->es
Automated framework to index anything-> specify index, fields and data location - it specifies the json data structure —creates and submits the job — gives sample queries.
build app first, make it stable.
Scaling: multiple clusters. Easy way to spin up cluster - gather simple info: cluster name, x- data nodes in this cluster, https vs http
Health check - a simple _search GET _cat/health
Logs - throw on hdfs. Run hive. check exception on machines. what endpoint is causing exception.
Clusters in multiple data center. Things to look: encryption of data when going between different data centers.
DR: build redundancy. Ability to build indices. cron job to save settings and mappings of indices on all clusters. Fall back of tools: fall back to db. Keep snapshots. Watch for failures. clusterFS —> better infrastructure & easy snapshots
Migrations: Internal systems: shutdown cluster, copy data, restart (hour downtime). Shield: Role based access control.
Live migration when people are expecting it to be up all the time: use alias, run shadow traffic to new cluster, cluster data in sync, check for exceptions, stats for good measures, flip the switch.

## Tableau Vs Kibana
It depends -
What does your client intend to do with the dashboard? How much interactivity do they want in place? Is the data real-time? What
sort of advanced statistical analysis does your client want to run?
Tableau- Cons: slow, maintenance - unfriendly, requires restart
Pros: Gets data from multiple systems. Statistical R integration built in.
Kibana - connects to ES. Its limited but pretty interactive and minimal upfront effort to put useful and pretty dashboard,


### Technical reference
* [elkref] - all about elastic stack
* [sparkref] - Spark
* [nosqlref] - Cassandra, HBase, MongoDB
* [logstashref] - Logstash


#### Useful urls

   [elkref]: <https://github.com/shradhatx/reference/elkdoc>
   [sparkref]: <https://github.com/shradhatx/reference/elkdoc>
   [nosqlref]: <https://github.com/shradhatx/reference/nosqldoc>
   [logstashref]: <https://github.com/shradhatx/reference/logstashdoc>

# ES Setup & Monitoring
Marvel, Watcher or GET _cat/health, x-pack

ES Metrics to watch

- current http connections grouped by node and head
- user cpu on data nodes (as heat map)
- network in/out by node 
- jvm heap used by host
- shards by status
- jvm garbage collection GC time by node
- query rates for the cluster

Tips and tricks
- read the doc
- read how others use elastic
- give some planning time to sizing etc.
- architect with migration in mind
- monitor from the beginning
- learn jvm tuning
- tweak one thing and monitor (check Easton 2016 Quantitative Sizing)

engineering.chartbeat.com/2015/05/26/logstash-deployment-and-scaling-tips/


# Casestudy - Datadog
Cluster made of head and data nodes
~20 nodes, 2 replicas/shard
Head nodes as load balancer accepting HTTP request
Data nodes interact with head and other data nodes
Rolling index with one month of event data each, plus 1 future
Use slow and fast data nodes

Multiple TB log data per day
150k events/s
Each stage in data flow adds metadata


logs —>log stash —>  index on fast node —> index on slow node (older than 1 day old data)—> s3 (older than 6 day data)  —> dump(using cron job on s3 data)

[Check this for logstash scaling tips](http://engineering.chartbeat.com/2015/05/26/logstash-deployment-and-scaling-tips/)

use kafka for queuing coz 16M events/s - can’t send this much to log stash



# Migrating to new version
Rolling restart Vs cluster restart


# Sizing

# Tips for PoC
For geospatial use case and architecture:  check and excellent talk by esri on geospatial on elastic.co
(UI example for taking input and showing results —

Where: ID IN (“id1”,”id2”) and field1 > n

Time Window: start time, end time

Geometry: {“min”: -100, “max”: …. ,“spatialReference”: {…}}
Note: expressed as GeoJSON

Query GET    Query POST

Search returned 4 of 4 in 120 ms

Visualize (see white paper at esri.com/library/whitepapers
  - check ArcGIS API for java script

## Elastic 5.x
Elastic stack 5.1.1
- reindex much faster
- can cancel long running jobs
- persistent queues (you may not need kafka)
- Kibana - tag cloud
- query profiler - much better to look and find issues

* Geospatial capabilities in 5.0

Check Blueliv  — correlation of event in geo

geo_point, geo_shape, 

ignore_malformed=true   — takes care of parameters

lucene 5 : added numeric capability for upto 2 dimensions now but can be expanded to 8
BKD Tree - Block KD tree

Binary tree can become unbalanced whereas BKD Tree takes equal time for every leaf.

* ES 5.0 webinar
ES 5.0 webinar
scripting language called ‘painless’
shrink api for merging shards
wait for refresh
ingest node
profile aggregation
percolation is now a query (percolator is out)

kibana-  5.0

sense- best to build free form query and run against es
sense is called console now
Console and Timeline are shipped with kibana core

Log stash - uses java so more flexible in enriching data
Beats - low footprint shipper

Ingest node- do parsing of incoming data, in es, received from for ex Beats

Use beats processor at Beat level to drop off ex 200 ok messages

New in 5.0 
  - kafka output from Beats
  - Metricbeat

 - monitoring of logstash ex: how much time spent in each filter etc., hot threads, granular logging for individual level component, change log level with log stash running

Logstash - Support for amazon kinesis input; dissect filter(features that grok provides);   

Check youtube Elasticsearch by Honza Kral

Useful links on ingest pipeline-

https://discuss.elastic.co/t/implementing-ingest-attachment-processor-plugin/52300

https://www.elastic.co/guide/en/elasticsearch/plugins/master/ingest-attachment.html5 

https://www.elastic.co/guide/en/elasticsearch/plugins/master/ingest-attachment.html

https://www.elastic.co/guide/en/elasticsearch/reference/current/ingest.html

 https://speakerdeck.com/elastic/ingest-node-re-indexer-et-enrichir-des-documents-dans-elasticsearch-softshake-2016

Example of pipeline with attachment
 https://discuss.elastic.co/t/about-the-elasticsearch-category/21/1

Elastic search plugins
https://www.elastic.co/guide/en/elasticsearch/plugins/current/index.html


* Elasticsearch and unsupervised ML (anomaly detection - outliers, deviation, rare/different occurrence) with Prelert

Probability-prediction model (more time I spend to analyze the data better the predictions or confidence is )

Model picked depending on your data - ex periodicity determined - based on moving average — look for harmonics.

Show anomaly as well as hints on the reason of anomaly or influencer (bayseian way of eliminating)

Importance of injecting human intelligence - ex threshold numbers

Prelert is a ES plugin.

ML to solve world huger - ha ha

* Elastic search and Graph 
       Instead of data ask the relationship between documents 
          - get answers in terms of graphs and relationships

       Same index in ES new ways to query

For recommendation use significant terms and sample(through adding simple search term like 'must' we can include high quality criteria) to get better results.
Save graph as percolate query and have real time recommendation.

Graph capabilities shows interesting stuff by combining it with use of compound keys for doc identification in various different ways ex:zipcode+lastname+dob and fulladdress+last_name to describe the same person.



* Cognitive computing
4 cognitive capabilities to interact: vision, speech, data and language.

Programmable system - mathematical system - precise results are rigid based on rules and decisions

Cognitive systems learn and not programmed or rule based but evidence based. 

Human intelligence: observe,interpret & generate hypothesis,evaluate.

Augmented intelligence: augmentation of human expertise- helps to make best decision possible. Ex: Throw lots of information/ and nothing is missed in decision making.

Corpus of knowledge is curated (disregarding dated and useless info) before it can be made useful.

Watson is good at NLP speed & semantics - language - intent - extract logical inferences.
Watson’s interacts well with speech and natural language, nuances and understands many languages.  It learns from interaction or responses

It scores the hypothesis - (similar to ES)
Uses statistical modeling for scoring

Comment: technologies are complementary… there is a bit of an overlap…this gives you a way to exploring and may be prevent you from wanting to invest, complication and overhead. This gives you an easy way for exploration and start with a simple solution.



Relevance - what’s different about this population than the rest of the documents

## Lucene
Machine learning algorithms:
http://www.kdnuggets.com/2016/08/10-algorithms-machine-learning-engineers.html

Relevancy based on TF (term freq) /IDF (inverse document freq) - ranking of results using vector space model. Document is just a bag or words - the position of word in doc is not considered relevant.
Elastic uses this similarly model to extract what extent the texts are similar.
Classification of documents into finite set of categories where categories are known.

Similarity model: bm25 - a probabilistic model

How lucene does tf/idf ?  As it aims for performance the formula is not calculated entirely at query time. It uses the similarity model both while indexing and querying.
Lucent scoring is blazingly fast and hides the complexity from user.
Lucent uses vector space mode(VSM) and applies Boolean model for refinement. It also allows you to customize the Lucene internals in Changing your Scoring -- Expert Level

See the scoring formula here-

https://lucene.apache.org/core/3_6_0/scoring.html


tf/idf performs pretty well. But there are other candidates that offer more tuning flexibility (ex: bm25 good for short documents)
tf/idf is not the only ranking contributor in Elastic. Rank similarity in lucene/elastic is not a pure tf/idf implementation. (Ex: it does account for document length normalization too)  {difficult to compare with academic papers)

Similarity model of lucene: SVM score, lucene conceptual scoring formula. 

You can customize/improve lucene by-
      -Implementing your own query class,
      -Change similarities factor

ML (LDA or Word2Vec) - lot of statistical stuff to get to the answer. It is complex. It goes beyond simple tfidf feature extraction and direct matching. To determine the accuracy or improve you only have to apply models, predict and compare the result with actual and fine tune.

To create a good search experience it is key to combine textual similarity rank with metadata suiting the case.
Compare the precision and recall of models.

I strongly recommend taking the time to test both models with your documents- there can be a significant potential in using one model over other.

Circumstantial Vs general proof.


> When fine tuning ES mappings, identify:
i)String fields that are of exact-value —> not_analyzed
ii)Full text fields that use standard or english language analyzer
iii)one or two fields that need custom analyzer ex title field may need to be indexed in a way that supports find-as-you-type


full text search ex: match is high level search and preferred than term as they understand field level mappings
term based - looks at inverted index for single term
Analyzers can be specified at three levels: per-field, per-index or the global default. 

## Queries
phrase matching or proximity matching
{
    "query": {
        "match_phrase": {
            "title": {
		“query”: “quick fox”,
		“slop”:1
		}
        }
    }
}

## String query (not analyzed)

1. Full text (analyzed) - usually you want full query rather than individual terms 
{
    "query": {
        "match": {
            "title": “Family Medicine!”
        }
    }
}
## Note: about search result in match of one word or both with any other word in between

To add precision
{
    "query": {
        "match": {
            "title": {
		“query”: “Family Medicine!”
	    	“operator”: “and”
             }
	}
    }
}

## note: the above search requires both word be present

{
    "query": {
        "match": {
            "title": {
	   	“query”: “quick brown dog”
	    	“minimum should match”: “75%”
		}
        }
    }
}
## Note:above should match rules can be adjusted or use along with bool
{
  "query": {
    "bool": {
      "must":     { "match": { "title": "quick" }},
      "must_not": { "match": { "title": "lazy"  }},
      "should": [
                  { "match": { "title": "brown" }},
                  { "match": { "title": "dog"   }}
      ]
    }
  }
}
## Note: above should clauses get more weight than must

curl -XGET 'localhost:9200/_search?pretty' -d'
{
    "query": {
        "bool": {
            "must": {
                "match": {  
                    "content": {
                        "query":    "full text search",
                        "operator": "and"
                    }
                }
            },
            "should": [
                { "match": {
                    "content": {
                        "query": "Elasticsearch",
                        "boost": 3 
                    }
                }},
                { "match": {
                    "content": {
                        "query": "Lucene",
                        "boost": 2 
                    }
                }}
            ]
        }
    }
}'
## Note: able further boost
* Single term queries are better expressed as filters and benefit by caching and no score needed
GET /_search
{
    "query": {
        "constant_score": {
            "filter": {
                “state”: { “tx” }
            }
        }
    }
}


## dis_max example

{
    "query": {
        "dis_max": {
            "queries": [
                { "match": { "title": "Brown fox" }},
                { "match": { "body":  "Brown fox" }}
            ]
        }
    }
}


## combining first_name and last_name to full_name

one custom _allfield for the person’s name, and another custom _all field for the address.

PUT /my_index
{
    "mappings": {
        "person": {
            "properties": {
                "first_name": {
                    "type":     "string",
                    "copy_to":  "full_name" 

                },
                "last_name": {
                    "type":     "string",
                    "copy_to":  "full_name" 

                },
                "full_name": {
                    "type":     "string"
                }
            }
        }
    }
}

## Alternately cross-field queries provide the solution at search time. Advantage of per field boosting
{
    "query": {
        "multi_match": {
            "query":       "peter smith",
            "type":        "cross_fields",
            "fields":      [ "title^2", "description" ] 
        }
    }
}

## Partial matching (not whole term)
In inverted index only terms exist
matching part of the term and not the whole term i.e. finding fragment of the word eg from sql world %fox%

stemmer provides a way to index words in their root form

partial matching on non_analyzed fields like postal code

postal code that starts with…
search_as_you_type displaying the most likely results before the user has finished typing the search terms

prefix query …

PUT /my_index
{
    "mappings": {
        "address": {
            "properties": {
                "postcode": {
                    "type":  "string",
                    "index": "not_analyzed"
                }
            }
        }
    }
}


GET /my_index/address/_search
{
    "query": {
        "prefix": {
            "postcode": "W1"
        }
    }
}

* wildcard and regex queries
GET /my_index/address/_search
{
    "query": {
        "wildcard": {
            "postcode": "W?F*HW" 
        }
    }
}

——
{
    "query": {
        "regexp": {
            "postcode": "W[0-9].+" 
        }
    }
}

* caveat: Running prefix, regex, wildcard queries on a field with many unique terms can be resource intensive

Q: Difference between Cache and JVM heap and stack
CPU Cache is faster and more costly. CPU cache holds data that is referenced often automatically.

JVM heap, stores the application global resource. Heap is typically allocated at application startup by the runtime, and is reclaimed when the application (technically process) exits.

Stack - thread specific resource, allocated when a thread is created. Stack is faster than heap
because the access pattern makes it trivial to allocate and deallocate memory from it (a pointer/integer is simply incremented or decremented), while the heap has much more complex bookkeeping involved in an allocation or deallocation. 
 
Check this-
http://www.programmerinterview.com/index.php/data-structures/difference-between-stack-and-heap/

HyperLogLog+: single-value metrics aggregation that calculates an approximate count of distinct values. 
algorithm for the count-distinct problem in a probabilistic way. It does it in approximate way.

### Technical reference
* [elkref] - all about elastic stack
* [sparkref] - Spark
* [nosqlref] - Cassandra, HBase, MongoDB


   [elkref]: <https://github.com/shradhatx/reference/elkdoc>
   [sparkref]: <https://github.com/shradhatx/reference/elkdoc>
   [nosqlref]: <https://github.com/shradhatx/reference/nosqldoc>
