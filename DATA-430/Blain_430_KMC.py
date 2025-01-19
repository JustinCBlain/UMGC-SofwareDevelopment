"""
Name: Blain, Justin
Class: Data 430
Date: 08.04.24

Goal: KMeans Clustering
"""
import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

from sklearn.cluster import KMeans
from sklearn.metrics import *
from sklearn.preprocessing import LabelEncoder, StandardScaler
from sklearn.model_selection import train_test_split, GridSearchCV, \
    cross_val_score
from kneed import KneeLocator

# Set Style
plt.style.use('fivethirtyeight')
sns.set_style("darkgrid")

# Import
df = pd.read_csv('ONLINE EDUCATION SYSTEM REVIEW.csv')
print(df.info())

# Correlation Heatmap
sns.heatmap(df.corr().abs(), annot=True, cmap='RdYlGn', fmt='.2f', xticklabels=True, yticklabels=True)
plt.xticks(rotation=45, ha='right')
plt.yticks(rotation=45, va='top')
plt.title('Correlation Heatmap')
plt.show()

# Manual Encoding
df['Your level of satisfaction in Online Education'].replace('Bad', 0, inplace=True)
df['Your level of satisfaction in Online Education'].replace('Average', 1, inplace=True)
df['Your level of satisfaction in Online Education'].replace('Good', 2, inplace=True)
df[['bottom_range', 'top_range']] = df['Average marks scored before pandemic in traditional classroom'].str.split('-', expand=True)

# Split
feature_cols = ['Age(Years)',
                   'top_range',
                   'bottom_range',
                   'Your interaction in online mode',
                   'Clearing doubts with faculties in online mode',
                   'Performance in online',
                   'Your level of satisfaction in Online Education']

X = df[feature_cols]
X = X.drop(['Your level of satisfaction in Online Education'], axis=1)
scaler = StandardScaler()
X = scaler.fit_transform(X)

y = np.asarray(df['Your level of satisfaction in Online Education'])
y_2d = y.reshape(-1, 1)


# Visualize Clusters
g = sns.PairGrid(df[feature_cols], diag_sharey=False, hue='Your level of satisfaction in Online Education',)
for ax in g.axes.flatten():
    ax.tick_params(rotation = 45)
g.map_upper(sns.scatterplot, s=15)
g.map_lower(sns.kdeplot, lw=2)
g.map_diag(sns.kdeplot, lw=2)

# Train
clf = KMeans(n_clusters=6)
clf = clf.fit(X)

# Test & Measure
print("Homogeneity: %0.3f" % homogeneity_score(y, clf.labels_))
print("Completeness: %0.3f" % completeness_score(y, clf.labels_))
print("V-measure: %0.3f" % v_measure_score(y, clf.labels_))
print("Silhouette: %0.3f" % silhouette_score(y_2d, clf.labels_))

# Tune
kmeans_kwargs = {
    "init": "random",
    "n_init": 10,
    "max_iter": 300,
    "random_state": 42}

sse = []
silhouette_coefficients = []
for k in range(2, 11):
    kmeans = KMeans(n_clusters=k, **kmeans_kwargs)
    kmeans.fit(X)
    sse.append(kmeans.inertia_)
    score = silhouette_score(y_2d, kmeans.labels_)
    silhouette_coefficients.append(score)

plt.plot(range(2, 11), sse)
plt.xticks(range(2, 11))
plt.xlabel("Number of Clusters")
plt.ylabel("SSE")
plt.show()

kl = KneeLocator(range(2, 11), sse, curve="convex", direction="decreasing")
print(kl.elbow)

plt.plot(range(2, 11), silhouette_coefficients)
plt.xticks(range(2, 11))
plt.xlabel("Number of Clusters")
plt.ylabel("Silhouette Coefficient")
plt.show()

clf = KMeans(algorithm='elkan', max_iter=500, n_clusters=6, n_init=50)
clf = clf.fit(X)

#Remeasure
print("Homogeneity: %0.3f" % homogeneity_score(y, clf.labels_))
print("Completeness: %0.3f" % completeness_score(y, clf.labels_))
print("V-measure: %0.3f" % v_measure_score(y, clf.labels_))
print("Silhouette: %0.3f" % silhouette_score(y_2d, clf.labels_))
