#### notes from book Ractive Microservices Architecture pdf
Polyglot persistence - state stored in any storage medium NoSQL, RDBMS, Kafka etc. 

REST is most often synchoronous. 

For microservices a pure streaming processing architecture with event logging as persistence solution is most interesting.

Microservices are scaled on cores as well as nodes (location transparency)

Virtual addressing alows seamless failover.
Statful service active-passive replication
Stateless service - LB 

Microservices based architecture- pieces to consider
Discovery, Coordination, Security, Replication, Data consistency, failover, deployment, integration

Service Discovery: Each service should report information to the platform about where it is currently running and how it can be contacted. Information available through Service Registry or LB (server side discovery).

Two methods to store service information - depending on consistency desired - eventual consistency or strong consistency

API management
Be conservative in what you do, be liberal in what you
accept from others.
—Jon Postel

API Gateway: responsible for receiving request from client and routing to right set of servicesi and returning response to client.

Communication between services: pub-sub to topics like kafka or kinesis

For cases like splitting and merging streams (Akka streams or Apache Camel)

Authentication:
TLS Certificates - two way authentication.  Very robust security solution for
inter-service authentication in which each service is given a unique
private key and certificate on deployment. In this strategy, it is not
only the server that is verifying the identity of the client, but the cli‐
ent verifying the identity of the server. 
SSL:  Communication over SSL is well understood standard, however, complicated to manage.
HTTPS: when the services are HTTP-based

Apology Oriented Programming (when the data coordination is important) -when operation on data is composable.

Perform compensating action - same as banking withdrawal.

ACID (Associative, Communicative, Indempotent, Distributed) - equivalent is CRDT(counters, sets, map and graph)

If eventual consistency is not sufficient look into causal consistency ( implemented using logical time2)



