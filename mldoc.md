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

IgniteD2K - lead generation by integrating social data, blending structured (dbusa) / unstructured data and complex event processing - to predict Propensity Index (PI) or probability of taking some action. 200 MB every 10 minutes (about 50 GB daily)
We used many latest technologies in Hadoop ecosystem: Cassandra & Hbase, CQL and Phoenix, Elastic search and ELK stack, Spark and Scala,
Statistical & predictive algorithms and Python/Pandas/numpy/scikit-learn/seaborn
• Topic modeling -LDA (see paper by David Blei) (NaiveBayes, SVM-support vector machine), k-means
• Predictive modeling - Logistic Regression, Random Forest/ensemble
• Use of bag of words (can be enhanced by- Rule based sentiment extraction
combined with keywords. Sentence level sentiment analysis, feature based sentiment, negation support)
ML models: Supervised: Classification(discrete values),Regression(continuous) Unsupervised: Clustering - used for recommendation (tried Kaggle competition)
Cleanse - categorize var fields, keep useful features, fillna and innova- take care of empty fields. Test model - scatter plot, MSE. Use k-fold- x-validation 80/20 split data train/test.

## Data analytics & ML
￼￼
• Predictive modeling: (Logistic Regression, Decision Trees, Random Forest),
• ML: clustering: (Topic modeling- LDA, K means, SVM, classification or categorization: (naive
bayes),
Text modeling: Text classifier TF/IDF & vector space model on TF/IDF
•
• Recommendation engine. Collaborative filtering: (recommendations- user based/item
based / model based) multi-dimensional, context aware • Sentiment analysis (bag of words)
Seaborn: For plotting ex: FacetGrid, boxplot, violinplot, kdeplot, pairplot


## technology description of work done at ignited2k
Python program- subscribe to twitter feed (using OAuth); transform & extract relevant fields from twitter blob; filter (spambot), process primitive fields and put the relevant info on kafka.
Spark streaming- read kafka, apply statistical model/ ML, apply business logic(in scala) and call API to store to Hbase and to ES.
Index data to make them searchable in ES, aggregate , create analytical dashboard to reveal trend in kibana.
UI in angularJS calling REST APIs- fetching data from Hbase or ES
*filter spambot (look for few followers, large number of hashtags, short text)

## See: https://github.com/luvgupta008/Scream...
~/resources/reference.conf , application.conf
( Scala code connecting to twitter api, spark stream, relay to es, kibana dashboard refreshed 10 sec gives realtime viz- ex top hashtag (filter spam, language))




