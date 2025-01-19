# -*- coding: utf-8 -*-
"""
Name: Blain, Justin
Class: Data 430
Date: 08.17.24

Goal: Label Encoding
"""

#Import
from sklearn.datasets import fetch_openml
from sklearn.preprocessing import LabelEncoder
import pandas as pd

#Diabetes dataset from OpenML and convert to DataFrame
diabetes = fetch_openml(name='diabetes',  as_frame=True)
df = pd.DataFrame(diabetes.data, columns=diabetes.feature_names)
df['target'] = pd.Series(diabetes.target)

#Label Encoding
le = LabelEncoder()
df['target'] = le.fit_transform(df['target'])
