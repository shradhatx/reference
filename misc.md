# Miscellaneous notes 
## Ideas on blogs-
 
* Predictive Analytics, Statistical probability, Machine Learning and Artificial Intelligence

Predict on future input based on learning on past input and labels

> Unsupervised learning: learning from the unlabeled data, steps -
 segment data into segments or cluster exhibiting similar behaviors
 no way to describe cluster or how many cluster it will result into and who will fall into which cluster

> Supervised Learning: when goal is a specific outcome; steps-
 train ML model by labeled data
 predict on new data

What model to use, attributes to include and estimate on predictions?

Statistical Learning

Reinforcement learning 

## Web crawling: Apache Nutch

Scrapy - python crawler

Nutch 2.x stream has abstraction layer that works with any data store

## Enterprise search
Searchblox Folder Link in Innovation Lab
 
https://intersysconsulting.sharepoint.com/consulting/innovationlab/SitePages/Home.aspx?RootFolder=%2Fconsulting%2Finnovationlab%2FShared%20Documents%2FSearchblox&FolderCTID=0x012000E9F1D16653AE3446AF16080286055B12&View=%7B8C2F12A9%2D152C%2D4FF2%2D915D%2D5C4527414957%7D 
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
==== DEMO prep
 IMF_SerachDemo.ppt by Andrew - if you like that we can use that format.  Title International Monetary Fund (IMF) - Search Demo  with slides for - 1) Seachblox - Features 2) Security  3) Search capability 3) Next steps - High Level Estimates

Question - how would you breakdown cost estimates? I am thinking you have to list high level tasks that will include tasks that Timo gives from Serachblox including the licenses(if any) cost and then integration work.





========

Demo on 16: 1-202-623-0498

Daily standup: 9:30EST  1800-281-5923,0007,,1234

Agenda:
1. Feature of SearchBlox in comparison with Sinequo

2. Security: 
Any known roadblocks ?  Risk: none

*okta - by 12th morning
AD - 

*integrate imf.org and sharepoint sites with searchblok -  

*ui - 
shield: as an option
authentication and access


>>when an we have an internal demo of - a) okta,  b) integration with imf.org and sharepoint c) UI 


====IMF FAST demo ===
FAST - indexes the doc and sharepoint queries the data
Business rules- slide relevance
Taxonomy built on the rules built in slide relevance.


crawls various sharepoint sites

FAST - clickthrough and FAST
sharepoint contact

SeeUnity connector - 3rd party


Problem at hand or use cases we are trying to resolve or trying to look for solution

9-11AM CST meeting to 

server account
crawler account

==== AD for security 
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

Rakesh Soni
Lawren Fendrich
Becky
Roger Wahman
Shradha

Elastic:
Joe Quick
*Nick Drost

GM:
*Mark Miller
Eric
*Denis 
Srini



## Personal Rajasthan trip

March 9  - Start from US & reach Delhi March 11 late evening
              Delhi - Dinner at Cyber Hub Gurgaon (Farzi Cafe), ( Buy white Kurta pajama for Holi)
March 11 -
         Overnight Gurgaon
March 12 - drive to Agra  
           **check if Taj is open on Sunday
           Agra - Agra fort, Taj Mahal during the day and at sunset  
        Dinner - Enjoy Mughlai cuisine
        Overnight Agra (Hotel: Seven Hills Tower;  Hotel Grand: http://www.grandhotelagra.com/Restaurant.html )
March 13-  drive morning @8a for Bharatpur - watch or play Holi - have lunch 
            Evening - drive to Jaipur, Spend evening enjoying a lavish Rajasthani dinner in front of a traditional folk dance
	  Overnight Jaipur (Hotel Mandawa Haveli; Hotel Rambagh Palace)
March 14 - 
            Jaipur- Amber Fort, Palace,  Snake charmer, Fabric dying, block printing, cotton weaving.
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
Board Orders

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

Downloaded files from fsmb at ~/fsmbInfo/FsmbArchive

\NPI- only 1xls file, and a link to NPI updates

You can access the latest monthly NPI file at http://download.cms.gov/nppes/NPI_Files.html

cs ~/Downloads/NPPES_Data_Dissemination_September_2016
NPPES*September*.zip (581 MB) has-
	NPPES*Data*Readme.pdf
	npi*Header.csv  - fields in cvs
	Data
	NPPES*Data*.csv    - data file also has first line as header
	NPPES *Code*Values.pdf

NPPES*Monthly*Deactivation*update*.zip  (1190KB)
NPPES*Weekly*update*zip (2.4MB)


Only one monthly file is available: contains the complete information and fully replaces the old information.
Weekly file contains the new providers and any updates on existing providers.


~1.4M physicians
~400 fields, sparsely populated

cd ~/Downloads/NPPES_Data_Dissemination_September_2016

** To split a large file
$split  -a 10 -l 5000 - fn*.csv fileprefix
$split  -b 1000m  fn*.csv fileprefix


** to split a gz file, input file name in env dump and output filename in index
gunzip -c ../$dump | split -a 10 -l 500 - $index


A site that may be useful to look for matching is -
https://npiregistry.cms.hhs.gov/registry/?





