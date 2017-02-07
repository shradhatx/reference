### Tell me about yourself.
I am a IT professional passionate about technology and learning.  I thoroughly enjoy my work. I help my clients create products and solve business problems using IT. 

In big data space, I specialize in solution architecture,  I also develop to solve specific business problem mainly in ES, NoSQL(cassandra, Hbase), PoC in many other open source trending technologies.

I like to be engaged in big picture, involved in end to end solution and ROI.

I have worked in Enterprise IT -  as a developer, technical PM, and  managed high performance teams in software dev, DW merger, tech refresh, vendor management, data center setup in a highly compliant financial banks. 

I have coded in many languages C/Java/Oracle and extensively worked on Oracle Database PL/SQL.

I have also worked in-  Telecom, Healthcare, Insurance sectors
I have my education in CS and Electrical Engineering 

Goal - technical consulting role as big data solution architect leveraging open source technologies and cloud computing (virtual/hybrid/server-less infrastructure).    Also interesting to me is a senior developer/lead in Elastic/ELK.


#### Tell us about the architecture of your most interesting project

Intersys Consulting: to solve sticky business problem leveraging ES (boundary by customer) coz the prob  seems to do with search
* Elastic consultant on Federation of State Medical Board projects.  
Perform exploratory and feasibility analysis of client’s data and processes and provide technology roadmap and iterative solution architecture with focus on Elastic 5.x with a goal to improve data quality and operation efficiency.

PoT and PoC - demo capabilities of ES with FSMB data sample.  Propose overall solution, iterative roadmap, work breakdown and estimates. Two of the projects I worked on at FSMB-

i) FSMB gets board orders in PDF from different States (court order stating if the physician is reprimanded, barred from performing certain procedures, or cleared of prior accusation or license reinstated). Over 250 categories.
These are reviewed manually and categorize choosing from over 250 categories. FSMB wants to learn about automating the process utilizing text search, NLP and categorization. They have Elastic stack and wanted to explore the capabilities in Elasticserach first.    
Architecture: 
Ingest pdf using ingest pipeline -convertng pdf to base64 
Use of percolator to identify the basis and order
Userinterface to review the top 3-5 top scored categories before persisting.
Off course queue to keep requests, user access etc.


ii) FSMB maintains a database of ~1.4M on Physicians. Certains updates on physicians are available monthly from another source in a csv file with over 300 fields and ~ 6M records.  There is no common identifier.  FSMB wanted a solution to efficiently match these Millions of records with FSMB database first comparing many fields to correctly identify the physician and then noting changes and updating FSMB record.
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


#### IgnitedD2K:  most interesting coz -lots of parts, also coz of my end-to-end involvement 
* Team was building a product that leveraged consumer data from various sources, structured/unstructured data, to predict good quality leads for prospective clients.
* Enrich -  filter, topic, ip to location,handle/id to name and location, sentiment, best way to contact etc. while building consumer database
* Merge with structured data (demography on consumer collected from database usa - all adults in US )
* REST using python Flask
* Dashboard using angular.js

> Used AWS EC2 M4-L, XL, S3, Spark, Nosql technologies. 
> Data analysis - using python, panda, scikit-learn, numpy etc.  (Also -used Random Forest Ensemble,SVM in Kaggle)
> Built data pipeline 
> Participated in backend data architecture and APIs
> Topic modeling using LDA. wordtovec - singular word, doctovec  

##### Architecture:
* Data pipeline evolved over time
* Ingest from 100s of website (twitter OAuth - geo boundaries, keywords list; local radio sites, newspaper sites, business websites
* Initial filters were applied and metadata attached and data was parked in kafka; 
* Spark Cleaners - transform data and put in another topic 
* Models were built (LDA) periodically and the model results were kept in s3.
* Spark Movers- read data from kafka, apply model get topic, sentiment, get demography and land data into HBase.

* Demography data was bought from 3rd parties - Loaded into ES index (alll adults in US from DB USA 30 million).

* Separate process reading social handle, name, location from kafka, hit ES dbusa data & match with dbusaID and put in cache ---> also in Hbase dbusaid, social_handles

* Third party like fullcontact used to get info by handle -  name, location -- hit ES dbusa & match with dbusaID.

* Periodic update of HBase from cache (cache with different handles -fb, twitter,...)


#### Questions
Q: How to write LRU in scala, db connector

DB connector: service that acts as a gateway to corporate DB.



Q: In ML what is precision, sensitivity, recall?
Sensitivity(also Recall) - measurement of performance of a model - ex classification test. Percentage of positives that are correctly identified
Specificity - percentage of negatives that are correctly identified as negative.
 
Recall = TP/(TP+FN)
Precision = TP/(TP+FP) 

Ex: Total case 10,000
TP: case was positive and predicted positive
TN: case was negative and predicted negative
FP: case was negative but predicted positive
FN: case was positive but predicted negative



               | Predicted Negative | Predicted Positive
---------------|------------|---------------|
Negative Cases | TN: 9,760  | FP: 140 |
---------------|------------|---------------|
Positive Cases | FN: 40     | TP: 60        |


Percentage prediction correct: 'accuracy' was 9,760+60 out of 10,000 = 98.2%

What percentage of the positive did you catch: 'recall' was 60 out of 100 = 60%

What percentage of positive predictions were correct: 'precision' was 60 out of 200 = 30%


#### Personal Strength
 > Motivated to do the best, be in touch with the business goals while deep diving in technology- problem solving .
 > I know the process(that is proven) to deliver high quality solu and meet customer goals and team focused(accountability & assertive)
 > build agreement understanding the tradeoffs
 > Listen & involve all - There are so many technologies and alternate ways to do things. See you are not missing out input. Understand personality types. 
 > Have emotional maturity.


Leadership skills
* customer first
  We all have learnt a lot from Steve Jobs-
  You’ve got to start with the customer experience and work back towards the technology.
  —Steve Jobs
  Ex: Keep in mind triple constraint of customer project- Cost, Schedule, and Scope
* good judgement and instinct
   Ex: My PM b/g guides me to pay attention to trade offs
   whether convincing the team to agree to iterative solution approach or to remind them of project goals.h
* develop the talent in team
  80-20 principle applies to every team. But I also recognize that all 4 personality type have a place in team- leaders, followers, entertainers.
Aanalytical, driver, amiable, and expressive

  `Ex: Many members of the team are lost without a clear requirement or constantly changing requirement.  Using R&D and continuously build a deployable solution and seek feedback I found is a great approacha`
  R&D and
* take ownership
  Language agnostic world - well almost!!
  Good example is microservices - approach a complex problem in small manageable independent chunks.
  Java EE (monolithic with generally strong coupling between components/services, deployed like EAR/jar as single running instance - very fragile.)


#### Weakness
As a developer - challenge to say no to work request -inundated with continuously changing demands. 
As a PM Learned the importance to prioritize intake - team or people work best when targets are achievable 
Negotiation explaining the tradeoffs works well
Now I value pragmatism over perfection.

#### Questions to ask interviewer

> Q: is there anything i can tell you about me that would help you make a hiring decision?
> Q: tell me about your process of customer engagement? 
> Q: tell me about yourprocess of implementation & delivery strategy?
> Q: Is the solution hosted on cloud or on-premise?
> Q: Technologies you are using - real time spark /storm /impala
> Q: If I were hired what would my project look like? what is your (or scope & life) most recent project (that is completed) about?
> Q: what are the skills/qualities that you desire in the person you hire?
> Q: what are the imminent challenges in the project/team?
> Q: Can you describe my role and responsibilities if hired?

* Requirements
Understand requirements - data volume, access patterns, security and compliance, performance, archival, access patterns, consistency, availability, tolerance for downtime. 

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


Titan: supported by backend store Cassandra, Gremlin
GraphX: spark - in memory processing, Graph QL, Data can be viewed as collection or graph, 











