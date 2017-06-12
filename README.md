### Tell me about yourself.
I am a IT professional passionate about technology and keeping myself relevant helpng my clients create products and solve business problems through innovation and deep knowledge in  IT 

Held variety of roles over many years in IT
- contributed in technical design and s/w development, used  many languages
- managed IT teams
- managed programs and projects of many flavors. experienced in project and organizational leadership
- held customer facing responsibilities and vendor management as well

Last 4 years I have been in big data spaces-
2 years with a startup worked as a consultant and developer
- predictive analysis and lead generation by integrating consumer data, ingest, merge, enrich, apply ML model, store, viz
In big data space, I specialize in end to end solution architecture.  
I also can deep dive and develop to solve specific business problem in ELK, Spark, NoSQL(cassandra, Hbase) 
I have developed PoC in many other open source trending technologies.

I have worked in-  Telecom, Healthcare, Banks, Insurance sectors
I have my education in CS and Electrical Engineering 

Goal - technical consulting role in big data space, Keepng relevant, staying in touch with technology trends and would love to further expand my experience in selected areas like  Elastic/ELK,  ML.
Bank - Mortgage default  & Insurance - fraud detection(HMS keeps the eligibility database from various providers and federal govt)
Kaggle projects
Loan data - finding the likelihood of default (algorithms: Regression, RF, fine tuning: Gradient descent)
Allstate purchase prediction - Logistic Regression, RF and then GB 
Otto group - classify products into coreect category WWinner: using NN, AdaBoosting
Forecasting sales-Integrating feature extraction method and prediction tool, such as support vector regression (SVR). Independent component analysis (ICA) is a feature extraction technique- spatial ICA (sICA), temporal ICA (tICA)


* Presently working as a consultant (depends on what is asked and the maturity of clients in big data)
- solving sticky problem related to ES, cognitive search, intranet search
- comfortable in many roles (can wear multiple hats - strategy for big data or cloud, roadmap, WBS, dev or delivery)

#### Personal Strength
* comfortable in many roles (can wear multiple hats - strategy for big data or cloud, roadmap, WBS, dev or delivery)
* take ownership
  Language agnostic world - well almost!!
* Motivated to do the best, be in touch with the business goals while deep diving in technology- problem solving .
 > build agreement -  thru understanding & communicating the tradeoffs
 > Listen & involve all - There are so many technologies and alternate ways to do things. See you are not missing out input. Understand personality types.
* multiple business knowledge and technologies(telecom, bank, insurance, services - big and startups)

#### Weakness
As a developer - challenge to say no to work request -inundated with continuously changing demands. Important to prioritize intake - team or people work best when targets are achievable
As a PM be aware and communicate the tradeoffs - let stakeholders make that decision 
I now negotiate better -  explaining the tradeoffs works well.
Now I value pragmatism over perfection.
Unified focus, common mission, achievable targets helps build team. I build relationships of trust and accountability.

#### What would your manager or peer say about you
> Manager: I can ablsolutely rely on her for any type of work - technical, logistics, managerial, building consensus.
Very methodical.

> Peer: friend/helpful,trustworthy,humble and respectful of others. She involves everyone or at least makes sure that everyone opinion is heard.

>critique/ or things you can improve on: Very methodical - slow down as sometimes it takes a while to build trust or people to open up. Not everyone wants to go at 200miles/hr

#### IgnitedD2K:  most interesting coz -lots of parts, also coz of my end-to-end involvement 
At Ignited2k- Predictive analytics and lead generation by integrating consumer data
- predictive analysis and lead generation by integrating consumer data, ingest, merge, enrich, apply ML model, store, viz
* Enrich -  filter, topic, ip to location, handle/id to name and location, sentiment, best way to contact etc. while building consumer database
* Merge with structured data (demography on consumer collected from database usa - all adults in US )
* Data pipeline evolved over time
* Building blocks: python for ingestion, Kafka, Spark, HBase/Cassanra, REST apis(python flask), kibana & angular.js

> Used AWS EC2 M4-L, XL, S3, VPC - IAM, Route53, LB, subnets, (Lambda,SQS,RDS, DynamoDB, Redshift)
> Data analysis - using python, panda, scikit-learn, numpy etc.  (Also -used Random Forest Ensemble,SVM in Kaggle)
> Built data pipeline 
> Participated in backend data architecture and APIs
> Topic modeling using LDA building vocabulary. wordtovec - singular word, doctovec  

> Architecture: players data ingest- kafka - filtering & normalisation - Spark - ML & enrichment, transformation - join - business logic - NoSQL Store - Elastic - visualization
* Data pipeline evolved over time
* Ingest from 100s of website (twitter OAuth - geo boundaries, keywords list; radio sites, newspaper sites, business websites and parked it on kafka
* Initial filters were applied and metadata attached and inormalised data was put in kafka; 
* Spark Cleaners - transform data and put in another topic 
* Models were built (Topic extraction using LDA) periodically and the model results were kept in s3.
* Spark Movers- read data from kafka, apply model get topic, sentiment, get demography and land data into HBase.

* Demography data was bought from 3rd parties - Loaded into ES index (all adults in US from DB USA 50 million).

* Separate process reading social handle, name, location from kafka, hit ES dbusa data & match with dbusaID and put in cache ---> also in Hbase dbusaid, social_handles

* Third party like fullcontact used to get info by handle -  name, location -- hit ES dbusa & match with dbusaID.

* Periodic update of HBase from cache (cache with different handles -fb, twitter,...)

#### Tell us about current project 
Intersys Consulting: to solve sticky business problem leveraging ES (boundary by customer) coz the prob  seems to do with search
1. problem with categorizing PDF documents- semanting understanding of content (roadmap of application around it- with ingestion, Queues, GUI)
2. problem of updating the db of million of physicians with weekly/monthly updates with no common criteria for matching
 Used multiple compound maching key and scoring to present a subset of possible matches for review
3. intranet search capability

* Elastic consultant on Federation of State Medical Board projects:
Perform exploratory and feasibility analysis of client’s data and processes and provide technology roadmap and iterative solution architecture with focus on Elastic 5.x with a goal to improve data quality and operation efficiency.

PoT and PoC - demo solution/capabilities of ES with FSMB problem and data sample.  Propose overall solution, iterative roadmap, work breakdown and estimates. Two of the projects I worked on at FSMB-

i) FSMB gets board orders in PDF from different States (court order stating if the physician is reprimanded, barred from performing certain procedures, or cleared of prior accusation or license reinstated). 
These are reviewed manually and categorize choosing from over 250 categories. FSMB wants to learn about automating the process utilizing text search, NLP and categorization. They have Elastic stack and wanted to explore the capabilities in Elasticserach first.    

Architecture: 
Ingest pdf using ingest pipeline -convertng pdf to base64 
Use of percolator to identify the basis and order
Interactive user interface to review the top 3-5 top scored categories before persisting.
Off course queue to keep requests, setup security, user access etc.
Adv: reduces time and accuracy.   

ii) FSMB maintains a database of ~1.4M on Physicians. Certain updates on physicians are available monthly from another source in a csv file with over 300 fields and ~ 6M records.  There is no common identifier.  FSMB wanted a solution to efficiently match these Millions of records with FSMB database first comparing many fields to correctly identify the physician and then noting changes and updating FSMB record.
Architectue:
Ingestion of monthly file in ES
Bulk query request for matching
User Interface to review categorized results and persist
Interface with existing company db 
Improve matching algorithm - manual retry or running multiple queries in the background

* Elastic consultant on IMF project to propose an intranet search solution(intranet that provide internet like search experience- auto classification)  and demo a PoC that replaces Microsoft FAST leveraging Elasticsearch that works seamlessly with Sharepoint, company websites and AD. Support for various documet format - xml, doc, db, many languages, images, emails, relevant search results, semantic understanding of content and query, NLP, Interactive, 
BAInsight, SearchBlox, Sinequa
Crawl company web site and Sharepoint, tag metadata and index (categorization - person, location, org)
Import user access
Integration with db/mail etc
Cognitive 

#### tell us about your customer facing responsibility or project
FSMB - Federation of State Medical Board 
It was to do with the exploratory and feasibility analysis of data and processes for new capabilities exposed by big data crunching. JAD session to gather current process , transform them to user stories and value they are looking to achieve.  Formulation roadmap and iterative delivery.

The user story describes the type of user, what they want and why.
As a <type of user>, I want <some goal> so that <some reason>

CHASE- as PM - DWH project with distributed x-functional team - managing requirements of multiple business org

#### vendor management experience
CHASE- big dependencies on third party softwares ex: collections etc are part of the fabric of overall solution in mortgage. Managing iand coordinating the s/w change, delivery from vendor with internal releases.

Managing vendor risk is a continuous process - evaluate,track,and measure third party risk. Communicate what you learn to assess the organization wide impact.
Ongoing oversight(proactive idententify risks, verify compliance); aligning vendor control environment with yours; have strategy(contingency plan) for addressing higher-risk vendor;
Four categories of risks: strategic, reputation, compliance, operational (including information security) and business continuity.

#### Questions to ask interviewer
> Q: tell me about the flavor of projects that your team gets (technology or business trends seeing the demand of your global fortune 500 clients)
> Q: can you describe the company culture - siloed or collaborative
> Q: tell me about the team makeup
> Q: Can you describe typical responsibilities if hired?
> Q: what are the skills/qualities that you desire in the person you hire?
> Q: Is there a preferred technology stack - for whatever reason you recommend???


> Q: Technologies you are using - real time spark /storm /impala
> Q: tell me about your process of project success evaluation ? 
> Q: where do you want to take the company/team/architecture moving forward? What's their vision.
> Q: is there anything else i can tell you about me that would help you make a hiring decision?
> Q: tell me about your process of implementation & delivery strategy
> Q: Is the solution hosted on cloud or on-premise?
> Q: If I were hired what would my project look like? what is your (or scope & life) most recent project (that is completed) about?
> Q: what are the imminent challenges in the project/team?

#### Requirements
Understand requirements - data volume, access patterns, security and compliance, performance, archival, consistency, availability, tolerance for downtime. 

Understand tradeoffs on solutions presented. Tradeoffs on CAP are real and these are business decisions not engineering.

Infrastructure: Evolve: on-premise/virtual/hybrid
Datastore: Keep it simple, understand flexibility CAP- pros for your use-case
Application: synchronous or Asynchronous


#### Technical reference
* [elkref] - all about elastic stack
* [sparkref] - Spark
* [nosqlref] - Cassandra, HBase, MongoDB


#### Tips

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


#### Graph comparison
Graph for recommendation, page ranking/credibility of article
Cons: hard to distribute - works well for a single node /replicated, keep the footprint of edge small. Edge compression is useful
Use of significant terms and samples for recommendations say of moviews.

Titan: supported by backend store Cassandra, Gremlin
GraphX: spark - in memory processing, Graph QL, Data can be viewed as collection or graph, 


### Hadoop
Archtecture
NameNode - Task Trackers
DataNode - Job Tracker 

### NoSQL Comparison: CAT theorem
HBase- Highly consistent
Caasandra - more available, tunable consistency
MongoDB - more consistent, rich query support, location aware. Driver->Query Router

HBase - HMaster -- N RegionServers --> HLog, MemStore, HFile -pointer to datanodes
Client --> Zookeeper <--- HMaster 


Cassandra - Client writes to Coordinator node that replicates to other nodes sends ack to client after n nodes have the data

Hortonworks Vs Cloudera









