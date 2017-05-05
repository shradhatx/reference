# Kibana - reference material
 Time lion is part of Kibana core now.
./bin/kibana-plugin -install timelion
You will see a new icon in kibana for timeline
 Ask questions like - this Friday to last Friday
 traffic
 offset=-1w
 Separate two graphs by comma,
 Ex: .es(*), .es(metric=cardinality:user)
 or Chain functions, Ex:
 .es(*).divide(.es(metric=cardinality:user
  
Combine information from two different data source, cumulative sums, moving averages,
 derivatives etc.
CLI - to save a dashboard as pdf
Painless scripting language-
 secure
 similar to any other scripting language
 use in ingest node

Elastic Kibana and timelion demo
by Tanya Bragin

repo: 
https://github.com/tbragin/elasticon_tour16_demo

downloaded repo with data at ~\Download\elasticontour*.zip

Datasource - Northern California Earthquake data center

Steps: (view README.md in repo)
Download elastic stack 5.3  tar files for es, log stash and kibana
extract es and start es
extract log stash
put code repo in logstash
Extract the dataset archive with tar zxf ncedc-earthquakes-dataset.tar.gz
put earthquake data in es by cat earthquakes.txt| ../bin/logstash -f ncedc-earthquakes-logstash.conf
In Kibana, create a ncedc-earthquakes index pattern with timestamp checked
{"range" : { "mag":{"gte":4, "lte":8}}}
In Timelion
.es(index=ncedc-earthquakes,metric=avg:depth).points(fill=5).color(#8cd98c).label('Depth - raw').yaxis(label="Depth"),.es(index=ncedc-earthquakes,metric=avg:depth).lines().movingaverage(52).label('Depth - moving avg').yaxis(label="Depth").color(#257425 ), .es(index=ncedc-earthquakes,metric=avg:mag).points(fill=5).label('Magnitude - raw').yaxis(2, label="Magnitude").color(#ff6666), .es(index=ncedc-earthquakes,metric=avg:mag).lines().movingaverage(52).label('Magnitude - moving avg').yaxis(2, label="Magnitude").color(#b30000 )

.es(index=ncedc-earthquakes,metric=avg:mag).points(fill=5).label('raw').yaxis(2, label="Magnitude").color(#ffa3a3), .es(index=ncedc-earthquakes,metric=avg:mag).lines().movingaverage(52).label('5 year moving avg').yaxis(2, label="Magnitude").color(#b30000 ), .es(index=ncedc-earthquakes,metric=avg:mag).lines().movingaverage(10).label('1 year moving avg').yaxis(2, label="Magnitude").color(#ff3333)

.es(index=ncedc-earthquakes,metric=avg:depth).points(fill=5).color(#a3e0a3).label('raw').yaxis(label="Depth"),.es(index=ncedc-earthquakes,metric=avg:depth).lines().movingaverage(52).label('5 year moving avg').yaxis(label="Depth").color(#257425 ), .es(index=ncedc-earthquakes,metric=avg:depth).lines().movingaverage(10).label('1 year moving avg').yaxis(label="Depth").color(#00b359)


.es(index=ncedc-earthquakes,metric=max:mag),.es(index=ncedc*,metric=max:mag).mvavg(10)


.es(index=ncedc-earthquakes,metric=max:mag).points().color(blue),.es(index=ncedc*,metric=max:mag).mvavg(10).color(orange)
 
