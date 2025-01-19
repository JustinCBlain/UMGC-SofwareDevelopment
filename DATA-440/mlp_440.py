"""
Name: Blain, Justin
Class: DATA 440
Date: 09.02.24

Goal: Neural Network
"""

#Import
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from sklearn.neural_network import MLPClassifier
from sklearn.model_selection import train_test_split, GridSearchCV
from sklearn.metrics import (classification_report,confusion_matrix,
                             ConfusionMatrixDisplay)
from sklearn.datasets import fetch_openml
from sklearn.preprocessing import LabelEncoder, StandardScaler

# https://github.com/jzliu-100/visualize-neural-network
import VisualizeNN as VisNN

#Load Data
#All data property of National Institute of Diabetes and Digestive and Kidney Diseases
diabetes = fetch_openml(name='diabetes', as_frame=True)
df = pd.DataFrame(diabetes.data, columns=diabetes.feature_names)
df['target'] = pd.Series(diabetes.target)

#Drop zero's
df = df[(df != 0).all(1)]

#Label Encoding
le = LabelEncoder()
df['target'] = le.fit_transform(df['target'])

target_column = ['target']
#derive the list of predictor column id's
predictors = list(set(list(df.columns))-set(target_column))
#provide summary statistics for the dataframe
diabetes.frame.describe().transpose()

#the input data
X = df[['plas', 'mass', 'age', 'pedi', 'pres']].values
scaler = StandardScaler()
X = scaler.fit_transform(X)
#the output data
y = df[target_column].values

#we encode target classes from strings to numbers as neural networks cannot
# require all numerical inputs and outputs
le = LabelEncoder()
y = le.fit_transform(y)

#divide data into training and test sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.30)
print(X_train.shape); print(X_test.shape)

mlp = MLPClassifier(random_state=42, hidden_layer_sizes=(20,20),
                    activation='relu', alpha=1, solver='adam', max_iter=1500)
mlp.fit(X_train, y_train)

#We predict the training set
predict_train = mlp.predict(X_train)
#we predict the test set
predict_test = mlp.predict(X_test)

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

"""# Tune
param_grid = {
    'hidden_layer_sizes': [(20,20),(15,15),(15,15,15),(15,10,10)],
    'activation': ['identity', 'logistic','tanh', 'relu'],
    'solver': ['lbfgs','sgd', 'adam'],
    'alpha': [1, 0.1],
    'learning_rate': ['constant', 'invscaling','adaptive'],
}

best_mlp = MLPClassifier(max_iter=300,random_state=42)
grid_search = GridSearchCV(estimator=best_mlp, param_grid=param_grid, cv=5,
                           verbose=True)
grid_search.fit(X_train, y_train)

new_clf = grid_search.best_estimator_
print (new_clf)"""

# Hard coded for expedience
new_clf = MLPClassifier(activation='logistic', alpha=1,
                        hidden_layer_sizes=(20, 20), max_iter=2000,
                        random_state=42, solver='lbfgs')

new_clf.fit(X_train, y_train)
y_pred = new_clf.predict(X_test)

# Remeasure
cm = confusion_matrix(y_test, y_pred)
print(cm)
print(classification_report(y_test, y_pred))

#Confusion Matrix
cm_display = ConfusionMatrixDisplay(confusion_matrix=cm,
                                    display_labels=["negative", "positive"])
cm_display.plot()
plt.show()

# Show Neural Network Structure
network_structure = np.hstack(([X.shape[1]],
                               np.asarray(new_clf.hidden_layer_sizes), 1))
network=VisNN.DrawNN(network_structure, new_clf.coefs_)
network.draw()
print([X.shape[1]])