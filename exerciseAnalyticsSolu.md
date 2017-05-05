=== Exercise :Building a log analytics solution
https://d0.awsstatic.com/Projects/P4113850/aws-projects_build-log-analytics-solution-on-aws.pdf
* cd ~/.ssh
ssh-keygen -t rsa -b 1024

* start EC2
IAM and permission to write to KinesisFirehose and CloudWatch

* ssh into EC2
chmod 400 fn.pem
ssh -i KPFeb.pem ec2-user@<dns>

* Create logs using Fake Apache Log Generator a python program (requires python 2.7 ) on your local mac
check https://github.com/kiritbasu/Fake-Apache-Log-Generator

> it requires python2.7
/usr/bin/python2.7

conda create -n python2 python=2.7 anaconda
source activate python2
python --version
sudo pip install --upgrade pip
Note: On EC2 use sudo /usr/local/bin/pip install
sudo pip install -r requirements.txt
python apache-fake-log-gen.py -n 100 -o LOG 
  creates file access_log_20170223-180751.log

Or create infinite logs to file with prefix SB
  python apache-fake-log-gen.py -n 100 -o LOG -p SB
$source deactivate

pip search 
> above line to deactivate python 2.7 env

copy log file created earlier
$scp -i fn.pem logfile.txt ec2-user@<dns>:~/logfile.txt

$scp -i KPFeb.pem /Users/shradhabhalla/Fake-Apache-Log-Generator/access_log_20170223-180751.log ec2-user@54.235.234.86:~/access_log_20170223-180751.log

* create kinesis firehose delivery stream
select destination: s3
delivery stream name: web-log-ingestion-stream 
choose new s3 bucket
IAM role: firehose_delivery_role  that can write to S3 bucket


* ceate a kinesis agent that collects and sends data to firehose by continuously monitoring files
It has native support for Apache access log files and can preprocess data

> ssh into EC2 54.92.147.247
$ssh -i KPFeb.pem ec2-user@54.88.175.255
$sudo yum install -y aws-kinesis-agent

$sudo su

$vi /etc/aws-kinesis/agent.json
{
  "cloudwatch.endpoint": "monitoring.us-east-1.amazonaws.com",

  "cloudwatch.emitMetrics": true,
  "firehose.endpoint": "firehose.us-east-1.amazonaws.com",
  "flows": [
    {
      "filePattern": "/home/ec2-user/access_log*",
      "deliveryStream": "web-log-ingestion-stream",
      "dataProcessingOptions": [
      {
        "initialPostion": "START_OF_FILE",
        "maxBufferAgeMillis":"2000",
        "optionName": "LOGTOJSON",
        "logFormat": "COMBINEDAPACHELOG"
      }
     ]
    }
   ]
}


$sudo service aws-kinesis-agent start

* Start ES service
domain name: web-log-summary
instance type: m3.medium

(note: takes 10 min for service to be created)

* create a second kinesis firehose
Destination: Amazon ES service
Delivery stream name: web-log-aggregated-data
ES domain name: web-log-summary
Index: request_data
Type: requests

Configuration-
IAM: firehose_delivery_role

* create Kinesis Analytics application
application name: web-log-aggregation-tutorial
source: weblog-ingestion-stream (firehose stream)

Go to SQL Editor
CREATE OR REPLACE STREAM "DESTINATION_SQL_STREAM" (
 datetime VARCHAR(30),
 status INTEGER,
 statusCount INTEGER);
CREATE OR REPLACE PUMP "STREAM_PUMP" AS
 INSERT INTO "DESTINATION_SQL_STREAM"
 SELECT
 STREAM TIMESTAMP_TO_CHAR('yyyy-MM-dd''T''HH:mm:ss.SSS',
LOCALTIMESTAMP) as datetime,
 "response" as status,
 COUNT(*) AS statusCount
 FROM "SOURCE_SQL_STREAM_001"
 GROUP BY
 "response",
 FLOOR(("SOURCE_SQL_STREAM_001".ROWTIME - TIMESTAMP '1970-01-
01 00:00:00') minute / 1 TO MINUTE);

Go to destination tab-
 Select stream: “weblog-aggregated-data”
This will forward aggregated output to ES and can be viewed in kibana

For automatic start on system startup
$sudo chkconfig aws-kinesis-agent on

Note:Agent activity is logged in  /var/log/aws-kinesis-agent/aws-kinesis-agent.log

* Open ES console, choose domain “web-logsummary”
Index name: request_data
Line chart, Frm new search, In metrics y-axis: aggregation: Sum, Field: Statuscount
X Axis Bucket Aggregation Date Histogram; sub-bucket - split lines, Aggregation Terms, Field: status


* Cleanup 
> Termintate EC2 instance

> Stop kinesis agent
sudo service aws-kinesis-agent stop

> Delete ES service domain

> Delete S3 bucket

> Delete Kinesis Anaytics application and Kinesis firehose delivery streams

 






