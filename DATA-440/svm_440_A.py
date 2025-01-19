"""
Name: Blain, Justin
Class: Data 430
Date: 08.23.24

Goal: Support Vector Machine
"""

#Import
import matplotlib.pyplot as plt
import pandas as pd

from sklearn import svm
from sklearn.model_selection import train_test_split, GridSearchCV
from sklearn.metrics import (classification_report,confusion_matrix,
                             ConfusionMatrixDisplay)
from sklearn.datasets import fetch_openml
from sklearn.preprocessing import LabelEncoder

#Load Data
#All data property of National Institute of Diabetes and Digestive and Kidney Diseases
diabetes = fetch_openml(name='diabetes',  as_frame=True)
df = pd.DataFrame(diabetes.data, columns=diabetes.feature_names)
df['target'] = pd.Series(diabetes.target)

#Label Encoding
le = LabelEncoder()
df['target'] = le.fit_transform(df['target'])

target_column = ['class']
#derive the list of predictor column id's
predictors = list(set(list(diabetes.frame.columns))-set(target_column))
#standardize the predictors by dividing by the maximum
diabetes.frame[predictors] = diabetes.frame[predictors]/diabetes.frame[predictors].max()
#provide summary statistics for the dataframe
diabetes.frame.describe().transpose()

#the input data
X = diabetes.frame[predictors].values
#the output data
y = diabetes.frame[target_column].values

#we encode target classes from strings to numbers as neural networks cannot
# require all numerical inputs and outputs
from sklearn.preprocessing import LabelEncoder
le = LabelEncoder()
y = le.fit_transform(y)

#divide data into training and test sets
X_train, X_test, y_train, y_test = train_test_split(X, y,
                                                    test_size=0.30)
print(X_train.shape); print(X_test.shape)

svm_model = svm.SVC(random_state=42)
svm_model.fit(X_train, y_train)

#We predict the training set
predict_train = svm_model.predict(X_train)
#we predict the test set
predict_test = svm_model.predict(X_test)

print('Training accuracy')
#we report the confusion matrix for the training set
print(confusion_matrix(y_train,predict_train))
#we report various accuracy statistics for the training set
print(classification_report(y_train,predict_train))

print('Testing accuracy')
#we report the confusion matrix for the test set
print(confusion_matrix(y_test,predict_test))
#we report various accuracy statistics for the test set
print(classification_report(y_test,predict_test))

# Tune
param_grid = {'kernel' :['linear', 'poly', 'rbf', 'sigmoid'],
              'decision_function_shape': ['ovo', 'ovr']
             }

best_svm = svm.SVC(random_state=42)
grid_search = GridSearchCV(estimator=best_svm, param_grid=param_grid, cv=10,
                           verbose=True)
grid_search.fit(X_train, y_train)

new_clf = grid_search.best_estimator_
print (new_clf)

new_clf.fit(X_train, y_train)
y_pred = new_clf.predict(X_test)

# Remeasure
cm = confusion_matrix(y_test, y_pred)
print(cm)
print(classification_report(y_test, y_pred))

#Confusion Matrix

cm_display = ConfusionMatrixDisplay(confusion_matrix=cm,
                                    display_labels=["tested_negative",
                                                    "tested_positive"])
cm_display.plot()
plt.show()
