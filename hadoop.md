## Hadoop & Map-Reduce
Master-Workers Architecture
Single Name Node
* Manages filesystem namespace by mapping inode to list of blocks & locations
* monitor daanode health, replicate missing blocks, keeps all namespace in memory (60M objects or File.Block in 16GB)
Many Data Nodes
* Handle block storage, send heartbeats to namenode, client reads from data node after getting info from namenode


Data is organized into files and directories
Files are divided into 128MB and distributed across cluster nodes

Map-Reduce
JobTracker(master)
TaskTracker(worker)

Client - Ui for submitting jobs
Task- Run Map and Reduce tasks, Manage intermediate output, run Map/reduce functins

User-
1) Copy INput files to HDFS
2) Submit job to client

Client-
3) Get input files info
4) Create splits
5) Upload job information to HDFS
6) Submit job to JobTracker


Initialization-
JobTracker-
7) Initialize job (to Job queue)
8) Read Job Files from HDFS
9) Create Maps and Reduces (as many maps as splits)

Scheduling-
TaskTrackers isend heartbeats to JobTrackers and gets jobs assigned from JobTracker

Execution-
TaskTrackers - writes output to local disk


MR Limitations: 
Message passing applications
Graph processing
Iterative applications

Maximum cluster size 4000 nodes, max concurrent tasks 40,000
Fixed map and reduce containers(slots)
















