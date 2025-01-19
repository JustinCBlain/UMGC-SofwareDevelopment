"""
Name: Blain, Justin
Class: Data 430
Date: 07.30.24

Goal: Decision Classifier
"""
import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

from sklearn.tree import DecisionTreeClassifier, plot_tree
from sklearn.metrics import classification_report, confusion_matrix, \
    ConfusionMatrixDisplay
from sklearn.preprocessing import LabelEncoder
from sklearn.model_selection import train_test_split, GridSearchCV

# Set Style
plt.style.use('fivethirtyeight')
sns.set_style("darkgrid")

# Import
df = pd.read_csv('ONLINE EDUCATION SYSTEM REVIEW.csv')
print(df.info())

"""# Label Encode
object_columns = df.select_dtypes(include=['object']).columns.tolist()
le = LabelEncoder()
for column in object_columns:
    df[column] = le.fit_transform(df[column])

# Correlation Heatmap
sns.heatmap(df.corr().abs(), annot=True, cmap='RdYlGn', fmt='.2f', xticklabels=True, yticklabels=True)
plt.xticks(rotation=45, ha='right')
plt.yticks(rotation=45, va='top')
plt.title('Correlation Heatmap')
plt.show()"""

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
                   'Performance in online']

X = np.asarray(df[feature_cols])
y = np.asarray(df['Your level of satisfaction in Online Education'])
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2,
                                                    random_state=42)

# Train
clf = DecisionTreeClassifier()
clf = clf.fit(X_train, y_train)

# Test
y_pred = clf.predict(X_test)
print(confusion_matrix(y_test, y_pred))
print(classification_report(y_test, y_pred))

# Measure
print(confusion_matrix(y_test, y_pred))
print(classification_report(y_test, y_pred))

cm = confusion_matrix(y_test, y_pred)
cm_display = ConfusionMatrixDisplay(confusion_matrix=cm,
                                    display_labels=["Bad", "Average", "Good"])
cm_display.plot()
plt.show()

"""# Tune
param_grid = {'criterion' :['gini', 'entropy', 'log_loss'],
              'max_features': ['sqrt', 'log2'],
              'ccp_alpha': [0.1, .01, .001], # postpruning
              'max_depth' : [1,2,3,4,5, 6, 7], # prepruning
              'min_samples_split': [3,4,5,6,7],
              'min_samples_leaf': [1, 2, 4]
             }
gs_clf = DecisionTreeClassifier(random_state=42)
grid_search = GridSearchCV(estimator=gs_clf, param_grid=param_grid, cv=5, verbose=True)
grid_search.fit(X_train, y_train)

print (grid_search.best_estimator_)"""

# Retrain
max_clf = DecisionTreeClassifier(ccp_alpha=0.001, criterion='entropy', max_depth=5,
                       max_features='sqrt', min_samples_split=5,
                       random_state=42) #Hard coded for expedience on repeat runs
max_clf.fit(X_train, y_train)
y_pred = max_clf.predict(X_test)

# Remeasure
print(confusion_matrix(y_test, y_pred))
print(classification_report(y_test, y_pred))

cm = confusion_matrix(y_test, y_pred)
cm_display = ConfusionMatrixDisplay(confusion_matrix=cm,
                                    display_labels=["Bad", "Average", "Good"])
cm_display.plot()
plt.show()

# Tree Visualisation
classnames=["Bad", "Average", "Good"]
fig = plt.figure(figsize=(25,20))
viz = plot_tree(max_clf,
                   feature_names=feature_cols,
                   class_names=classnames,
                   filled=True)
plt.show()
fig.savefig("decision_tree.png")
