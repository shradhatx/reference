# Chef - reference material
Chef for Continuous Development
Automating configuration- infrastruction as a code - versions maintained.
 -install applications
 -define services
 -start/stop services
* Chef Server - stores cookbook, recipes, policies
* Chef nodes- communicate with Chef Server to get
configuration
* Chef workstation - where admin writes and test
the codes

Chef Recipes(in ruby) contain scripts to
 automate assigning resources (just like in AWS)
 To execute recipe-
 chef-apply <fn.rb>

## Ansible -IT automation for servers (thousands of nodes)
Ansible playbook- machine provisioning to code deployment.

#DevOps
Monitoring

A highly distributed and elastic environment not only needs real-time visibility, but also a more sophisticated way of evaluating whether a change is normal, an anomaly, part of a trend, or a real threat to availability and performance.

Needed- 
* analytics-centric approach to infrastructure monitoring with actionable relevant insights

* A dynamic, real-time view of time series metrics
* An easy way of exploring those metrics, using the properties of your environment, that help you identify, isolate and act on patterns that, in aggregate, lead to latency or outages 
* A broad set of analytical capabilities that allow you to present the most timely and relevant perspective for each user 
* Streaming analytics as the foundation for rational, effective alerts 


Capabilities-
Accept any and all data at high-velocity ingest. 
Storage of data at different resolutions, 


## Analytics for better action, ex:
Determine how effectively a cluster is load balanced by looking at the ratio of the standard deviation to its mean and setting up an alert when the load balancing effectiveness ratio falls below a threshold. Computing standard deviations, means, and ratios across multiple time series is as simple as selecting from the comprehensive library of analytical functions and creating mathematical expressions. Smooth out transient variations with the moving average function. Have the right data readily available to you when making a decision, rather than drowning in noise. 

Check the company Datadog.

Setup agent on each node and start monitoring. 
Events stream such as - CPU, docker came up, chef deployed something,… Events stored in ES
Dashboards-
  Customer facing
  Internal purposes
Alert and notifications

Internal logging goals:
 Provide a service all internal teams can use for specialized purpose
 Decrease need for server login & distributed grep
 Super fast access to latest log data
 Good access to older log data


















### Technical reference
* [elkref] - all about elastic stack
* [sparkref] - Spark
* [nosqlref] - Cassandra, HBase, MongoDB
* [logstashref] - Logstash

   [elkref]: <https://github.com/shradhatx/reference/elkdoc>
   [sparkref]: <https://github.com/shradhatx/reference/elkdoc>
   [nosqlref]: <https://github.com/shradhatx/reference/nosqldoc>
   [logstashref]: <https://github.com/shradhatx/reference/logstashdoc>










