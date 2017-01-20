# Logstash - reference material
Check the performance of CPU input source and output
destination.
Check CPU: top -H
(if CPU usage is high that is good. Next look for JVM heap)
Memory: Logstash runs on JVM. Check what else is running and if
log stash is not swapping back to disk often
Check for disk saturation: see if logs are not filling up disk
space and lot of i/o as well
Check for network saturation
Use  monitoring APIs- node info api, plugin api, node stats, hot
threads
localhost:9600
GET /
GET /_node/jvm
GET /_node/pipeline

Syntax - input, filter, output
input { stdin { } }
filter {
mutate { add_field => { "show" => "This data
will be in the output" } }
mutate { add_field => { "[@metadata][test]" => "Hello" } }
mutate { add_field => { "[@metadata][no_show]" => "This data will not be in the output" } }
}
output {
 if [@metadata][test] == "Hello" {
 stdout { codec => rubydebug }
}

Note : Output like
output {
  file {
 }
}
path => "/var/log/%{type}.%{+yyyy.MM.dd.HH}"
  
Note : Mutate filter to remove the field
filter {
if [action] == "login" {
}
mutate { remove_field => "secret" }
}

Note : Overriding @timestamp with time in the message
input { stdin { } }
filter {
grok { match => [ "message", "%{HTTPDATE:
[@metadata][timestamp]}" ] }
date { match => [ "[@metadata][timestamp]",
"dd/MMM/yyyy:HH:mm:ss Z" ] }
}
 output {
 }
stdout { codec => rubydebug }
  Input example-
 02/Mar/2014:15:36:43 +0100
outputs -
{
"@timestamp" => 2014-03-02T14:36:43.000Z,
"@version" => "1",
"host" => "example.com",
"message" => "02/Mar/2014:15:36:43 +0100"
}

Note : there is a special field called @metadata great for extending and building event fields. metadata does not show as part of event during in output

 You can see the metadata by-
 stdout { codec => rubydebug { metadata => true }

Note : use of environment variables in log stash conf. Restart required to if you you want to change the value
${var:default value}
 input { tcp {
 port => "${TCP_PORT:54321}"
 }
 }
export TCP_PORT=12345

### Technical reference
* [elkref] - all about elastic stack
* [sparkref] - Spark
* [nosqlref] - Cassandra, HBase, MongoDB
* [logstashref] - Logstash

   [elkref]: <https://github.com/shradhatx/reference/elkdoc>
   [sparkref]: <https://github.com/shradhatx/reference/elkdoc>
   [nosqlref]: <https://github.com/shradhatx/reference/nosqldoc>
   [logstashref]: <https://github.com/shradhatx/reference/logstashdoc>






