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






"""params_NB = {'var_smoothing': np.logspace(0,-9, num=100)}
gs_NB = GridSearchCV(estimator=clf,
                 param_grid=params_NB,
                 cv= ShuffleSplit(n_splits=5, test_size=0.2, random_state=42),
                 verbose=1,
                 scoring='accuracy')
gs_NB.fit(X_train, y_train)

print(gs_NB.best_params_)"""

"""print(df.shape)"""

"""df = df[['funding_rounds', 'has_roundA', 'has_roundB', 'has_roundC',
    'has_roundD', 'relationships', 'avg_participants', 'milestones',
    'age_first_milestone_year', 'age_last_milestone_year', 'status']]
"""
"""plt.figure(figsize=(20,12))
col_list = list(df.columns)
sns.displot(df, hue="col_list", kind="kde", fill=True)
plt.title('Distributions')
plt.show()"""



"""
X = df.drop(['status'], axis=1)
scaler = StandardScaler()
scaler.fit(X)
X = scaler.transform(X)

y = df['status'].values
y = LabelEncoder().fit_transform(y)


LR = LogisticRegression(C=0.01, solver='liblinear').fit(X_train,y_train)
yhat = LR.predict(X_test)"""




"""list = list(range(1,10))
cv_scores = []

for x in list:
    LR = LogisticRegression(C=0.01, solver='liblinear', max_iter=x).fit(X_train,y_train)
    scores = cross_val_score(LR, X_train, y_train, cv=5, scoring='accuracy')
    cv_scores.append(scores.mean())

MSE = [1 - x for x in cv_scores]

plt.figure(figsize=(15, 10))
plt.title('The optimal value', fontsize=20, fontweight='bold')
plt.xlabel('Value', fontsize=15)
plt.ylabel('Misclassification Error', fontsize=15)

plt.plot(list, MSE)

plt.show()

print(LR.get_params())"""


"""accuracy = accuracy_score(y_test, yhat)
precision = precision_score(y_test, yhat)
recall = recall_score(y_test, yhat)
f1 = f1_score(y_test, yhat)

print(f"Accuracy: {accuracy}")
print(f"Precision: {precision}")
print(f"Recall: {recall}")
print(f"F1-Score: {f1}")"""




"""sns.relplot(x="age_first_milestone_year", y="relationships", hue="status",
            size="funding_rounds", sizes=(40, 400), alpha=.5, palette="muted",
            height=6, data=df)
sns.kdeplot(x="age_last_milestone_year", y="avg_participants", hue="status",
            palette="muted",
            height=6, data=df)
plt.show()"""



# Dataset specific manipulation
"""object_columns = df.select_dtypes(include=['object']).columns.tolist()
le = LabelEncoder()
for column in object_columns:
    df[column] = le.fit_transform(df[column])"""





"""y_pred = classifier.predict(X_test)
pd.crosstab(y_test, y_pred, rownames=['True'], colnames=['Predicted'], margins=True)
print(classification_report(y_test,y_pred))

accuracy = accuracy_score(y_test, y_pred)*100
print('Accuracy of our model is equal ' + str(round(accuracy, 2)) + ' %.')
"""

"""x_obj_col = X.select_dtypes(include=['object']).columns.tolist()
le = LabelEncoder()
for column in x_obj_col:
    X[column] = le.fit_transform(X[column])"""




"""

funding_rounds
has_roundA
has_roundB
has_roundC
has_roundD

relationships
avg_participants

milestones
age_first_milestone_year
age_last_milestone_year

is_otherstate
is_top500
closed_at


 location or sector didnt seem to matter as much
all acheived corrrelations scores above .1, have a real world intelligible meaning, and matain their meaning withou other categories that did not make the list.
"""

'''

oh_df = pd.get_dummies(df[['state_code', 'zip_code', 'id', 'city', 'Unnamed: 6',
                        'name', 'founded_at', 'closed_at', 'first_funding_at',
                        'last_funding_at', 'state_code.1', 'category_code',
                        'object_id']],
                    prefix=['state_code', 'zip_code', 'id', 'city',
                            'Unnamed: 6', 'name', 'founded_at', 'closed_at',
                            'first_funding_at', 'last_funding_at',
                            'state_code.1', 'category_code', 'object_id'])

df['status'] = label_binarize(df['status'], classes=['closed', 'acquired'])

plt.style.use('fivethirtyeight')
sns.set_style("darkgrid")
sns.barplot(sorted_corr)
plt.xticks(rotation=45, ha='right')
plt.title('Correlation Bar Plot')
plt.show()'''

"""
oh_df = pd.get_dummies(df[['state_code', 'zip_code', 'id', 'city', 'Unnamed: 6',
                        'name', 'founded_at', 'closed_at', 'first_funding_at',
                        'last_funding_at', 'state_code.1', 'category_code',
                        'object_id']],
                    prefix=['state_code', 'zip_code', 'id', 'city',
                            'Unnamed: 6', 'name', 'founded_at', 'closed_at',
                            'first_funding_at', 'last_funding_at',
                            'state_code.1', 'category_code', 'object_id'])

pipeline = Pipeline(steps=[('preprocessor', preprocessor),
                           ('regressor', LinearRegression())])

preprocessor = ColumnTransformer(
    transformers=[
        ('num', StandardScaler(), ['Unnamed: 0', 'latitude', 'longitude',
                                   'labels', 'age_first_funding_year',
                                   'age_last_funding_year',
                                   'age_first_milestone_year',
                                   'age_last_milestone_year', 'relationships',
                                   'funding_rounds', 'funding_total_usd',
                                   'milestones', 'is_CA', 'is_NY', 'is_MA',
                                   'is_TX', 'is_otherstate', 'is_software',
                                   'is_web', 'is_mobile', 'is_enterprise',
                                   'is_advertising', 'is_gamesvideo',
                                   'is_ecommerce', 'is_biotech',
                                   'is_consulting', 'is_othercategory',
                                   'has_VC', 'has_angel', 'has_roundA',
                                   'has_roundB', 'has_roundC', 'has_roundD',
                                   'avg_participants', 'is_top500']),

        ('cat', OneHotEncoder(), ['state_code', 'zip_code', 'id', 'city',
                                  'Unnamed: 6', 'name', 'founded_at',
                                  'closed_at', 'first_funding_at',
                                  'last_funding_at', 'state_code.1',
                                  'category_code', 'object_id', 'status'])])



X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.2, random_state=42, stratify=y)

# Train the model
pipeline.fit(X_train, y_train)

# Predict
predictions = pipeline.predict(X_test)



scaler = StandardScaler()
scaler.fit(X_train)

X_train = scaler.transform(X_train)
X_test = scaler.transform(X_test)

correlations = X.corrwith(y).abs()
sorted_corr = correlations.sort_values()

plt.style.use('fivethirtyeight')
sns.set_style("darkgrid")
sns.barplot(sorted_corr)
plt.xticks(rotation=45, ha='right')
plt.title('Correlation Bar Plot')
plt.show()"""