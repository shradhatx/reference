#### Leadership principles
* customer first
  You’ve got to start with the customer experience and work back towards the technology.
  —Steve Jobs
* take ownership
  Good example is microservices - approach a complex problem in small manageable independent chunks.
  Java EE (monolithic with generally strong coupling between components/services, deployed like EAR/jar as single running instance - very fragile.)
* encourage innovation
  Microservices with isolation possible using virtualization, docker - resiliency, scalability, CD.
  Discreet and isolated sub-systems communicating over well defined protocolsi(asynchronous, share nothing).
* good judgement and instinct
  Ex: Convincing the team to agree to iterative solution approach.
* always a learner

* develop the talent in team
  Ex: Many members of the team are lost without a clear requirement.  Using R&D and continuously build a deployable solution and seek feedback I found is a great approach
  R&D and 
* high standard/quality product
* think big, create bold direction
  Ex: Moving from traditional/monlithic to 100s of microservices is hard to grasp. 

* bias for acion (try - as most actions are reversible) - calculated risk taking

* frugality

* earn trust: listen, respect

* Dive deep: detail, audit, metrics 

* disagree and commit: challege decision but commit once decision is made
  Ex: These days in a language agnostic developemt you would expect one to be given levay to make use of whatever one chooses to get the work done.  One team member was insisting that eveyone uses scala coz of common libraries - some new folks had liking for python.
Solu: how we can integrate and learn new techniques liek microservices... 

* deliver results: despite setback never settle  

I have yet to be on a project where everyone agrees about everything. 
Lack of humility in one adversely affects the project.
Carefully suggest without being condescending.
Have good judgement and instinct to divert conversation accordingly.
Ex: focus on common goals.  Consesus by - its not personal

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

#### read
whitepaper, http://d0.awsstatic.com/whitepapers/aws_cloud_adoption_framework.pdf it is our Cloud Adoption Framework that is our delivery methodology. 


#### 
Check out the page: https://aws.amazon.com/partners/
As APN Partner, you will receive business, technical, sales, and marketing resources to help enable you to grow your business and better support your customers. 


https://aws.amazon.com/this-is-my-architecture/
#### check blogs
* Starting Jan 30 workspace have SSD storage at no cost - better performance
* MXNet deep learning engine now an apache project Jan 30, 2017. Recommendation modeling 
* Amazon Cloud Directory - hierarchial 






#### [Watch videos on architecture](https://aws.amazon.com/this-is-my-architecture/)
* ESEYE 
IITservice - cloudwatch - lambda - creates id - eseye(service provider - inserted into cellular devices) --> IITService

* IBM 
Hypervisor (only changed blocks ie incremental) -- policy management to create pool, depdup, compression, encrypt --> IBM storage pool, optimize for s3 by combining small chunks to 1GB container, 100 MB part  --> s3
For read from s3 - range reads in parallel,assemble, decompress and decrypt

* Financial Times
Cloud Health --> Lambda -JSON (cost by team) -> Stream <--- Dashboard (spent by Service, $left)

* IronSource Atom: Shimon Tolts
Clients use SDK of Ironsource and make a request to Route53

Route53 --Events Json backed by ELB/EC2 -> Kinesis in a single region(use of protobuf for compression -> Workers (do transformation and enrichment using KCL in python, workers put state in Redis and are stateless)--> SQS(knows data to insert ) --> inserters(lookup s3) --> RedShift
			     Workers put data in-->s3 
 
Use of spot instance(to create model)
Use of Dymnamo where workers checkpoint

* Coinbase: Rob Witoff: Security
Geo Engineer -- > open source tool
Resource based access, IAM based staff access
Dev/Prod determine rough policy on dev to make the tea more productive.
Select group of staff - finely taired policy that allows them to change things in prod, he descopes some of the privilges from dev takigt hings to prod
Use cloud trail (review actions being taken by staff) --> s3-> lambda--> kinesis

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
ELB for laod balancing
Active-Active
Horizontal as well vertical scalabilityi depending on customer on L or XL


* Lyft: Micro Services & Discovery: Chris
over 150 microservices over 30 groups, over 200 deployments in a day
over million request per sec
Phone -> proxy service S1 (running on EC2 cluster) --> Discovery service (intercats with Dynamo) -->

challanges- service delivery
S1 - keeps a cache of every service every 1 min by polling Discovery Service asynchornously
DS(in python) - Discovery Service keeps the state of every service in Dynamo DB
Choice of Dynamo DB- reliable, scalable, scale it using readIOPS and writeIOPS makesit really simple

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
Ingest and process massive amount of unstructured data from social sites
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



To do: 
Architecture diagram Ignited2k
IMF and RAInsight  






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

