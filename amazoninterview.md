#### Leadership principles
* customer first
  We all have learnt a lot from Steve Jobs-
  You’ve got to start with the customer experience and work back towards the technology.
  —Steve Jobs
  Ex: Keep in mind triple constraint of customer project- Cost, Schedule, and Scope 
* take ownership
  Language agnostic world - well almost!! 
  Good example is microservices - approach a complex problem in small manageable independent chunks.
  Java EE (monolithic with generally strong coupling between components/services, deployed like EAR/jar as single running instance - very fragile.)
* encourage innovation
  Microservices with isolation possible using virtualization, docker - resiliency, scalability, CD.
  Discreet and isolated sub-systems communicating over well defined protocolsi(asynchronous, share nothing).
* good judgement and instinct
   Ex: My PM b/g guides me to pay attention to trade offs
   whether convincing the team to agree to iterative solution approach or to remind them of project goals.h
* always a learner

* develop the talent in team
  80-20 principle applies to every team. But I also recognize that all 4 personality type have a place in team- leaders, followers, entertainers.
Aanalytical, driver, amiable, and expressive

  `Ex: Many members of the team are lost without a clear requirement or constantly changing requirement.  Using R&D and continuously build a deployable solution and seek feedback I found is a great approacha`
  R&D and 
* high standard/quality product
  Stress on automation, knowledge sharing, brinstormin
  
* think big, create bold direction
  `Ex: Moving from traditional/monlithic to 100s of microservices is hard to grasp.`

* bias for acion (try - as most actions are reversible) - calculated risk taking

* frugality

* earn trust: listen, respect

* Dive deep: detail, audit, metrics 

* disagree and commit: challege decision but commit once decision is made
  `Ex: These days in a language agnostic developemt you would expect one to be given levay to make use of whatever one chooses to get the work done.  One team member was insisting that eveyone uses scala coz of common libraries - some new folks had liking for python.`
Solu: how we can integrate and learn new techniques liek microservices... 

* deliver results: despite setback never settle  

I have yet to be on a project where everyone agrees about everything. 
Also, have to make sure the ideas are invited from everyone without putting anyone on spot. Remember personality type and adjust your communication. Also satisfy  technical folks ego.

Carefully suggest without being condescending.

Have good judgement and instinct to divert conversation accordingly.
`Ex: focus on common goals.  Consesus by - its not personal`

_Have vision, strategy, shorter-term prioritization and execution. _
The key is to use data to ‘experiment’ and ‘iterate’. Use data for small, defined and closed experiments without worrying about connecting the dots completely. That way, the data is both immediately useful, and if you do connect the dots completely – it is useful again, making it a double victory.

 -Define the problem clearly; be very specific. Confirm
 -Communicate business value; define solution that has ROI
 -Solve problem in clear and defined time frame.
 -approach - roll out a piece and see some benefits
 -work on increasing benefit incrementally

Vision- data nirvana
Strategy - short term prioritization and execution. Experiment and iterate (small and defined iteration) 

Let data speak.

Frivolous work or unachievable deadlines affects the team morale.

One time business asked for some feature that dev team thought was frivlous stuff but it turned out to be a differntiating feature that customers really liked.  Often stakeholders are right and know the market pulse.
When solving problems get the notes, research, data and come up with solution that fit client first then all others.
 
Don't let ego get in the way. Don't be defensive if your opinion differ. Show humility. It canbe hard but that's how we grow from good listener to great.

Get in the head and hearts of the clients/stakeholders. Walk a mile in their shoes. Discern business problems and see if the solution helps them accomplish their goals.

Unified focus, common mission helps build team. Empathy. Make allies. Build relationships of trust and accountability. 


#### open amazon account
Go to aws.amazon.com and create an account and understand how the platform and the console come together and how things work. 

#### 
[APN Partner](https://aws.amazon.com/partners/)
As APN Partner, you will receive business, technical, sales, and marketing resources to help enable you to grow your AWS based business and better support your customers. 

APN Consulting partners:professional services firms - Value Adder Resellers(VAR), Managed Service Providers (MSP)
Ex: 8kMiles, Aquilent, 2ndWatch, DataPipe, CloudReach

APN Technology partner: have solution hosted on AWS platform
Ex: Acquia, Appian, Alert Logic, Dynatrace, Infor, Splunk(opn data analytics - logs), Sumologic



#### check blogs
* Starting Jan 30 workspace have SSD storage at no cost - better performance
* MXNet deep learning engine now an apache project Jan 30, 2017. Recommendation modeling 
* Amazon Cloud Directory - hierarchial 


#### [Watch videos on architecture](https://aws.amazon.com/this-is-my-architecture/)
* ESEYE 
IITservice - cloudwatch - lambda - creates id - eseye(service provider - inserted into cellular devices) --> IITService

* IBM 
Hypervisor (only changed blocks ie incrimental) -- policy management to create pool, dedup, compression, encrypt --> IBM storage pool, optimize for s3 by combining small chunks to 1GB container, 100 MB part  --> s3
For read from s3 - range reads in parallel,assemble, decompress and decrypt

* Financial Times
Cloud Health --> Lambda -JSON (cost by team) -> Stream <--- Dashboard (spent by Service, $left)

* IronSource Atom: Shimon Tolts
Clients use SDK of Ironsource and make a request to Route53

Route53 --Events Json backed by ELB/EC2 -> Kinesis in a single region(use of protobuf for compression -> Workers (do transformation and enrichment using KCL in python, workers put state in Redis and are stateless)--> SQS(knows data to insert ) --> inserters(lookup s3) --> RedShift
			     Workers put data in-->s3 
 
Use of spot instance(to create model)
Use of Dynamo where workers checkpoint

* Coinbase: Rob Witoff: Security
Geo Engineer -- > open source tool
Resource based access, IAM based staff access
Dev/Prod determine rough policy on dev to make the team more productive.
For select group of staff - finely tailored policy that allows them to change things in prod, he descopes some of the privilges from dev as you take things to prod
Use cloud trail (review actions being taken by staff) --> logs on s3-> lambda--> kinesis

* Netflix: Cobourn Watson: Multi region resiliency
Route53(support of many alias record)
Geo routing
ELB
In DR - services running in differnt region can take the traffic over from different region

* Talend: Elastic data integration
External Real-time data feeds ---------> internet g/w -------->Router --- Tier1
Internal Real-time data from ERP etc --> AWS direct connect -->Router

Tier1 -- 2xEC2 cleansing  -- > S3 (& 2 SQS)

Tier2 (2xEC2 for real-time and bulk)  --> target (aurora for opn reporting Vs Redshift that is for historial reports)
2 Aurora One master and other replica. User hit replica through Route53

[See architecture diagram](on desktop)

* Palo Alto Networks : Autoscaling VM : Warby Warburton
ELB and auto scaling
Adding firewall to architecture - Webservers in two AZ with ELB 
ELB and Firewalls to be in independent scaling model
Lambda function to monitor cloudwatch metrics and Firewall to determine to scale or bring down  
Cloudwatch metrics  

* Sumo Logic: Stefan:  Massive logs 

Collectors --> ELB --> Receivers --> S3(server side encryption)
		       Receivers --> locator to Kafka --> Forge (parses, filters, accounting by customer) -->  kafka --> CQ (continuos query feeding realtime dashboard and the snapshot of dashboard is stored on S3)

Forge--> S3
Forge--> Dynamo DB to keep a count by customer. Dynamo has ability to do dynamic add by customer used for billing

Kafka --> Indexer 

Amazon DynamoDB's atomic counter feature to meter usage so that they can accurately charge their customers.

*  Check Point Software Technologies:Gregory : Auto Scaling Security
EC2: M4 large, XL - more core more performace
2 AZ each with 2 EC2s one on private subnet and one public
ELB for load balancing
Active-Active
Horizontal as well vertical scalability depending on customer on L or XL


* Lyft: Micro Services & Discovery: Chris
over 150 microservices over 30 groups, over 200 deployments in a day
over million request per sec
Phone -> proxy service S1 (running on EC2 cluster) --> Discovery service (interacts with Dynamo) -->

challanges- service delivery
S1 - keeps a cache of every service every 1 min by polling Discovery Service asynchornously
DS(in python) - Discovery Service keeps the state of every service in Dynamo DB
Choice of Dynamo DB- reliable, scalable, scale it by increasing readIOPS and writeIOPS makes it really simple

* Sophos: Inbound & Outbound Filtering/inspection: Rich
IGW
ELB
UTM controller - scales in 2 AZ in EC2 instances M3 but customer can change that
S3(configurantion changes kept here that replicates to other AZ) notified to workersthrough SNS

UTM controller with policies controls the workers that are clone of UTM with policy. 
With Cloud formation template - clients control the instance type

Outbound-
Internal ELB
OGW (outbound gateway)
GRD tunnels to workers where traffic is inspected and allowed or denied


* Ignited2k
Predictive analytics and lead generation by integrating consumer data from various sources
Ingest and process massive amount of unstructured data. 
Enrich -  topic, filter, ip to location, Ex fullcontact, location
Merge with structured data (from database usa - demography of all adults in US )
Enrich - sentiment 
REST using python Flask



Data from different sources - batch and real time 
Near real time insight 
Enrichment-
geo conversion: ip to location, id/location to name  (using Ex: fullcontact)
name, location to other attributes(demography, family size, finacial data, interest etc.)
text to topic and sentiment(model: topic modeling using LDA and sentiment using bag of words and Stanford NLP)
 
use of s3 to stored data, IA and archive
use of EC2 for running kafka 
parallel workflow for low latency  


To move, to breathe, to fly, to float,
To gain all while you give,
To roam the roads of lands remote,
To travel is to live.
—H.C. Andersen

The greatest obstacle to discovery is not ignorance—it is the illu‐
sion of knowledge.
—Daniel J. Boorstin

Silence is not only golden, it is seldom misquoted.
—Bob Monkhouse

#### cloud adoption framework
[whitepaper on delivery methodology](http://d0.awsstatic.com/whitepapers/aws_cloud_adoption_framework.pdf) 

Moving to cloud can be a radical shift for org running many systems. Mapping journey to the cloud. 

Steps:
Business case
Once the value add is understood financial(cap ex Vs op ex, pay as you go) as well as strategic(innovative) next evaluate-

Cloud maturity and readiness assessment-
 * cloud readiness assessments - infrastructure, s/w applications and data. Also, existing goveranance, risk mgmt, compliance.
 * Cloud maturity heat map-provide summary of info gathered, analysis and recommendations. Determine high level prioritization, cost and organizational impact. Agree on target cloud platform, iterative implementation towards strategic goals. Each organization wih existing IT capabilities need to have raodmap towards cloud evolution - service to be leveraged or replaced.
 * Application portfolio
 * Roadmap - sequencing & dependencies
 * IT management and processes- not, assess, recommend

Roadmap created after current state assessment and desired target state.  Provide guidance to each functional area on change mgmt to existing practices towards journey to cloud.

1) Staffing perspective ex: devops, training 
Current org structure(with roles, job description, Skills, training) - Target org structure
Recommendation on org change management strategy

2) Process perspective: processes to be in place - plan, implement and operate cloud based IT env
List activities across IT lifecycle. Adopt agile, CD/CI, automation practices
Components : 
  Portfolio mgmt
  Service delivery mgmt: SLA and OLA(op level agreements)
  Program and project mgmt
  CD/CI - automated build and testing
  Process automation - Infrastructure as a code for accuracy and reduce cost
  Quality of IT processes and procedures as well

3) Operations perspective: 
 IT operation to align with business operation
 Tasks: list current operating procedures, identify target procedures, note process changes, plan for change management
 service mgmt through automation
 SLA/OLA mgmt- note and formate policies for meeting, monitoring and reviewing metrics.
 Business continuity planning- DR plan(look at ITIL for guidance)
 Incident and problem mgmt
 Change and configuration mgmt
 Performance and Operation health

4) Security Perspective
Audit, control, agility
 Directive or compliance
 Preventative- mitigate threats and vulnerabilities
 Detective - visibility
 Responsive - remediation from potential deviation


#### Architecting and designing optimum solu. 
    Design architecture: business vision, goals & objectives, conceptual and logical (functional) design diagrams.  Used to communicate architecture to business-focused stakeholders.
    Implementation architecture: physical views within IT system and specific implementation com[ponents and their relationships. Used primarily by technical stakeholders. 

### AWS Enterprise accelerator
Professional services offered by AWS to enterprise customers to rapidly achieve key capabilities or meet specific objective ex: value analysis, compliance jumpstart (HIPAA, MPAA,...), SAP architecture, application optimizationi etc.


#### IgniteD2k
First on cloud till hardware was procured (used from DELL) then containing cost became important. 

Once product was ready and we wanted to benchmark and make it available to first customer - we deployed on cloud as we ran out of resources to version the prod and have test and prod and need to secure, persist data and not a team for operation.
Archiving data with simple ttl came in handy.
Initially having the infrastructure in house made sense as cost of AWS were growing - optimal solution seems like to have infrastructure in house which we continued to use it for dev, R&D etc.

Cloud business case
Define governance structure
Aquiring/traing people - devops, cd/ci/automation needs
Define security requirements
Audit- cost analysis





The speed of development and deployment in the cloud
encourages an iterative approach. Deliver functionality incrementally and catch and fix defects early.

##### Splunk Vs ELK
Questions to ask-
On-prem vs cloud vs managed solutions
Daily GBs consumed and required data retention (Main pricing component)
Which and how many services you’d like to connect
Users, are they all developers?
Specific use cases
Rate of expected changes to your dashboards

Look at the overall TCO (total cost of ownership)

* Data ingest:
Logstash - Cons- long startup time, hard to debug, non-standard configuration lanaguage.
Packetbeat - alternative
ELK requires you to identify fields before its shipped to es

Splunk- out of the box support with preconfigured setting for any kind of data source.
With splunk you identify the data after it is in teh system.
* Security:
User management features - through Elastic security (Shield) or Managed ELK.
Splunk is a paid product on prem, or cloud and security is a part.

* Visualization
Splunk - has added feature for in-dashboard control and dashboard access management.  Exporting to PDF is easy. Mobile support.

Kibana - sort is after the data is displayed.  What if you want to limit the data - data dsiplayed is 500 records -- very limitig and not useful for exploration or spontaneous search

Check- InfluxDB written in Go.  Logscape- on prem free, Logsene
Check- For log anlaysis specially error troublshooting use OverOps that provides links to in-depth error analysis.

##### DynamoDB
Fully managed so you dont worry about operation or provision - just turn it on
Document db or k-v store
Robust
Horizontal scaling- data is growing and horizontal scaling is important
Fast and consistence - low latency
Access control - v granular
Design consideration: sharding, time series data, txn operation
 Partition key(customer id) + Sort key(order id)

Partition key- for equality query only. Hashed to created a randomized key space.
Sort key - for range query.
Hot key - the work load goes to one nodes

AWS Scaling by - thruput(read and write capacity) and size
Thruput - provisioned at table level
By default it is eventual consistent ie read from fisrt node. This can be turned off to achive consistency

Number of partitions
A (By capacity)= reads/3000 + writes/1000
B (By size)= size/10GB
#partitions = ceiling(max(A, B))
RCU and WCU evenly distributed to partitions

Burst capacity is given on best effort basis to avoid throttling.

Avoid hot keys: rolling old data, ...or create a table per time period
Increase provisioned throughput.
Calculate RCU and WCU per partition, check if max number of reads and writes can exceed on single partition.
Roll the old data off the table when not needed.

To get the most out of DynamoDB throughput, create tables where partition key element has a large number of distinct values, and values are requested fairly uniformely, as randomly as possible.

Space: access is evenly spread over the key space
Time: requests arrives evenly spaced in time

Aggregations: Simple as materialized views are already there
Hierarchial data in DynamoDB
JSON format 
Remember the design to minimize WC and RC 
If items are relatively small keep the child as one record with parent
If you have large hierarchy go for items as separate records (DynamoDB limits 400KB on item size)
Lambda - can be thought as stored procedure, scales processing independently of DB head node
(contrast this to MondoDB javascript processing for aggregation can slow down DB)
Stream - keeps a durable queue of every operation. Can be used for real time view of the system

Design patterns:
1) Write sharding: shard write-heavy partition keys
If you have high velocity write operations - and few key values (coz reads are aggrations on dense key value) than adding a random value(salting) you can create more  partitions and programmatically at the time of aggreagtion do scatter gather. Adv- makes teh write load horizontally scalable
Trade off read cost for write scalbility
Consider throughput per partition ley

2) Event logging: time series
Static TTL
Create hot table to take all recent data then roll it off to warm table after a period of time.
ie bucket data by time period.
Future table - precreate -keep it already provisioned.

Dynamic TTL Ex: user data
Solu: build TTLIndex ie another table
Table1: Active Tickets Table
   EventID+Timestamp+GSIKey(rand 0-N)+....

Table2: Expired Tickets GSI
   GSIKey+Timestamp

Have a Lambda function for lookup in Table2 by range query on timestamp and quickly know which ones are active. You can use this also to archive the old data from curent table and move to archive table using stream.

3) Product catalog
very imbalaced access patterns. People looking into select number of products.
Solu-
leverage cache for popular items(redis or self managed instance) to reduce table hits.
leverage lambda streams to update cache and maintain cache consistency.

4) Transactions in NoSQL or multiversion concurrency
Use item partitions to manage transactional workflows
Manage versioning across items with metadata
Tag attributes to maintain multiple versions
Code your app to recognize when updates are in progress
Implement app layer error handling and recovery logic
 Important when: transactional writes across items is required

5) Messaging app: large and small attributes mixed
MessageTable: Receipient+Date+Sender+message
GSI: Sender+Date
Cost: 50(items evalated by query) * 256KB(average item size) * (1RCU/4KB) (conersion ratio) * (1/2) (eventual consistent reads) = 1600 RCU

Solu: vertical partition to avoid reading the complete message body ie create another table
Table: MsgID+Body

Tip: Distribute large items
Reduce one-to-many item sizes
Configure secondary index projections
Use GSI to model M:N relationship between sender and recipient

Important when: querying many large items at once but who don;t need to know the details but only descriptors 

6) Needle in the haystack Ex: multiplayer online gaming 
if the use case requires reading and filtering large data create composite key to reduce RCU
Composite key ex: statusDate
converts filter query to range query

7) Sparse index
Create another index to support highly selective queries

Conclusion: depending on access pattern create a GSIIndex, Composite key or split data


####  Security
along with SNS and AWS Lambda for alerting
AWS Management Console-

IAM Console-
IAM - role
Policy
Lambda Console-

Cloudwatch Console-

##### Kinesis
splitting and merging of stream

##### Lambda 

##### hybrid architecture



