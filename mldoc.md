##  Machine Learning
ML k-means - unsupervised learning algorithms
k-means assigns a doc to any one of k topics or clusters
whereas LDA assigns a doc to one or more topics

Gaussian Mixture Model (GMM) is like LDA - soft clustering

Latent Dirichlet Allocation (LDA)
Taxonomy construction i.e. ontology or knowledge about the topics of our interest

The goal is to find a topic structure that covers as many semantic concepts.

Gather training data that is high quality labeled data. Human annotation is prohibitive for many reasons.  Expensive for scale and could be noisy (cognitive overload), biased based on expertise.  Techniques: white lists like user-level prior and entity-level prior.

There are techniques for labeled data aquisition-

see http://cobweb.cs.uga.edu/~squinn/mmd_s15/papers/p1907-yang.pdf

The labeled data acquisition pipeline is illustrated as Figure 3. First, tweets are streamed through a set of topic priors to prefilter tweets that are weakly relevant to each topic. These topic priors are white-list rules that include: User-level priors: users who tweet predominantly about a single topic (e.g., @ESPN about “Sports”). Entity-level priors: unambiguous named entities and hashtags that are highly concentrated to one or a few topics (e.g., #NBA about “Basketball”), see Section 5.2. URL-level priors: for tweets containing URLs, the URL string usually contains topic information about the webpage it links to (e.g., sports.yahoo.com/nba/*.html).

IoTDoneQuick
http://iotdonequick.com/2016/11/10/the-perfect-big-data-platform-my-blueprint/

=== ML Interview
1 - Business Understanding  - ( What comprises a machine learning problem vs. Otherwise)
  
2 - Data Understanding – Data Characteristics and Catalog Management platform skills ( Ontologies, ERD, Repos)

3 - Data preparation – Exposure to Data Cleansing Tools ( Informatica, Python, Perl, Hadoop Hive, Scala, Spark)

4 - Modeling – Understanding of Modeling Ensembles and Platform components supporting the same ( PMML-sharing models , Model Support formats )  

5 - Validation – Understanding of Scoring frameworks around ML (x-validation 80/20 split train/test data, bootstrapping, many factor to assess quality of model )

6 - Deployment – Microservices , OpenSource, Big Data , Spark

IgniteD2K - predictive modeling, lead generation by integrating social data, blending structured (dbusa) / unstructured data and complex event processing - to predict Propensity Index (PI) or probability of taking some action. 200 MB every 10 minutes (about 50 GB daily)
We used many latest technologies in Hadoop ecosystem: Cassandra & Hbase, CQL and Phoenix, Elastic search and ELK stack, Spark and Scala,
Statistical & predictive algorithms and Python/Pandas/numpy/scikit-learn/seaborn/matplot lib

• Topic modeling -LDA (see paper by David Blei) (NaiveBayes, SVM-support vector machine), k-means
• Predictive modeling - Logistic Regression, Random Forest/ensemble
• Use of bag of words (can be enhanced by- Rule based sentiment extraction combined with keywords. Sentence level sentiment analysis, feature based sentiment, negation support)
ML models: Supervised: Classification(discrete values),Regression(continuous) Unsupervised: Clustering - used for recommendation (tried Kaggle competition)
Cleanse - categorize var fields, keep useful features, fillna and innova- take care of empty fields. Test model - scatter plot, MSE. Use k-fold- x-validation 80/20 split data train/test.

Aspects that determine the quality of predictions: accuracy(the level of agreement between the forecast and the truth), association, skill(the relative accuracy of the forecast over some reference forecast) 

PMML stands for "Predictive Model Markup Language". It is the open standard for sharing predictive solutions. A PMML file, in xml format, contains data transformations (pre- and post-processing) as well as one or more predictive models.

Pickle in python is a standard way to serialize objects. You can use the pickle operation to serialize your machine learning algorithms and save the serialized format to a file.Later you can load this file to deserialize your model and use it to make new predictions.
See-
http://machinelearningmastery.com/save-load-machine-learning-models-python-scikit-learn/

See- to cleanup a csv file in pySpark
https://www.nodalpoint.com/spark-data-frames-from-csv-files-handling-headers-column-types/
See downloaded file with pysaprk commands : ~\Downloads\Spark_DF_from_CSV.*  on personal mac 

Creating table from a csv file-
In python pandas - read.csv
In R - fread or ff or LaF for Large files. Don't have to worry about data not fitting into memory or inferring data type
Header row provides a good field name for starter

Creating table from json or parquet -

See- for IoT Node-RED OracleNoSQL example in python  to load db. Very good blogs on various topics DevOps, Ansible. Vagrant, 
SparkML
https://www.nodalpoint.com/raspberry-pi-oracle-nosql-node-red/

check: Node-RED
DBScan sklearn.cluster.DBSCAN for clustering from vector array
TensorFlow: Image & ML : opensource graphs & ML by Google Brain or neural n/w team: nodes and edges
https://databricks.com/blog/2016/01/25/deep-learning-with-apache-spark-and-tensorflow.html
Use Spark on a cluster of machines to improve deep learning pipelines with TensorFlow

Spark to find the best set of hyperparameters for neural network training
Spark to apply a trained neural network model on a large amount of data.

* The TensorFlow ML library automates the creation of training algorithms for neural networks of various shapes and sizes.

Process of building a neural network is more complicated than just running some function on a dataset. 

Image or sound byte converted to a vector.
TensorFlow itself is not distributed, the hyperparameter tuning process is highly distributed. 

Tensorflow utilizing GPU's (Graphical Processing Units) - multi cores (100s) more performant(than CPU) as multithreaded.  

* Python Frontend to TensorFlow. On top of Python there are Layers(to build models), Estimator and Keras(train & evaluate model).

XLA - TensorFlow compiler; Ahead of time OR Just in time

Classical algorithms: k-means, SVM, Random Forest/ Decision Trees

DGX-1 with 8 GPUs to K80 

Use case to share with Sameer: Classification of MRI based brain networks to diagnose psychiatric disorders and plan treatment
Keep an eye on TensorFlow for large scale image analysis for efficiency and ease of use.

Youtube: Tutorial TensorFlow by Sherry Moore
https://github.com/sherrym/tf-tutorial/

https://github.com/tensorflow/models

Neuro-net made of neurons take data, do computations (Convolution, translation) like matrix multiplication etc and pass it on to next neuron. A tensor is a (multi-dimensional array. 
What is TPU? (tensor processing units - application specific chip)
Good practice - keep code modular (keep apis consistent) and you can easily distribute work.

Ex: use jupyter notebook

Linear Regression problem-
y = W*x + b
Given lots of samples of y and x you can determine W(weight) and b(Bias)
For model: you need input or data, build inference graph, training operation where you define loss and optimizer, and then train to minimize loss.

Liner Regression: Create graph, start Session, run graph, minimize loss.
Image recognition mnist problem: Given enough images and labels. figure out weight and biases so the model can correctly identify digits.

Define loss function, use optimizer.
Use global step and use checkpoint to save to placeholder with saver so you can pick up building of training model where you have left off.
logist with mnist inference
run and plot loss value and see it is going down with each step
Now feed the image after loading the checkpoint and evaluate with truth.

Tip: Highly suggest upgrading workstation with a new gen GPU, either Vega or 1080TI. 
May be NVIDIA, many frameworks still only support CUDA. TitanX and the 1080Ti are comparable. The ~12GB of memory is great for larger models and CNNs.

Tensor for Time Series
RNN- Recurrence Neural Net












 










Kaggel competition:
Titanic
Loan defaulters
Pima Indians onset of Diabetes dataset
??
??


## Data analytics & ML
entropy is disorder or uncertainility or randomness
￼￼
• Predictive modeling: (Logistic Regression, Decision Trees, Random Forest),
• ML: clustering: (Topic modeling- LDA, K means, SVM, classification or categorization (naive bayes)

SVM - supervised learning for both classification and regression. SVMs are based on the idea of finding a hyperplane that best divides a dataset into two classes.
Support vectors are the data points nearest to the hyperplane, the points of a data set that, if removed, would alter the position of the dividing hyperplane. hyperplane is a line that linearly separates and classifies a set of data.
SVM Pros- accuracy with cleaner data set  Cons-data is noisy, training time can be high
SVM is used for sentimet analysis, text classification, spam detection

http://www.kdnuggets.com/

A/B test


Text modeling: Text classifier TF/IDF & vector space model on TF/IDF
• Recommendation system
> collaborative system
> content based system

python has a library called lightfm that uses hybrid approach using warp ( Weighted Approximate Rank Pairwise) as loss(measures the difference between predicted and actual) uses the gradient decent

Seaborn: For plotting ex: FacetGrid, boxplot, violinplot, kdeplot, pairplot

Neural network - for adaptive learning or ability to self organize or create the representation of information. Ex: speech recognition, image recognition

* Cognitive computing
4 cognitive capabilities to interact: vision, speech, data and language.

Programmable system - mathematical system - precise results are rigid based on rules and decisions

Cognitive systems learn and not programmed or rule based but evidence based.

Human intelligence: observe,interpret & generate hypothesis,evaluate.

Augmented intelligence: augmentation of human expertise- helps to make best decision possible. Ex: Throw lots of information/ and nothing is missed in decision making.

Corpus of knowledge is curated (disregarding dated and useless info) before it can be made useful.

Watson is good at NLP speed & semantics - language - intent - extract logical inferences.
Watson’s interacts well with speech and natural language, nuances and understands many languages.  It learns from interaction or responses

It scores the hypothesis - (similar to ES)
Uses statistical modeling for scoring

* Elasticsearch and unsupervised ML (anomaly detection - outliers, deviation, rare/different occurrence) with Prelert

Model picked depending on your data - ex periodicity determined - based on moving average — look for harmonics.

Show anomaly as well as hints on the reason of anomaly or influencer (bayseian way of eliminating)



## technology description of work done at ignited2k
Python program- subscribe to twitter feed (using OAuth); transform & extract relevant fields from twitter blob; filter (spambot), process primitive fields and put the relevant info on kafka.
Spark streaming- read kafka, apply statistical model/ ML, apply business logic(in scala) and call API to store to Hbase and to ES.
Index data to make them searchable in ES, aggregate , create analytical dashboard to reveal trend in kibana.
UI in angularJS calling REST APIs- fetching data from Hbase or ES
*filter spambot (look for few followers, large number of hashtags, short text)

## See: https://github.com/luvgupta008/Scream...
~/resources/reference.conf , application.conf
( Scala code connecting to twitter api, spark stream, relay to es, kibana dashboard refreshed 10 sec gives realtime viz- ex top hashtag (filter spam, language))

#### good articles on Spark ML
https://www.infoq.com/articles/apache-sparkml-data-pipelines

* Visualization-
Choose the right type of chart for your data:

Line Charts to track changes or trends over time and show the relationship between two or more variables.
Bar Charts to compare quantities of different categories.
Scatter Plots show joint variation of two data items.
Pie Charts to compare parts of a whole - used them sparingly since people have hard time comparing the area of pie slices
You can show additional variables on a 2-D plot using color, shape, and size
Use interactive dashboards to allow experiments with key variables

Q: What are some of the common data quality issues when dealing with Big Data? What can be done to avoid them or to mitigate their impact?

Big Data – Volume, Velocity, Variety, Veracity

Volume - Despite the great volume of underlying data, it is not uncommon to find out that some desired data was not captured or is not available for other reasons (such as high cost, delay in getting it, etc.). It is ironical but true that data availability continues to be a prominent data quality concern in the Big Data era.

Velocity - it incredibly hard to monitor data quality within a reasonable overhead on time and resources.  Need to re-define data quality metrics so that they are relevant as well as feasible in the real-time. Sampling can help you gain speed for the data quality efforts, but this comes at the cost of a bias

> 2016 election
assumption - past events are similar to current events
Pollsters need to get a representative sample, estimate the likelihood of a person actually voting

Q:What problems arise if the distribution of the new (unseen) test data is significantly different than the distribution of the training data?
A: The main problem is that the predictions will be wrong! Sample selection bias 
Solution: retrain the model using new data.

Issue with spam filtering and network intrusion detection, where spammers and hackers constantly change their behavior 
spam- a nuisance. Techniques - run a series of checks, build a white list/black list, content-based filters, heuristic filters, baysian filter
Bots

Also seen in - telephone company develops a model for predicting customer churn or a credit card company develops a model to predict transaction fraud. 

Q: What are bias and variance, and what are their relation to modeling data?
A: Bias is how far removed a model's predictions are from correctness, while variance is the degree to which these predictions vary between model iterations.
Both contribute to toal errors.
bias and variance are two sides of an important trade-off when building models

Q: What error metric would you use to evaluate how good a binary classifier is?

Q: What are some ways I can make my model more robust to outliers?

Q: feature engineering, a process of transforming data into features that are most relevant to the problem, is often needed.

Q: ensemble methods use multiple learning algorithms to obtain better predictive performance than could be obtained from any of the constituent learning algorithms alone.

Q: In ML what is precision, sensitivity, recall?
Sensitivity(also Recall) - measurement of performance of a model - ex classification test. Percentage of positives that are correctly identified
Specificity - percentage of negatives that are correctly identified as negative.
Precision: out of total positive predictions what percentage were correct

Recall = TP/(TP+FN)
Precision = TP/(TP+FP)

Ex: Total case 10,000
TP: case was positive and predicted positive
TN: case was negative and predicted negative
FP: case was negative but predicted positive
FN: case was positive but predicted negative



               | Predicted Negative | Predicted Positive
---------------|------------|---------------|
Negative Cases | TN: 9,760  | FP: 140 |
---------------|------------|---------------|
Positive Cases | FN: 40     | TP: 60        |


Percentage prediction correct: 'accuracy' was 9,760+60 out of 10,000 = 98.2%

What percentage of the positive did you catch: 'recall' was 60 out of 100 = 60%

What percentage of positive predictions were correct: 'precision' was 60 out of 200 = 30%


## Tableau Vs Kibana
It depends -
What does your client intend to do with the dashboard? How much interactivity do they want in place? Is the data real-time? What sort of advanced statistical analysis does your client want to run?
Tableau- Cons: slow, maintenance - unfriendly, requires restart
Pros: Gets data from multiple systems. Statistical R integration built in.
Kibana - connects to ES. Its limited but pretty interactive and minimal upfront effort to put useful and pretty dashboard,

Questions to ask USAA
what it is really like to work as a Data Scientist in practice?
Data Scientist: data analysis- making sense of data, very similar to a statistician

Data Engineer: Coders. They make use of models in application that  help users making decision.

I understand how models are built, fine tuning mehods, measure accuracy etc , but I identify as Data Engineer - using models in applications to bring benefits to end users.  

A/B testing (sometimes called split testing) is comparing two versions of a web page to see which one performs better. You compare two web pages by showing the two variants (let's call them A and B) to similar visitors at the same time. The one that gives a better conversion rate, wins!

