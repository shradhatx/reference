##  Machine Learning
ML k-means - unsupervised learning algorithms
k-means assigns a doc to any one of k topics
whereas LDA assigns a doc to one or more topics

Gaussian Mixture Model (GMM) is like LDA - soft clustering

Latent Dirichlet Allocation (LDA)
Taxonomy construction i.e. ontology or knowledge about the topics of our interest

The goal is to find a topic structure that is as complete as possible to cover as many semantic concepts.

Gather training data that is high quality labeled data. Human annotation is prohibitive for many reasons.  Expensive for scale we require and could be noisy (cognitive overload), biased based on expertise


There are techniques for labeled data aquisition-

see http://cobweb.cs.uga.edu/~squinn/mmd_s15/papers/p1907-yang.pdf


The labeled data acquisition pipeline is illustrated as Figure 3. First, tweets are streamed through a set of topic priors to prefilter tweets that are weakly relevant to each topic. These topic priors are white-list rules that include: User-level priors: users who tweet predominantly about a single topic (e.g., @ESPN about “Sports”). Entity-level priors: unambiguous named entities and hashtags that are highly concentrated to one or a few topics (e.g., #NBA about “Basketball”), see Section 5.2. URL-level priors: for tweets containing URLs, the URL string usually contains topic information about the webpage it links to (e.g., sports.yahoo.com/nba/*.html).

IoTDoneQuick
http://iotdonequick.com/2016/11/10/the-perfect-big-data-platform-my-blueprint/



=== ML Interview
1 - Business Understanding  - ( What comprises a machine learning problem vs. Otherwise)
  
2 - Data Understanding – Data Characteristics and Catalog Management platform skills ( Ontologies, ERD, Repos)


3 - Data preparation – Exposure to Data Cleansing Tools ( Informatica, Python, Perl, Hadoop Hive, Scala, Spark)



4 - Modeling – Understanding of Modeling Ensembles and Platform components supporting the same ( PMML , Model Support formats )  



5 - Validation – Understanding of Scoring frameworks around ML ( PMML, other)



6 - Deployment – Microservices , OpenSource, Big Data , Spark
