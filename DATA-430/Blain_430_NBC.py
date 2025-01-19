"""
Name: Blain, Justin
Class: Data 300
Date: 07.14.24

Goal: Bayesian Classifier
"""
import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

from sklearn.naive_bayes import GaussianNB, BernoulliNB
from sklearn.metrics import classification_report, confusion_matrix, \
    ConfusionMatrixDisplay
from sklearn.preprocessing import StandardScaler, LabelEncoder
from sklearn.model_selection import train_test_split, cross_val_score

df = pd.read_csv('startup data.csv')


# Plotting distributions of key variables
key_df = df[['funding_rounds', 'relationships', 'avg_participants',
             'milestones', 'age_first_milestone_year',
             'age_last_milestone_year']]
key_melted = key_df.melt(var_name='variable', value_name='Value')
sns.kdeplot(data=key_melted, x='Value', hue='variable', common_norm=False)
plt.title('Distributions of Key Variables')
plt.show()

# Preprocessing
df = df.dropna()
object_columns = df.select_dtypes(include=['object']).columns.tolist()
le = LabelEncoder()
for column in object_columns:
    df[column] = le.fit_transform(df[column])

# Separating and scaling data
X = df.drop(['status', 'is_top500'], axis=1)
scaler = StandardScaler()
scaler.fit(X)
X = scaler.transform(X)
y = df['status'].values

# Training classifier
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2,
                                                    random_state=42)
clf = GaussianNB(var_smoothing=1.0)  # using indicated value from optimization
clf.fit(X_train, y_train)
yhat = clf.predict(X_test)

# Displaying metrics
print(confusion_matrix(y_test, yhat))
print(classification_report(y_test, yhat))
cm = confusion_matrix(y_test, yhat)
cm_display = ConfusionMatrixDisplay(confusion_matrix=cm,
                                    display_labels=["acquired", "closed"])
sns.set_style("darkgrid")
plt.style.use('fivethirtyeight')
cm_display.plot()
plt.show()

#Cross Validation
list = list(np.logspace(0, -9, num=100))
cv_scores = []

for x in list:
    clf = GaussianNB(var_smoothing=x).fit(X_train, y_train)
    scores = cross_val_score(clf, X_train, y_train, cv=5, scoring='accuracy')
    cv_scores.append(scores.mean())

MSE = [1 - x for x in cv_scores]

plt.figure(figsize=(15, 10))
plt.title('The optimal value', fontsize=20, fontweight='bold')
plt.xlabel('Value', fontsize=15)
plt.ylabel('Misclassification Error', fontsize=15)

plt.plot(list, MSE)

plt.show()

