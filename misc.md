# Miscellaneous notes 
* Predictive Analytics, Statistical probability, Machine Learning and Artificial Intelligence

Predict on future input based on learning on past input and labels

> Unsupervised learning: learning from the unlabeled data, steps -
 segment data into segments or cluster exhibiting similar behaviors no way to describe cluster or how many cluster it will result into and who will fall into which cluster

> Supervised Learning: when goal is a specific outcome; steps-
 train ML model by labeled data
 predict on new data

What model to use, attributes to include and estimate on predictions?

Statistical Learning

Reinforcement learning 

## Web crawling: Apache Nutch
Nutch 2.x stream has abstraction layer that works with any data store

Scrapy - python crawler

clustering algorith- k-means

classification technique- naive bayes assumes all features are independet of each other in its predictions. Improve by data preprocessing (normalising, missing data) and feature selection.

Confusion matrix - used for evaluation of a classification algorithm
Accuracy of a model = (TP+TN) / (TP+FN+FP+TP)

For imbalanced data set where occurance of event is rare ex-fraud, technique used is - resampling techniques
of training data by pre-processing and having n sample equal number by building up the minority class data
Disadv- overfitting

Boosting - Ada boosting, gradient boosting

Neural network- for image recognition, speech recognition
Most ML algorithms tend of lose accuracy when given a data set with several variables, whereas a deep learning model does wonders in such situations.



## Enterprise search
Searchblox AD integration
https://searchblox.atlassian.net/wiki/plugins/servlet/mobile?contentId=30310416#content/view/30310416

Searchblox document security
http://www.searchblox.com/document-level-security-enterprise-search

Part 1
https://blogs.technet.microsoft.com/heyscriptingguy/2013/10/28/building-a-demo-active-directory-part-1/

Part 2
https://blogs.technet.microsoft.com/heyscriptingguy/2013/10/29/building-a-demo-active-directory-part-2/


1)Azure Details:

Please install Microsoft Remote Desktop from app store for mac and for windows use default Remote Desktop to connect to the Azure Windows 2012 Server.

Credentials and Server Details:

Username:
adminshradha
adminnagy

Password:
Summer2012@!

DNS Name
intersyswin2012r2.cloudapp.net
Administrator
intersyswin2012r2.cloudapp.net\Username
Password
Summer2012@!
Machine
Win 2012 R2
Cores
2
RAM
14 GB
Storage
28 GB SSD
Data Disks
4
End Points
RDP TCP (TCP/3389)
RDP UDP (UDP/3389)
WinRM (TCP/5986)

2)Searchblox Details:

URL to access Searchblox - http://intersyswin2012r2.cloudapp.net/searchblox/admin/main.jsp  

username:admin password:intersys2016



========

>>when an we have an internal demo of - a) okta,  b) integration with imf.org and sharepoint c) UI 


====IMF FAST demo ===
FAST - indexes the doc and sharepoint queries the data
Business rules- slide relevance
Taxonomy built on the rules built in slide relevance.


crawls various sharepoint sites

FAST - clickthrough and FAST
sharepoint contact

SeeUnity connector - 3rd party

==== AD Active Directory for security 
Solution Use case:
- time it takes for full crawl 
- security update (need  to detect changes to avoid full crawl - user add/delete to source system, security change to a folder etc.)
- search performance 
- complexity of security roles/user/hierarchy/preference

* Provides secure, high performance crawling and indexing
* Maps security entitlements with source systems into search
* Ensures results are trimmed to expose only content the user has access to

Sharepoint Connector for ES- 
Early binding document-level security
Optimizing indexing performance ( preferably no write to SharePoint repository)
Comprehensive metadata capture during ingest
Mapping and extraction functions to enhance the search experience and drive features such as faceted search and sorting by property
Federated sharepoint search - for additional content that has not been crawled by SharePoint

** note from Peter about ES and AD 
user_search.base_dn and user_search.scope are used together to define the scope of the AD entries to consider. By default, 'scope' is set to 'sub_tree', which means the entire hierarchy under the base_dn is going to be searched. 

If you have 'unmapped_groups_as_roles: true' set, then all the groups are auto-map to a role in your ES roles definition. You still need to explicitly define an ES role for each AD security group. Otherwise, Elasticsearch doesn't know what permissions that users of a particular AD group should have.
 

******** 

AD users and groups

Crawl content in SP

Content is in documents in ES cannot be secured out of the box 

Search security - customization and configuration goes into this work
Search engine often confused with database

Security is complex - has to honor the person searching and also the content being searched. All these complexities are layered. Certain corpus of people is allowed to see content A from source B and other is not.

AD component is a main component of security and SP leverages that.
Search has to identify security of different sources and bind them.

Many tools- never thought of security but manually crafted security as afterthought as they crawl SP.

Real time security trimming or late binding — very cumbersome and expensive.

Solution: Label a document (ex: metadata) with security at the time you ingest it or early binding. (Query scripting capability needed to enable the manipulation of metadata fields).
This solution has to be independent of AD so it is applicable to all (ex: IBM file net)

BA Insight - writes to AD- individual id or group id (SP small groups expanded to individual Ids)
BA Insight uses custom scripts in ES in visual Basic that may have security hole.

Where are your hinge points - groups and #members distribution.

Group id is written with ES document.

Shield can do an automatic AD group binding to Roles in shield.

Manage the entire security framework ourselves.
Come up with own translation of security to users that imitates AD.  (EX: if we have to do with Shield)
Disadvantage - This puts up another layer.  Joe Smith in AD world, Joe Smith in source system world, Joe Smith in BA Insight security world.  
   - Expensive query time experience (evaluate perf)
   - Ongoing administration cost

Decision based on- corporate policy (ex: no third party allowed to write into AD)

Kerberos token bloat - can cause nightmare


Need an experienced AD administrator  
Need an expert at SharePoint crawler

select a particular ou in a domain and a particular account with confined access to ou.


Story about a customer you are proud of - tying system together - an example of how you solved their problem with specific example

15-20 M searchable items
Multiple source systems, users and groups
Q to ask - how many groups in AD  (concern on replicating this to various servers)
How many groups have more than 1000+/500+/200+ members?
Groups with small number of users can be turned into individual user.

Only need to write to AD as many number of groups.  

How your content is secured right now?
Evaluate existing security and do cleanup before moving to enterprise search.


Test: HR document should not end up in someone else’s search.

Check tech net blog & tech net social 

    https://blogs.technet.microsoft.com/
    https://social.technet.microsoft.com/Forums/en-US/home




Q to ask -
- How is security applied on items - index and items? through automating filter generation at query time
- How easily can you detect security changes? 
- How are users and groups managed?

Search should honor the security of source system.


Groups can be mimicked easily.
Users are more complex - challenge to keep the map up to date constantly and fault tolerant (a way to do that may vary from system to system)

Resolution on cases such as-
- hierarchical security
- denial winning over grant
- security on data types
- multi-level security (ex: user of this group and with clearance of this type)


Running frequent full crawl is expensive and may not be acceptable.

BAInsights
Metrics on bulk crawl = 150 docs/s
SP: 15-20 dps
10M items - full crawl may take multiple days.
1M 1-2 hour of crawl

Multiple full crawl- limitation on SP side.  


## Intersys contacts
melissa Cadwallader
mcadwallader@intersysconsulting.com
Phone: 404-514-9563

Angie Shepard 512.585.9190
Rakesh Soni
Lawren Fendrich
Becky
Roger Wahman

Elastic:
Joe Quick
*Nick Drost

GM:
*Mark Miller
Eric
*Denis 

## FSMB Contacts
Cyndi Streun, SVP Information Systems
Jill Putman, Enterprise Data Manager  Phone:  817-868-5129
Christine Wells, Agile Project Manager
Aaron Young, PhD, Assistant VP, Research & Data Integration

Cyndi Streun (FSMB) <CStreun@fsmb.org>
Aaron Young <ayoung@fsmb.org>
Cheryl Rojo <crojo@fsmb.org>



## Personal Rajasthan trip

March 9  - Start from US & reach Delhi March 11 late evening
              Delhi - Dinner at Cyber Hub Gurgaon (Farzi Cafe)
March 11 -
         Overnight Gurgaon
March 12 - drive to Agra  
           **check if Taj is open on Sunday
           Agra - Agra fort, Taj Mahal during the day and at sunset  
        Dinner - Enjoy Mughlai cuisine, check Oberoi Hotel and restaurant

March 13-  drive morning @8a for Bharatpur - watch or play Holi - have lunch 
            Evening - drive to Jaipur, Spend evening enjoying a lavish Rajasthani dinner in front of a traditional folk dance
	              at chowki dhani,sunrise at nahargarh fort, bapu bazar

	  Overnight Jaipur (Hotel Rambagh Palace)
March 14 - 
            Jaipur- City Palace,  Snake charmer, Fabric dying, block printing, cotton weaving.
             Experience rickshaw ride, see bazaar
            If have time watch a bollywood movie at the famous Raj Mandir Cinema
        Overnight Jaipur (Hotel Mandawa Haveli; Hotel Rambagh Palace)
March 15 - Jaipur-Alsisar (semi-desert region)
           Walk through local village- it was a trading post of a caravan route; Havelis were painted by merchants
           Drive Alsisar- Bikaner
        Overnight Bikaner (Hotel Laxmi Niwas Palace)
March 16-
            Bikaner- fortified town, see Junagarh Fort
            Drive Bikaner - Jaisalmer
          Overnight Jaisalmer
March 17-
          Jaisalmer - Fort Jaisalmer, Chattriya Jaisalmer, sand dunes- camel ride, wood and sandstone building, hidden shops 
        Overnight Jaisalmer (Hotel Suryagarh)
March 18-
       Drive Jaisalmer-Jodhpur
       Jodhpur- the blue city, grand Meherangarh Fort on a hilltop, bazaar- spices, ornaments and textile
      Overnight Jodhpur (Hotel Umaid Bhavan Palace)
March 19-
      Drive Jodhpur-Ranakpur-Udaipur
      Overnight Ranakpur or Udaipur
	Udaipur- tour of City Palace, cruise on Lake Pichola
	Sip an ice cool G&T at Lakeside restaurant  
	Sunset at Monsoon Palace 
      Overnight Udaipur (Hotel Taj Lake Palace )
March 20 - 
    Fly Udaipur - Delhi
    Drive through Rajpath, India Gate, Janpath 
    Clubbing at Delhi
    Overnight Delhi
March 21- fly Delhi to US

[Hotels by Taj or Heritage Group]
Check Delhi- Jaipur - Agra
https://www.onthegotours.com/India/Tours/Festival-Tours/Holi--Festival-of-Colour-8-days






## To start a windows on mac

http://www.parallels.com/products/desktop/ 

## ML and ES
Elasticsearch/Apache lucene uses what is called inverted index.  It is closer to 'more like this' on content or 'bag of words' approach.  'Good value' varies across different domain.  
Marrying Elasticsearch to NLP to solve the problem is very much possible.  Solution can be extended to use NLP techniques as modeling step.  
Whatever we use -it is equally important to pay attention to data input and data gathering step to improve accuracy.

NLP algorithms can be applied on data to compare the results from NLP model and compare accuracy.  We need an unbiased labeled data to train the model.

Prelert - executes as kibana app; behavioral analytics ex: anomalies
reduce false positives
Prelert Behavioral Analytics for the Elastic Stack helps you automate the analysis of massive Elasticsearch data sets using machine learning technology.

Excellent for anomaly detection. -Proprietary unsupervised machine learning algorithms baseline normal behavior without the need for training data sets. 
-Sophisticated machine learning algorithms provide you with accurate information (fewer false positives) 
-Statistical influencers for related anomalies

## FSMB
Intro

I have been working hands on in Big Data for last about 4 years now. Had end to end solution architecting and development experience using leading edge hadoop, spark, nosql technologies. Created data pipelines in spark/kafka calling ML and business logic on the way and landing data on NoSQL/Hbase or Elastic or Dashboard (angular.js)

Prior to that I have been in  Enterprise IT from development & Program management in Bank, Telecom, Healthcare Services etc.
Education background-Electrical Engineer & Computer Science 

Data Science-
Analyzing and interpreting data using advanced statistical methods
Topic modeling using LDA. wordtovec - singular word, doctovec - 
Predictive model using Regression
ML- Random Forest
A decision tree is built using the whole dataset considering all features,but in random forests a fraction of the number of rows is selected at random and a particular number of features are selected at random to train on and a decision tree is built on this subset.

Experience:
Perform exploratory and feasibility analysis for Federation of State Medical Boards (FSMB) data & processes and provide technology roadmap to exploit Elastic Stack for flexible search and matching capabilities with a goal to improve data quality and enhance automation.

Technology used for PoC: 
Elastic Stack 5.0 
Elastcisearch 5.0: ingest pipeline to pre-process PDFs, extract text content, index the data with meta data.
Kibana 5.0: Analyse and view data.  Complex query DSLs for searching in Kibana.
Use of percolator with pdf content for matching.  
Logstash for bulk loading and preprocessing files into Elastic indexes.  
Automated scripts to kickoff loading, pre-processing, matching and spewing results.

Board Orders
Problem Statement: Compare incoming PDF documents with a list of some 250 standard text description and find the top 3 best match.
We provided technical solution and demo using Elasticsearch ingest pipeline for pdf transformation to text and percolator query to match over 250+ description(or topics).  We sent pdf documents through the pipeline that returns the top 3 topics for each document to the client to review.  The client came back saying the results returned is not what they have come up manually.  At this time we do not have enough data to determine the accuracy.  There may be a room to fine tune the percolate query once we collect more result.

Big data is useless without human intelligence
Machine Learning requires huge data for any kind of predication algorithms.
Data driven predictions, issues-
     Bias in data
Data rarely come packaged in a form that is useful for making predictions about the outcomes you care about.

Elasticsearch uses what is called inverted index - tf/idf .  It is closer to 'more like this' on content or 'bag of words' approach.  'Good value' varies across different domain.  
Marrying Elasticsearch to NLP to solve the problem is very much possible.  Solution can be extended to use NLP techniques as modeling step.  
Whatever we use it is equally important to pay attention to data input and data gathering step to improve accuracy.


What may be helpful is if one of you can articulate the algorithms Elasticsearch inherently provides, its pros, cons and tradeoffs.
What are the other NLP algorithms you recommend that is likely to improve accuracy?  Describe the data needed to train the model.



./csvtojson.sh ActionCodes.csv
curl -s -XPUT localhost:9200/_bulk --data-binary @“requests”; echo
 
Kibana 5 examples www.timroes.de
http://localhost:5601
check stackoverflow.com   discuss.elastic.co

pdf and jpg stored as base64 format
Send file contents to es in base64 
Apache Text Extraction Library Tika use by es for extracting attachment  

Store in es the path to the file; application that will query Elasticsearch know where 
to look for the file. 
Look at the attachment plugin that converts the file to text-
http://www.elasticsearch.org/guide/en/elasticsearch/reference/current/mapping-attachment-type.html


PDF to plain text —> Google doc has it

Command line tool  - pdftotext

## NPI

Check this site for search input - 
https://npiregistry.cms.hhs.gov/registry/?


You can access the latest monthly NPI file at http://download.cms.gov/nppes/NPI_Files.html

cs ~/Downloads/NPPES_Data_Dissemination_September_2016
NPPES*September*.zip (581 MB) has-
	NPPES*Data*Readme.pdf
	npi*Header.csv  - fields in cvs
	Data
	NPPES*Data*.csv    - data file also has first line as header
	NPPES *Code*Values.pdf


Only one monthly file is available: contains the complete information and fully replaces the old information.
Weekly file contains the new providers and any updates on existing providers.


~1.4M physicians
~400 fields, sparsely populated

## On mac
cd ~/Downloads/NPPES_Data_Dissemination_September_2016

Get monthly NPI file NPPES* at http://download.cms.gov/nppes/NPI_Files.html
As the file is > 4GB (~6GB)
# install on mac 7z to unzip a large file
brew install p7zip
7z x filename.zip

# Elastic version to install 
elasticsearch-2.3.4
logstash-2.4.0

tar xzvf fn.tar.gz

alias l='ls -ltr'
alias es='cd /Users/sbhalla/elasticsearch-2.3.4'
alias logstash=‘cd /Users/sbhalla/logstash-2.4.0’

export es=localhost:9200
export index=npiraw
export indexfull=npifull

alias se='./bin/elasticsearch --cluster.name=estest --node.name=estest_n1'
alias sl= ‘./bin/logstash -f npiload.conf --configtest’

source ~/.profile
rm ~/.sincedb*

curl -XDELETE $es/$index?pretty
curl -XDELETE $es/$indexfull?pretty

./bin/logstash —quiet -f /Users/sbhalla/fsmb/loadnpifull.conf --configtest

logstash -e 'input { stdin { } } output { stdout {} }'


rm ~/.sincedb*
 curl -XDELETE $es/$index?pretty

## useful sites

discuss.elastic.co
https://www.elastic.co/elasticon
https://www.elastic.co/support/matrix
https://www.elastic.co/guide/en/logstash/current/index.html

./bin/logstash -e 'input { stdin { } } output { stdout {} }'

./bin/logstash —quiet -f /Users/sbhalla/fsmb/loadnpi.conf --configtest

curl 'localhost:9200/npiraw/_search?q=*&pretty'

curl -XGET 'http://localhost:9200/logstash-$DATE/_search?pretty&q=license:A12343'

filter {
csv {
columns => ["Name","Year"]
separator => ","
add_field => {
      "fullname” => “%{first_name} %{middle_name} %{last_name} %{suffix}”
    }
convert => { "column1" => "integer", "column2" => "boolean" }
remove_field => [ "foo_%{somefield}", "my_extraneous_field" ]
    }
}
}


** To split a large file
$split  -a 10 -l 5000 - fn*.csv fileprefix
$split  -b 1000m  fn*.csv fileprefix


** to split a gz file, input file name in env dump and output filename in index
gunzip -c ../$dump | split -a 10 -l 500 - $index


A site that may be useful to look for matching is -
https://npiregistry.cms.hhs.gov/registry/?

## NPI on windows
Windows Server 2008 R2, 64-bit OS, Dual core
4GB RAM, 8GB Virtual Memory, 40GM hard drive, Only 10GB available
Note: Its good to play around, good for PoC  with test file but no way for prod or perf benchmark
Specially if you are doing sorting or aggregating you run out of memory fast
Ideally machine with 64G of RAM, 32 and 16 are ok but no way 4G
ES is not CPU intensive, more core is better
Disk space is used for indexing-heavy need
SSDs for better performance if you can spend money


Install chrome
Download and install 7-zip
Download JDK1.8.0_101 from oracle, set path
Down NPI monthly file.zip
Download elasticsearch-2.3.4.tar.gz and logstash-2.4.0.tar.gz

“c:\Program Files”\7-zip\7z x elasticsearch-2.3.4.tar.gz 

Note- it is a two step process to extract files
Same for logstash

“c:\Program Files”\7-Zip/7z x NPPE*.zip

To set environment variables and path
Start-> search for path  
set variable JAVA_HOME as c:\Program Files\Java\jdk1.8.0_111
and append path with c:\”Program Files”\Java\jdk1.8.0_111\bin

Shortcut for going back to home dir
set usr=%userprofile%
    cd %usr%

cd elasticsear*
.\bin\elasticsearch.bat

.\bin\elasticsearch.bat --cluster.name=estest --node.name=estest_n1

.\bin\logstash.bat -f c:\Users\shbhalla\fsmb\npiload.conf --configtest

October NPI Full file: 
605,701,856 zip file
~6GB unzipped
Total 5,038,808 (~5M records) 


In powershell -
gc npidata*.csv | select -first 5000 > testdata.csv 

gc testdata.csv | select -last 2

gc fn.txt | %{ $_ -replace '\d+', '($0)' } 

Issue: looks like file created using gc is not UTF-8 but hex. Log stash doesn’t like it




Intersys time management:
 
·         URL: https://www.openair.com/index.pl?account_nickname=Intersys%20Consulting;r=pb9Qmb46365
·         Company ID: Intersys Consulting (case-sensitive)
·         User ID: sbhalla (all lower case, case-sensitive)

Why Linux over Microsoft?

Linux is better than microsoft
   - curl in windows even powershell is a nightmare; can use cygwin
   - no ssh - have to download putty
   - sick of OS telling how to use it and getting in the way.

Issues

- micro-manage:  way to communicate is very disrespectful; treat as if we are elementary kids
- too critical.  Ex: the text; template input; how to talk in client meeting: ‘do not say OK’;
- too restrictive on how the tasks to be performed - ex: parallel solution trial for vpn connectivity in mac and windows
- uncomfortable - treating of sort.  ‘you were told earlier how you irritated client earlier. Don’t want SoW to be mentioned…’
       Something that was said as a suggestion is quoted as reprimand…	
	In fact the client referred to us collectively that she mentioned working on their server. We brought up the vpn issue and wanted to confirm the importance of working on their server.

-



Must exercise common courtesy and manner
Culture of collaboration and appreciation; a sense of common goal and purpose ; where team work is aappreciated; no internal competition;  
Language agnostic and even technology agnostic -- some leeway given to encourage creativity 

