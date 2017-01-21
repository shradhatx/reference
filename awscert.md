## AWS Certified Solutions Architect
* draw.io — check to build n/w diagram
* Exam
Register at https://www.webassessor.com
$150 fee at a specified center
awscertification@aws.com https://aws.amazon.com/certification/certified-solutions-architect-associate/ aws.amazon.com/training/architect
aws.amazon.com/architecture https://aws.amazon.com/training/intro_series/
> Best practices for AWS architecture
> Design cloud services/plan/monitor-Pricing 
> RTO (recovery Time Objective) & RPO (Recovery Point Objective) DR Design
> Tradeoff - high availability Vs cost
> RDS , EC2
> Hybrid IT architectures e.g., Direct Connect, Storage Gateway, VPC
> Move data Disk Import/Export, Snowball
> Elasticity and scalability (e.g., Auto Scaling, SQS, ELB, CloudFront)

Configure an Amazon Machine Image (AMI)
* Operate and extend service management in a hybrid IT architecture 
* Configure services to support compliance requirements in the cloud 
* Launch instances across the AWS global infrastructure
* Configure IAM policies and best practices

## Data Security
AWS shared responsibility model
* AWS Identity and Access Management (IAM)
* Encryption solutions (e.g., key services)
* Complex access controls (building sophisticated security groups, ACLs, etc.) 
* Amazon CloudWatch for the security architect
* Amazon Virtual Private Cloud (VPC)
* AWS CloudTrail
* Incorporating common conventional security products (Firewall, VPN) • Design patterns
* Trusted Advisor
* CloudWatch Logs

## Disaster recovery: Recognize critical disaster recovery techniques and their implementation.
* Recovery time objective
* Recovery point objective
* Validation of data recovery method
* Elastic Load Balancer • Amazon Route53

## Troubleshooting
https://www.udemy.com/aws-certified-solutions-architect-associate/learn/v4/t/lecture
Cloud computing - on demand delivery of IT resources and app via internet with pay as you go pricing.
Cloud computing environment - o/s agnostic
[lambda servers architecture](bit.ly/1hzUUX1)

[cloudguru](https://acloud.guru)
https://aws.amazon.com/free/
[Get a domain name from godaddy](godaddy.com) 
http://exam.cloud.guru — App with practice exam on mobile

Re-Invent is a yearly conference by AWS IaaS

* Region- geographic area
* AZ- Availability Zone or data center - multiple (at least 2) in a region
* Edge location- CDN(content distribution Network) end point for cloud front

## Network-
VPC- virtual private cloud - a data center - you can have many in your AWS account(5 by default)
Direct Connect - a way to connect without using internet connection /fiber Route53- DNS service- 53 is the port DNS sits on

## Compute-
EC2 - virtual server
EC2 container Service or ECS - EC2 with docker
Elastic beanstalk - for deploying web application (in node.js, php, python, rails or java)
Ex: provides 2 node modules Express (web app framework) and Jade (for html) Bootstrap - a mobile-first front-end framework document)
Lambda- most powerful AWS service. Lets you run code without provisioning service. Cost effective.
Concepts & Components
EC2 are assigned 2 IP address at launch - public and private

## Concept and components
IAM
Directory services
Cloud Formation - for creating VPC Cloud Watch -
Cloud Trail - audit trail Opsworks -
Config - management tools Service Catalog
Trusted Advisor - automated service that tells you the way to save money etc. API Gateway -
AppStream - (for microsoft app - Note: check for Patientcare)
CloudSearch - manage and search solution
Elastic Transcoder - for transcoding media file SES - Email server
SQS - Queues, decoupling
SWF - simple work flow
CodeCommit - like github
CodeDeploy -
CodePipeline - continuous delivery service - deploys as code is changed MobileHub-monitor mobile apps
Cognito - app preferences for game
Device Farm -
Mobile Analytics - track app usage etc.
SNS - Notification
WorkSpaces - virtual desktop in the cloud WorkDocs - like drop box
WorkMail - like exchange
IoT

## Storage-
S3 - object based, hold flat files in cloud. You pay for storage that you use. Immediately available after write for PUTS. But eventual consistency for overwrite puts and deletes.
up to 1Byte - 5TB
S3-IA (infrequently accessed)
S3 is key value store + version ID; object based storage.
MFA (multi factor authentication) delete on S3.
Can transition from S3 to S3-IA if >128KB and 30 days old.
Also add TTL
Cloud front - edge locations (for cached files). You can write your files there.
Glacier - for long term backup (takes 4 hours if you need to access it but is cheapest storage.
EFS - elastic file system - block level storage— think of it as NAS on cloud - centralized storage - can connect to multiple EC2
Snowball - import/export services - send hard disk into AWS - move petabyte scale of data to AWS.
Storage gateway - connecting on-premise to cloudbase - for replication using VM
Pricing -
S3: $0.03 per GB up to 1TB/month

## Databases-
RDS- relational database services
Dynamo DB - NoSQL -non relational db Elasticache - caching db query result on cloud Redshift - DW service
DMS- DB Migration Service ex: oracle to MySQL

## Analytics-
Elastic Map Reduce (EMR)
TBD: Check Data pipeline
Elastic Search
Kinesis - streaming data into AWS ML
TBD: Check Quick Sight - BI service like Cognos.

## Security and Identity-
IAM- Identity Access Management, identity federation with fb, AD etc.
CloudFront-
CDN content distribution network or a collection of edge location
Edge location-where content is cached (different than Availability Zone); content cached till TTL. Can be used for write also.
Origin - where the content is
User request routed to the nearest Edge location to see if it is cached there; if not found it requests from CDN
Web Distribution - typically used for web sites
RTMP - for media streaming

## Security and Encryption-
Secure buckets using bucket policy and ACL
Policy is a document that provide a formal statement of one or more permissions. Encryption: In Transit (SSL/TLS)
At Rest
Server side encryption:
S3 managed key SSE-S3 AS-256,
AWS Key Management Service SSE-KMS envelop key provides added advantage, audit trail
Customer provided key SSE-C Client side encryption SSL

## AWS Storage Gateway-
On premise storage via Storage g/w on VMWare ESXi can be seamlessly connected to storage on AWS. (Download virtual image of AWS storage gateway on your data center
and activate with your AWS account)
G/W stored volumes: Entire data onsite and asynchronously backed up on S3
￼G/W Cached Volumes: only most frequently used data is stored locally so you don’t need SAN arrays. Cons: if internet connectivity is down you cannot access all data
G/W Virtual Tape Library (VTL): virtual tape on AWS accessed by iSCSI interface. Supported by NetBackup

## Import/Export
Import/Export Disk: to move large amount of data on/off AWS; bypasses internet and uses amazon’s high speed direct internal connection. Import to EBS, S3, Glacier. Export to S3
T1(1.544 Mbps), T3(44.736 Mbps), 10/100/1000Mbps
Import/Export Snowball: for PB scale data transfer. You can rent snowball. Saves n/w cost, secured. You should always use snowball rather than import/export disk

## S3 Transfer Acceleration
Transfer data first to edge location (using distinct url) which will then move it to S3. AWS optimizes this.
Extra cost

## EC2 Elastic Compute Cloud
Read: docs.as.amazon.com/AWSEC2/latest/UserGuide/ec2-instance-metadata.html
On demand Reserved Spot
EBS - elastic block storage - storage volume attached to EC2 for database etc.
You can have multiple disk/EBS on one computer/EC2 but not a disk shared between 2 computers.
General purpose SSD: 99.99 durability, <10,000 IOPS Provisioned IOPS SSD - for high I/O noSQL >10,000 IOPS Magnetic: lowest storage cost
Choose Amazon Linux and then t2.micro type M4 and M3 (DIRT MCG)
R- RAM intensive
C- CPU intensive
Set EBS
￼Bootstrap
Launch Configuration EFS
Lambda
HPC
[Check EC2 instance metadata](http://169.254.169.254/latest/meta-data/)
curl http://169.254.169.254/latest/meta-data/
get value of metadata item as-
curl http://169.254.169.254/latest/meta-data/hostname
http://aws.amazon.com/code/1825
EC2 instance can be configured with user data. User data is used during launch only - not by stop-start of instance.

## Security Group
Volumes exist on EBS - virtual hard disk; Snapshots exist on S3.
You can take a snapshot of a volume. Snapshots are incremental and can be accessed through APIs/CLI and AWS console

## RAID, Volumes and Snapshots
RAID: Redundant Array of Independent Disks RAID 0 no redundancy, high performance RAID 1
￼￼
RAID 5 - good for reads. NOT recommended by Amazon for EBS
RAID 10 good redundancy, good performance
Go to security group, Add rule - RDP, My IP
Go to EC2 - Launch a windows server R2, t2.micro; Set IAM role to S3 Access, ; Add storage - add 4 volumes; Launch (will cost some); Download key pair

## AMI Amazon Machine Image
EBS volume - root device created from AMI EBS snapshot
Instance Store - root device created from AMI with template on S3. The data is ephemeral and is lost when terminated.

## Load Balancer and Healthcheck CloudWatch - for perf cpu/disk monitoring
AWS CLI #aws s3 ls #aws configure #aws s3 help

## IAM Identity Access Management and Roles
Access key ID & Secret access key generated when you create a user.
Roles can be assigned to EC2 instance when you are launching it
With role you do not have to put secret key and password on EC2 thereby making it more secure

## Bootstrap scripts 
#!/bin/bash
yum install http -y
yum update -y
aws s3 cp s3://<fn> <dir> service https start

## Metadata on EC2
curl http://169.254.169.254/latest/meta-data/public-ipv4

## Launch Auto Config & Auto Scaling
To auto expand and shrink compute power

## EC2 Placement group
grouping of instances within a same availability zone for low latency ex grid computing.
(not t2.micro) compute, memory, GPU, storage optimized

## EFS Elastic File System (Very new on AWS)
sharing across different AZ (unlike EBS that cannot be shared across EC2s) NFS v4 protocol
No pre-provisioning required (unlike EBS storage attached to EC2 that is pre- provisioned). You pay for what is used.
EFS is a block based storage.

## Lambda
AWS Lambda is a compute function. You upload the code and create a lambda function and it takes care of the rest. Lambda runs the code in response to events ie.
￼change to data on s3 bucket or dynamo db table or response to http request using Amazon API gateway or AWS SDK
Very low cost - charged only when the code is run.
No Servers
Continuous scaling

## DNS
check http://www.iana.org/domains/root/db
InterNIC a service of ICANN
SOA Record - start of authority - keeps the info on servers/administrator etc NS Records - Name Server - directs traffic to DNS
‘A’ Record - Address - translates domain name to IP address
CNAMES - resolves two domain names to a single IP e.g. mobile domain
Alias records - alias for a naked domain name (without www) specially for DNS you create an alias record.

## Route 53
ELB do not have IPv4 address therefore you use them using DNS name. Routing policies: Simple, Weighted, Latency, Failover, Geolocation

## Databases
RDS - Relational or OLTP- MySQL, Postgres, Oracle, Aurora,
DynamoDB - noSQL document oriented db - collection/Document/key-value json Redshift - DW- used for BI OLAP
DMS - Data Migration Services
ElasticCache- in memory cache - Memcached or Redis
￼
## RDS Backup 
Automatically done. Retention by default 7 days but can be extended to 35 days. you can go up to the minute on recovery time. Choose a backup window. Storage i/o is suspended during backup.
Snapshots done manually. Snapshots are available even when db deleted.
Encryption - at rest using AWS KMS including the replicas and backup. You have to create a new instance with encryption and migrate the data into it.
Multiple AZ for DR only. Cannot be used as an independent read node.
Read Replica to boost performance. Asynchronous replication. Multiple AZ can read from multiple read replicas.
Read replicas —read only copy of production database. can be used by dev or QA for
testing on prod copy of data. Use read replicas primarily for very read-heavy database workloads. Available for postgres, MariaDB (not available for Oracle or SQL Server). Each read replicas have their own DNS endpoint. Main RDS must have automatic backup turned on.
To scale up to a bigger EC2 instance you need a downtime. Maximum size RDS volume = 6TB
Maximum IOPS capacity 30,000 IOPS

## DynamoDB
Stored on SSD
Spread across 3 geographically distinct data centers(facilities). Can have 2 different consistency models-
* eventual consistency (available within 1s)
* strongly consistent (available immediately after write response)
Price based on provisioned throughput- write, read and storage
Approx cost for 1 million reads and 1 million writes per day(or 11.6 read and 11.6 writes per sec or 12 read and 12 write capacity) for 3G of data:
Cost of write $0.18 per day
Cost of read $0.04 per day
Push button scaling i.e. no interruption for scaling up.
Reserve capacity (for 1 or 3 year) saves cost.

## Redshift- DW (OLAP)
Columnar database- less i/o improves performance of DW aggregation queries. Advance compression means less disk space.
Automatically distributes data.
Massively Parallel Processing (MPP)
Stores data sequentially on disk.
peta byte scale DW service
$1000 per terabyte per year (less than tenth the cost of any other DW solution)
Configuration-
Single node (160Gb of data)
Multi-node- a leader node & up to 128 compute nodes Price per node per hour (not charged for Leader node) Security-
Encrypted in transit using SSL
Encrypted at rest using AES- 256 encryption
Not designed for multiple AZ
￼Can restore to new AZ using snapshots during outage

## Elasticache
Supports two open source in-memory caching:
Memcached- compliant with popular Memcached. Do not support multi AZ Redis - k-v store, supports list and sorted sets, supports multi AZ
When data is not prone to frequent changing and is read heavy because of OLAP queries you can cache the data in memory

## Aurora
AWS proprietary database engine in RDS available only on AWS
MySQL compatible engine
Autoscales in 10Gb increment up to 64 Tb automatically
Compute resources scale up to 32CPUs and 244Gb of memory.
Keeps 2 copies of data in each availability zone with 3 AZ i.e. total 6 copies
R3 large instance (t3 micro not available)
tier 0 is the highest priority replica.
Storage is self healing i.e. data blocks are continuously scanned for errors and repaired automatically.
Replicas- 2 different replicas - aurora replicas (up to 15), Read Replicas up to 5.You create replicas manually.
Difference - failover occurs automatically over aurora replica

## VPC Virtual Private Cloud
Build VPC (virtual data center in the cloud): selection of IP address range, creation of subnets, configuration of route tables and network gateways.
￼5 VPCs max allowed in any AWS region by default.
Public facing subnet - web server
Private facing subnet for database or app
Security groups
Create Hybrid cloud: Hardware VPN connection - between corporate data center and VPC and use AWS as an extension of your data center.
Create Custom VPC -
Launch instances into subnet
Assign custom IP range
Configure route tables between subnets
Create internet gateway (one g/w per vpc) and attach them to subnets Instance security groups
Access Control Lists (ACL)
VPC Peering (in star configuration i.e. 1 central VPC peering with 4 other) No transitive peering
CIDR - give subnet 10.0.0.0/16 and your VPC
Create Subnets - one subnet on only one AZ (give CIDR block 10.0.1.0/24) Route Tables
Internet Gateway (attach to VPC)
EC2 on different subnet can communicate with each other

## NAT (Network Address Translation)
NAT - use it as a bridge to have outbound internet connection from EC2 on our private subnet.
￼On dashboard go to EC2- search for NAT- choose a larger instance for it for better n/w thruput.
Allow inbound and outbound traffic to IP
Note: A public IP or ELB needed for EC2 inside a subnet to be internet accessible
On NAT instance - must disable source and destination check under Actions-Network- Source/Dest check and Disable

## ACL (Access Control List)
Security groups act like a firewall at instance level. ACL provides additional level of security at subnet level. ACL Rules override security group rule.
Rule 100 is applied before rule 200
Subnet can only be assigned one network ACL.
A network ACL can be associated with multiple subnets
When a new ACL is created all inbound and outbound traffic is Denied.

## SQS Distributed Message Queue System - for decoupling components Messages can be 256KB of text in any format.
Doesn’t guarantee FIFO. Guarantees delivery but not only once (design your app to handle duplicates)
Retention 14 days
Billing in 65KB chunks payload
1 million SQS requests per month are free
For priority messages keep 2 queues
$0.50 per million requests per month after that.
Ex: Producer puts on SQS the command to encode image file & location of file on s3 Consumer or image processing software pulls the task message; retrieves the named file, process the conversion; writes the images back to s3; writes a “task complete” message to another queue; deletes the original task message;

## SWF Simple Work Flow
includes logistic services coordination Retention 1 year
Tasks oriented
Types of actors-
￼WF Starters - Deciders - Activity Workers -
A collection of related workflows is called domain
SWF ensures that task is assigned once and is never duplicated.

## SNS Simple Notification Services
Notification- to mobile devices, email, SQS and can even invoke lambda function.
Create Topic for multiple recipients of same notifications- Amazon resource name is created
Configure SNS under Mobile Services on AWS dashboard
Pricing $0.50 per million SNS request $2.0 per 100K email
$0.75 per 100 SMS
$0.06 per 100K http
SES - Simple Email Service

## Elastic Transcoder
Media transcoder in the code read.acloud.guru

## Building a Fault Tolerant Wordpress site
Users connect to route53 into Cloud Front, 2 EC2 instances behind an autoscaling group;
Multi AZ enabled RDS instances; The set up will automatically heal with no downtime. Keep media assets and code into S3
Health check and failover; Region replication on bucket.
* create IAM (identity Access Management) role for s3 (mys3role)- for EC2- give full access to s3.
* go to VPC and create 2 security groups (WebSG for EC2s and RDSSG for RDS) WebSG -> Inbound rule - allow SSH and HTTP
RDSSG -> Inbound url - type: MySQLAurora, source: WebSG
* Click S3 and create 2 new buckets in the region you created security groups
￼Create Bucket - SBWordPress (choose same region as SG)
Create Bucket SBWordPressMedia
* Click Cloud Front and create CDN; origin domain name is the bucket
SBWordPressMedia (note- takes some time), Restrict bucket Access:Yes, Grant Permission: Yes
When created displays the domain name. Note the domain name.
* Click RDS- create instance choose MySQL, t2.micro, multi-AZ deployment:yes dbinstance name: sbdbtest, sbdbuser; sbdbpw;
security group: RDSSG; Publicly accessible: No
Hit Launch. (it will only be accessible from our web server)
* Click EC2, Create LB Name:SBWPLB, SecurityGroup: WebSG, Configure Health Check
Click on Route53, create a zone apex record (you’ll see NS and SOA record already present)
On right panel create an alias, Alias Target: SBWPLB
Click EC2- create instance t2.micro, IAM role: mys3role; add bootstrap script(see
below), EC2 Name: SBWPServer; Hit Launch

## bootstrap script
#!/bin/bash
yum update
yum install httpd php php-mysql stress -y cd /etc/httpd/conf
cp httpd.conf httpdconfbackup.conf
rm -rf httpd.conf
wget https://s3-eu-west-1.amazonaws.com/acloudguru/config/httpd.conf cd /var/www/html
echo "healthy" > healthy.html
wget https://wordpress.org/latest.tar.gz
tar -xzf latest.tar.gz
cp -r wordpress/* /var/www/html/
rm -rf wordpress
rm -rf latest.tar.gz
chmod -R 755 wp-content
chown -R apache.apache wp-content
service httpd start
chkconfig httpd on

vi http.conf
change ‘AllowOverride None’ to ‘AllowOverride All’
￼Note: you can use sed to make this change and include it in bootstrap script ===
Go to SBWPLB and add our EC2 SBWPServer. Wait 30 sec.
Go to DB instance that is running, copy the db endpoint
url:<Ip address of EC2> or url:aforce.guru
Here : enter the dbname etc, for Database Host: <enter copied db endpoint here>:port Note if you get an error saying wp-config.php not found then go to terminal window,
ssh to EC2 cd /var/www/html
nano wp-config.php
<cut and paste the contents>
Go to url
Give db details. This will Install wordpress

## Make this fault tolerant
#aws s3 ls
Note: you see 2 buckets
Now backup code from html to s3
#aws s3 cp —recursive /var/www/html s3://wordpresscode16acloudguru Remove html dir to simulate EC2 failure
#rm - rf html
#aws s3 cp —recursive s3://wordpresscode16acloudguru /var/www/html #chmod -R 755 wp-content
#chown -R apache.apache wp-content

## Make image for Autoscaling #cd wp-content
cd uploads
ls
go to word press site dashboard
Side panel - click Media upload a pic
Side panel - click Posts insert the uploaded media/pic Hit update go to console
see that there is dir under uploads/2016/04/picname.jpg
aws s3 ls
Now copy the uploaded files to s3 that serves as cloudfront
￼￼
aws s3 cp —recursive /var/www/html/wp-content/uploads s3://
wordpressmedia16acloudguru
Next time when you upload file you give sync so the first file is not copied again(wasted cpu)
aws s3 sync /var/www/html/wp-content/uploads s3://wordpressmedia16acloudguru —delete <—dryrun>
> Note: for website to use cloud front instead of serving from EC2 instance url rewrite rules: create a file called htaccess
cd /var/www/html
Copy htaccess from S3 under our bucket config directory #cp htaccess .htaccess
nano .htaccess
rewriterule
go to aws dashboard click on CloudFront, get the domain name of your cloud front and provide that in .htaccess rewriterule
service httpd restart

## automate
Note: Run every 2 min
nano crontab
*/2 * * * * root aws s3 sync —delete /var/www/html/ s3://<dir wordpresscode> */2 * * * * root aws s3 sync —delete /var/www/html/wp-content/uploads s3:// <wordpress media dir>
*/3 * * * * root aws s3 sync —delete s3://<dir wordpresscode> /var/www/html/
The above command syncs dirs every 2 minutes and downloads EC2 changes down to every instances
Tip: Dedicate a EC2 instance to marketing with only 2 lines above who only do blogs Line 3 only for read only instances which pulls changes to code down
Try adding another media, wait 5 minutes and see it is uploaded to cloudfront

## create AMI
Go to EC2 console
Select EC2 instance Action->Image->create select option No reboot Check-
￼￼￼
Side menu- My AMI
Add simple bootstrap script
yum update -y
cp s3://<dir wordpresscode> /var/www/html/

## create AutoScaling Group & failover
aws console
delete both ec2 instances
Side menu- Autoscaling ASG -> Launch configuration My AMI- template web press
Name: WPLaunchCG, s3role, Security Grp: WebSG Run the small bash script
#!
ASG Name: WPLaunchConfigGrp
Start 2 instance in our Network: our VPC
Select all Subnets
Advance Details: Receive traffic from LB
Health check: ELB
Grace period: 300 (or 5 min)
Scale between 2 & 4
Create AS policies ex: cpu util > 60% 1 consecutive period Alarm: CPU above 60%
Go to EC2 dashboard and see the instances created automatically After instances are created you’ll see LB
Simulate Failover-
Go to EBS server, Action Reboot, reboot with failover - this will create another instance in another AZ
Go check your site. Comes up in within a min (you do not get couldn’t not connect error)
Run Stress-
Log into a EC2 instance
stress —cpu 100
let it run for 10 min. See that another instance should come up, see that cpu has gone upto 100% cpu

## Cloud formation template (like bootstrapping but for all of aws resources) Turn the entire infrastructure creation steps to code
Create the complete word press site in another region using a script.
Can apply version control
To test:
Delete ASG, EBS, ELB, EC2
AWS console Click CloudFormation
Create a Stack, Select a template: Multi AZ Sample-> Wordpress (check the n/w diagram) Check the CF script by clicking each module in the diagram.
Stack Name: SBWP-ASG-CF
DBAllocated storage: 5
DBClass: db.t2.small
DBName:
InstanceType: t2.micro
MultiAZ: true
SSHLocatiopn:0.0.0.0/0
WebServerCapacity:2
Next-Next-Create
Will provision SG, LB, ASG, EC2, start apache etc. (takes ~20 mins)
TBD: Google- How to convert existing Cloud to a CF script? **** Whitepapers
AWS - 12 region with multiple AZ
Infrastructure Security- 24x7, DR
Compliance - PCI Compliant, ISO 27001, HIPAA

## WP: Security: Shared responsibility
IAAS - user of AWS responsible for securing VPC, EC2, RDS, S3, Apps etc.
Transmission protection - SSL to protect against eavesdropping, tampering and message forgery.
VPC with private subnets for added n/w security.
VPN for encrypted tunnel between amazon VPC and your data center.
￼Network monitoring and protection against IP Spoofing, port scanning, packet sniffing, DDos, Man in the middle (MITM) attack
Password
MFA
Access keys
Key Pairs
Secure content thru X509 certificate
Trusted Advisor check provides recommendations Data encryption - EBS encryption using AES-256

## White Paper: Overview of amazon security
Different instances on same physical server are separated by Zen Hypervisor. Physical RAM separated by same mechanism.
Memory scrubbing when memory is freed, before reassigning to another.

## WP:Risk and Compliance
Risk assessment done 6 monthly by AWS Compliance-
SOC1, 2, 3
FISMA
PCI DSS Level 1
HIPAA
Cloud Security Alliance

## Benefits AWS/Cloud computing advantage
Business benefits: Capital Expense/zero upfront cost, Economy of scale, usage based costing, elastic, agility/JIT infrastructure, no maintenance data centers, go global in mins/reduce time to market
Technical benefits: automation, more efficient development life cycle, scriptable infrastructure, improve testability, DR & business continuity, autoscaling (elastic scaling), proactive scaling, overflow traffic to the cloud incrementally.

> Tips:
Design for failure: Be pessimist and think about recovery strategies during design time. Ex: Simian monkey by Netflix- designed to cause trouble ex destroys the instance or slow them intentionally.
Proactive cyclic scaling, event based scaling or auto scaling.

## kinesis
ingest streaming data e.g. social media data

## Opswork
Orchestration services ex chef

## Well architected framework
4 pillars:
* Security
* Reliability
* Performance Efficiency 
* Cost optimization

Design principles:
* Stop guessing your capacity to avoid over or under provisioning -Test systems at production scale
* Lower the risk of architecture change
* Automate to make architecture experimentation easier
* Allow for evolutionary architectures

a) Security- shared responsibility: encryption, access, infrastructure protection, audit. Apply security at all layers: Subnets, ACL, ports on LB, antivirus on instances, enable traceability, automate responses to alerts.
Secure: Data, Application, Operating system
Harden O/S
Data protection: classify data and access groups, encryption. Privilege management: ACL, MFA, Role based Access, Pw mgmt. Infrastructure protection: VPC subnet - private/public
Detective controls: cloudWatch, cloudTrail, AWS logs
b) Reliability
test your recovery procedures ex: semian army
automatic recovery from failure, anticipate and remediate failures -scaling horizontally for increased availability

Import step- Find out the size of comm link/pipe between your HQ and Data Center. If you mis -provision this it takes 3-6 months to upgrade. It will have massive impact on operation.
AWS has service limits - questions to ask-
* who and how you are managing service limits of your account?
* how are you planning your n/w topology on AWS?
* Do you have an escalation path for technical issues?

i) Change Management:
On AWS Cloud Watch can alert and you can always set autoscale. -How are you executing change management?
ii) Failure Management: Questions to ask-

* How are you managing your failures and recovery procedures
* How are you managing component failure?

c) Performance efficiency: Foundations
* Focus on maintaining efficiency as the demand changes
* Constantly evolve technology as advance tech becomes available on AWS
Ex: use AWS ML services; server-less architecture
i) Compute- flexible memory intensive or CPU intensive
Servers architecture design — check it out using lambda you pay only for execution time of EC2.
Questions:
* how do you select instance to use
* how do you monitor instances post launch to see they are performing as
expected
* how do you ensure that quantity of you instances matches the demand
ii)Storage
Optimal storage solution depends on -
* Access method - Block(EFS), File(EFS or S3) or Object(S3)
* Pattern of access - Random(SSD) or sequential(Tapes)
* Throughput required (IOPS)
* Frequency of access - online (EBS), offline or archival (S3-IA, Glacier)
* Frequency of update- warm, dynamic
* Availability constraints
* Durability constraints
Storage choices: S3- object based, EFS like NAS, RAID (0-no redundancy, 10-good perf, redundancy); AWS storage: gateway cached, gateway stored; RDS, DynamoDB, Redshift;
Data also as snapshots; read replicas;
Also, check the requirements - can we accommodate downtime when upgrading EC2
instance- RDS:not without downtime, DynamoDB- no downtime when upgrading instance.
iii) Database
Questions to ask-
* Do you need consistency, high availability, No-SQL, DR etc? Is the db going to
be used for analytics?
AWS options: RDS, DynamoDB, Redshift etc.
Read replicas for low latency.
Direct connect between HQ and AWS for predictable latency.
ElasticCache or CloudFront to reduce latency.
EBS volumes - SSDs, IOPS SSD, magnetic; Memcached, Redis(multi AZ); Global infrastructure for multiple copies of infrastructure close to customer base.
iv) Space-Time tradeoff Proximity & Caching solution
d) Cost Optimization pillar
Use managed services to reduce cost of ownership Benefit from economies of scale
Trade capital expense for operating expense
Stop spending money on data center operation
>  must use scale down to stop those idle ec2
> match supply and demand: optimum use by autoscaling or servers
How do you ensure your cost optimally matches the demand and not substantially
over exceed?
Look for cost effective resources
EC2 reserved instance -expenditure awareness
EC2 spot instances for experiment
￼CloudWatch alarms; consolidate AWS billing - how do you govern AWS cost -optimize over time: constantly reevaluate
how do you manage adoption of new services?
how do you consider data transfer charges when designing your architecture?
## LAB
Download image with new private key fn.pem $chmod 600 <MyEC2Key>.pem
$ssh ec2-user@<ip address> -i <MyEC2Key>.pem
$sudo su
$yum update -y http://169.254.169.254/latest/meta-data/
#yum install httpd -y #service http status #service http start #chkconfig httpd on
#cd /var/www/html #nano index.html
Hello Shradha!
see the public url ip address displayed on ec2 dashboard at right bottom.
set the security setting -allow http.
*** Volumes on EBS and Snapshots on S3
#lsblk —-> shows the volumes on virtual box
#file -s /dev/xvdf —> check if there is data
#mkfs -t ext4 /dev/xvdf —> to create a file system on new volume
￼
￼#mkdir /fileserver
#mount /dev/xvdf /fileserver #cd /fileserver
#ls
#rm -rf lost+found/
#nano helloworld.txt
#aws s3 ls
#aws configure
#aws s3 help
//for bootstrap script #!/bin/bash
yum install http -y yum update -y
aws s3 cp s3://<fn> <dir> service https start
===== TRY
Go to console
create S3 bucket (all lowercase letters) : bhallas31 , region: oregon
set permission, upload file, view(ok for pdf, docx-downloads, png
TBD: set SNS, events - move the file to a different location, try music
Q: how can this upload be faster?
Done: Try S3 Transfer Acceleration on s3 using edge location (cost little money. Test the speed improvement before doing that)
￼Done: versioning (once turned on can only be disabled); on bucket or objects with prefix on 7/31 turned TTL rule on Shradha1607 to 1 day(to glacier) and 2 days (expire) Done: cross region replication (needs versioning enabled)
Done : move file from s3 to Infrequently access(min 30 days & 128KB) and to glacier, expire, and permanently delete (using life cycle rules on bucket or files)
TBD: retrieve file from glacier
IAM
click on IAM
customize; https://sbtxtest.signin.aws.amazon.com/console
sbtxtest/Test#12
activate MFA(download google activator on your phone first); create user group;
Create users, download ( credentials.csv ),password; download (credentials1.csv) Vijay12 is the password for Vijay
Create group (SBGroup) - to assign group of users in a group
Grant permissions using policies (AmazonS3FullAccess)
IAM password policy - see settings e.g. password rotation etc Roles - Create role S3AdminAccess - choose policy (search by S3*)
Create CDN
click on Cloud Front & create distribution
domain name < select s3 buckets in drop down>
origin path <folder path>
origin name <give any name>
restrict bucket access < click YES for all request to go through cloud front> Allow HTTP method: <select GET, HEAD, PUT, POST,..> —-this lets you put file
on edge location
TTL (in secs) 86400 is 24 hours — time to live on edge location
￼
￼Restrict Viewer Access — here you can limit user access
Alternate Domain Name — is CName, a user friendly name ex- cdn.acloud.guru Note - takes about 5 min to create distribution. when done copy the domain name TBD: Check access to pdf file using cloud front domain name
TBD: Set up Geo-restrictions (white list and black list)
(note: when done testing Disable distribution. Takes 15 min)
Launch EC2
Note: One subnet is one AZ
EC2-> Launch->Choose AMI(t3.micro)
setup shutdown behavior, startup script
Add storage volume; Root is by default, cannot be encrypted; TBD: add EBS volume;
Tag instance: Name, Developer, StaffID etc
Security Group(like a virtual firewall): Create New, add rule SSH and HTTP ,port :anywhere
Hit Review and Launch
Create a new key-pair, download key-pair
(public key is like a lock and private key is the key to open the lock)
~Downloads/SBKeyPair.pem
Note: takes a couple of minutes to launch Open terminal
chmod 600 *.pem
ssh ec2-user@52.33.150.126 -i SBKey2.pem
Note: connects to linux cli sudo su
￼#yum update -y
When done. Click on EC2 instance- go to Action drop down and Terminate (by default terminate option is turned off so enable it first under setting)
#yum install httpd -y
#cd /var/www/html
#nano index.html
Hello Shradha!! Created new EC2 instance.
#service httpd start
#chkconfig httpd on
#service httpd status
#
#history -c
=== create AMI
volume(side menu) - snapshot
EC2 instance - create image from snapshot
Note: snapshots are regional i.e. you need to copy the AMI if you are on another region == volumes and snapshots
#lsblk —-> shows the volumes on virtual box
#file -s /dev/xvdf —> check if there is data
#mkfs -t ext4 /dev/xvdf —> to create a file system on new volume
#mkdir /fileserver
#mount /dev/xvdf /fileserver
#cd /fileserver
#ls
#rm -rf lost+found/
#nano helloworld.txt
#aws s3 ls
￼=== Load Balancer
#cd /var/www/html
#nano health check.html
Hello Shradha - this instance is healthy
Click on Load Balancers Create LB - MyWebDMZ HTTP Port 80 to HTTP Port 80 Ping: healthcheck.html
Health Check Interval: 10 (in seconds) Unhealthy Threshold: 2
Healthy Threshold: 3
Start the LB
Under description- get the DNS name
Put this as url and see it is working
Note: LB have a DNS name not the IP address
To do:
Cloud Watch -you can make a dashboard to watch the usage etc.
Use bash script to automate EC2 creation, you then don’t need to ssh- #!/bin/bash
yum install -y
aws s3 cp s3://.../index.html /var/www/html/
service httpd start
Route53
￼Click on Route53 -> Get started now
Click on Create Hosted Zone
Enter domain name: (domain name registered with godaddy)
Type (dropdown): public hosted zone
Creates 2 records SOA and NS (creates in 4 zones)
Create Record Set - create an alias for LB you created earlier; Select Routing Policy:Simple
Go to godaddy.com and add 4 zones in name server settings - custom
Test: url-registered domain on godaddy( it should display the contents of index.html on AWS)
Launch RDS instance:
Launch EC2: Add bootstrap script (install php, php mysql, httpd);
Add security group for RDS instance.
Start EC2
Start RDS: select MySQL; enter user, password; use rest as default
Launch RDS (takes few mins)
Note: Do the following-
vi /var/www/html/connect.php and add the RDS endpoint from launched RDS) Allow Web server security group to talk to RDS security group
Go to RDSSecurity group, change the inbound rule- allows MySQLAurora to SGWeb
Go to url (the one displayed when RDS is alive)
Lab-
Create a snapshot of RDS-
￼Point in time recovery - restore to point in time.
Restore DB instance- here you can upgrade EC2 instance it is restored to, or to a encrypted DB or multi AZ (for automatic failover to standby during maintenance or DR). Migrate a snapshot- ex from MySQL to Aurora.
Create read replica, read replica of read replica and scale.
For performance create read replicas or caching. Read replica cannot have multi-AZ. Read replicas have their own endpoint. Can be promoted to its own db.
You can select different EC2 instance type, different region etc. Limited to 5 read replica. Can scale up with bigger instance but cannot scale out.
DynamoDB-
create instance
create table, give name (acloudguru) and pk (studentId), read capacity: 1, write capacity: 1
Add items to table. Append fields- add FirstName and give value
Check filter in overview; See metrics; change capacity on the fly.
No downtime when scaling.
****Lab: Create Custom VPC -Under Networking- do not use wizard instead create your own(see side menu) Note: you can have 3 VPCs for dev, test and prod. Dev or test VPC can communicate with prod via peering using private IP address if they want to talk.
-give name(SBTest-VPC), CIDR block(10.0.0.0/16), Tenancy:Default (Hit create) -Click on Route table(side menu)- see that a record is created automatically.
-Click on Subnet(side menu) - Create 3 Subnets SB-Subnet1,2,3; Give Name, Select SBTest-VPC, select AZ; CIDR: 10.0.1.0/24 and 10.0.2.0/24 and 10.0.3.0/24 (available IPs 251)
￼-Click on Internet Gateway (side menu); give a name: SB-IGW Hit create
By default g/w is detached. You attach it to your VPC
-Click on Route table(side menu) - create another route table SB-RouteT under SBTest- VPC (other than one created by default). Select record that is created and go to tab Routes (see below)- Edit (add a row) , under Target - choose our g/w, in destination give 0.0.0.0/0 (i.e allow all traffic)- Hit Save
Click Association tab- select the subnet 10.0.1.0 as the one to have internet access Now EC2 instance in this subnet will have internet access.
- Click Subnets(side menu) - see subnets
-Launch EC2, select our VPC, and SB-Subnet1, Auto assign IP address: Enable, click Next, Next (call SBWebS) under security group allow SSH and HTTP and Launch. Create a new keypair SBWebKP and download. Cut and paste in your keyboard.
- Launch another EC2 - for SBDbS, put this in 10.0.2.0 (the one that does not have
internet access), SBDbKP key-pair downloaded
- Test doing ssh to the two EC2 instances, the first one SBWebS has the public IP
address and can be accessed. The second one SBDbS doesn’t have public IP address it only has private IP address i.e. it doesn’t have internet access. You can ssh from SBWebS to SBDbS using private IP but #yum update -y fails. You give access to internet using NAT instance.
-Click Security Group, name: SBNatSG, give our VPC; see tabs below - under ‘inbound ‘add two rules type:HTTP and HTTPS Source: give 10.0.2.0/24 (where DB server sits in) for both
under ‘outbound ‘add two rules type:HTTP and HTTPS Source: give 0.0.0.0/0 (for both -Click on EC2, Choose an AMI, Community AMI search for nat* select gp2
(to improve network throughput select larger nat) network: SBTestVPC, subnet: 10.0.1.0
auto-assign public ip:Disable (since we assign it manually) we need public Ip or ELB
￼Next, use SBNatSG - Hit Launch
-Click on Elastic IPs (side menu under Network & Security), click Allocate new address, associate the allocated IP to our NAT instance Action->Associate Address
-Click on EC2 dashboard, select NAT instance running, click on Action->Networking- >source/destination check : Disable (by default it is enabled)
-Go to VPC Dashboard- create a new route- click Route Table- select default Route Table (row that is created by default) & click Routes tab(down below) - add a row Destination: 0.0.0.0/0, Target: my NAT VM Hit Save
Open terminal window - yum update -y
yum install mysql
see you have internet access even though it inside a private subnet (cannot ssh into it directly)
——-
Instance security groups
Steps
1. create custom VPC
1 VPC - multiple subnets, 1 g/w per VPC
For g/w to communicate to EC2s create route table and configure inbound and outbound connectivity to subnets.
2. Create EC2
Web server on subnet accessible to all EBS on subnet not accessible to all
￼Two subnets - 1 public facing ex web server, 1 internal facing ex EBS
+++ Tuning
S3: Turn off versioning on if not needed, set TTL. CloudFront- TTL on edge location
RDS: configure replication closely
Read replicas for heavy read
Think proximity of DC for geographically located users.
￼You are building a system to distribute confidential training videos to employees. Using CloudFront, what
method could be used to serve content that is stored in S3, but not publically accessible from S3
directly?
A. Create an Origin Access Identity (OAI) for CloudFront and grant access to the objects in your S3
bucket to that OAI.
B. Add the CloudFront account security group “amazon-cf/amazon-cf-sg” to the appropriate S3 bucket
policy.
C. Create an Identity and Access Management (IAM) User for CloudFront and grant access to the
objects in your S3 bucket to that IAM User.
D. Create a S3 bucket policy that lists the CloudFront distribution ID as the Principal and the target
bucket as the Amazon Resource Name (ARN).
￼
