# Elastic Stack - reference material
Elasticsearch - distributed, search performant, interface with json, RESTful interface
> ES advantage: boosting, scoring, relevancy, significant terms, fuzzy matches, geo-distance, aggregation
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



# Migrating to new version
Rolling restart Vs cluster restart

> Difference between sharding and partition? 
Sharding-keeping the related data together Vs partition-splitting the data for parallel processing

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
As you add memory to Heap space to jvm garbage collection becomes expensive. If it grows beyond 32G jvm can no longer use pointer compression. So you need to add more nodes to same physical machine. But ES is meant for scaling out not up. If you add another node to physical machine then route replicas correctly so not to have single point of failure.
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
What does your client intend to do with the dashboard? How much
interactivity do they want in place? Is the data real-time? What
sort of advanced statistical analysis does your client want to
run?
Tableau- Cons: slow, maintenance - unfriendly, requires restart
Pros: Gets data from multiple systems. Statistical R integration
built in.
Kibana - connects to ES. Its limited but pretty interactive and
minimal upfront effort to put useful and pretty dashboard,


### Technical reference
* [elkref] - all about elastic stack
* [sparkref] - Spark
* [nosqlref] - Cassandra, HBase, MongoDB
* [logstashref] - Logstash


### Tips

Use of links:

* [node.js] - evented I/O for the backend

Another example [public repository][dill]
 
And another way to give links [latest pre-built release](https://github.com/joemccann/dillinger/releases).

References, here is how to use them:

* [plugins/dropbox/README.md] [PlDb]

Here is  _text in italics_ or  `red color text` 

```sh
shell command here
```

#### Use of hyperlink

See [my github home page](https://github.com/shradhatx/reference/blob/master/README.md)

**This and more!**

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [elkref]: <https://github.com/shradhatx/reference/elkdoc>
   [sparkref]: <https://github.com/shradhatx/reference/elkdoc>
   [nosqlref]: <https://github.com/shradhatx/reference/nosqldoc>
   [logstashref]: <https://github.com/shradhatx/reference/logstashdoc>

# Monitoring
Marvel, Watcher or GET _cat/health, x-pack


# Migrating to new version
Rolling restart Vs cluster restart


# Sizing



### Technical reference
* [elkref] - all about elastic stack
* [sparkref] - Spark
* [nosqlref] - Cassandra, HBase, MongoDB


   [elkref]: <https://github.com/shradhatx/reference/elkdoc>
   [sparkref]: <https://github.com/shradhatx/reference/elkdoc>
   [nosqlref]: <https://github.com/shradhatx/reference/nosqldoc>