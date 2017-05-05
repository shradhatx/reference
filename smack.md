#### lightbend webinar 3-8-17
lightbend.com/fast-data-platform

Microservices and fast data are merging 
J2EE apps are monolithic - app bundled together as war or ear file deployed to app container. 

Sharing db is still designing app in a monolithic manner

Microservices: message driven and asynchronous

Message overhead is much better than function calls
(check diff between the two)

Best practices:
-ML based monitoring and management: keeps your system resilient without much intervention
- Ingest interface: REST(using lightbend lagom, play, akka) , Sockets(for ngesting from Hive), Logs(using pub-sub into kafka, kafka connect to connect to storage- NoSQL, S3 or search)

Streaming tradeoffs to choose streaming engine to use
 - latency
 - volume
 - data processing (such as CEP)
 - source and sinks to interoperate with

Streams:offer never ending service
  Spark: microbatch, SQL support
  Kafka streams (check??): simple etl, handles stateful streaming opn
  Flink: low latency, high volume, data flows, handles out of order data
  akka: 

ML:
    Spark ML, tensorflow, skymind for deep learning 
Monitoring:
    opsclarity (great support for kafka and spark)
    Lightbend provides hosted services to monitor and manage cluster

Mesosphere DC/OS on premise or cloud

 


















