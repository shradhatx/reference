### Introduction
My name is Shradha Bhalla, I am an IT professional leveraging  my 20+ years of experience in innovating, developing, and deploying technology solutions to help clients create products and solve business problems. Specializing in hands-on architecture, proofs of concept, and development of open source, big data systems using trending hadoop and spark technologies. 

I have varied experience in Enterprise IT - managed high performance teams bad high risk projects in software dev, infrastructure upgrade, tech refresh etc.

Business verticals - Banking, Telecom, Healthcare, Services - Insurance
Education background- EE and Masters in CS

Goal - technical consulting role as big data solution architect leveraging open source technologies and virtual/hybrid/server-less infrastructure.    Or a senior developer/lead in Elastic/ELK.


#### Intersys Consulting 
* Elastic consultant on Federation of State Medical Board projects.  
Perform exploratory and feasibility analysis of client’s data and processes and provide technology roadmap and iterative solution architecture with focus on Elastic 5.x with a goal to improve data quality and operation efficiency.

PoT and PoC - demo capabilities of ES with FSMB data sample.  Propose overall solution, iterative roadmap, work breakdown and estimates. Two of teh projects I worked on at FSMB-


i) FSMB gets board orders in PDF from different States. These are reviewed manually and categorize choosing from over 250 categories. FSMB wants to learn about automating the process utilizing text search, NLP and categorization. They have Elastic stack and wanted to explore the capabilities in Elasticserach first.    

ii) FSMB maintains a database of ~1.4M on Physicians. Certains updates on physicians are available monthly from another source in a csv file with over 300 fields and ~ 6M records.  There is no common identifier.  FSMB wanted a solution to efficiently match these Millions of records with FSMB database first comparing many fields to correctly identify the physician and then noting changes and updating FSMB record.


* Elastic consultant on IMF project to propose an intranet search solution and demo PoC that replaces Microsoft FAST leveraging Elasticsearch that works seamlessly with Sharepoint, company websites and AD. 

#### IgnitedD2K  
Built a product that leveraged social data, and other structured/unstructured data collected from many different sources to predict useful metrics for clients.
* Built end-to-end data pipeline 
* Participated in backend data architecture and APIs
* Data analysis and modeling- using python, panda, scikit-learn, numpy etc.  (Also -used Random Forest Ensemble,SVM in Kaggle)
* Topic modeling using LDA. wordtovec - singular word, doctovec  

> Used Hadoop/HDFS, AWS EC2, S3, Spark, Nosql technologies. 
> Created data pipelines in spark/kafka calling ML model and business logic on the way and landing data on NoSQL/Hbase or Elastic or Dashboard (angular.js)


#### Worked as Program Manager/ Technical PM on several projects of many flavors in Bank, Telecom, Healthcare Services etc.

#### Worked in roles such as C/Java/Database programmer, team lead and Dev Mgr.

#### Education background-Electrical Engineer & Computer Science 


#### Questions
Q: How to write LRU in scala, db connector

DB connector: service that acts as a gateway to corporate DB.



Q: In ML what is precision, sensitivity, recall?
Sensitivity(also Recall) - measurement of performance of a model - ex classification test. Percentage of positives that are correctly idetified
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


#### Strength
- Motivated to do the best & deliver high quality solu, I am adaptable, result driven- Always in
touch with the business goals while deep diving in technology- problem solving skills.
- Have emotional maturity.
- Can accomplish your strategic/tactical goals. I can lead and build a high performing technical team.
- There are so many technologies and alternate ways to do things. I am adaptable.
- I have passion for technology and learning. 

#### Weakness
I had as PM - challenge to say no to work request -inundated with continuously changing demands. Learned the importance to prioritize intake - team or people work best when targets are achievable - challenging but possible.
Now value pragmatism over perfection.

#### Questions to ask interviewer

> Q: is there anything i can tell you about me that would help you make a hiring decision?
> Q : tell me about your process of customer engagement and solution implementation & delivery strategy.
> Q: Is the solution hosted on cloud or on-premise?
> Q: Technologies you are using - real time spark /storm /impala
> Q: If I were hired what would my project look like? what is your (or scope & life) most recent project (that is completed) about?
> Q: what are the skills/qualities that you desire in the person you hire?
> Q: what are the imminent challenges in the project/team?
> Q: Can you describe my role and responsibilities if hired?

* Requirements
Understand requirements - performance, security, access, archival, access patterns, consistency, availability, tolerance for downtime for upgrade if updating existing system etc.

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














