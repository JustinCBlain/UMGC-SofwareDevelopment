# Import
from __future__ import print_function
import boto3
import random
import logging
from botocore.exceptions import ClientError
from input import secure_input
import json
import decimal
import sys
from boto3.dynamodb.conditions import Attr

# Encoder
class DecimalEncoder(json.JSONEncoder):
    def default(self, o):
        if isinstance(o, decimal.Decimal):
            if abs(o) % 1 > 0:
                return float(o)
            else:
                return int(o)
        return super(DecimalEncoder, self).default(o)

dynamodb = boto3.resource('dynamodb')
s3 = boto3.client('s3')
number = str(random.randint(100000, 999999))
bucket = "dafot" + number
table = dynamodb.Table("Enemies")

def create_table():
    table = dynamodb.create_table(
        TableName="Enemies",
        KeySchema=[
            {
                'AttributeName': 'Name',
                'KeyType': 'HASH'  #Partition key
            }
        ],
        AttributeDefinitions=[
            {
                'AttributeName': 'Name',
                'AttributeType': 'S'
            }
        ],
        ProvisionedThroughput={
            'ReadCapacityUnits': 20,
            'WriteCapacityUnits': 20
        }
    )
    print("Table status:", table.table_status)
    
def add(enemies):
    table.meta.client.get_waiter('table_exists').wait(TableName='Enemies')
    for e in enemies:
        response = table.put_item(
            Item={
                'Name': e.name, 
                'Phys': e.phys,
                'Ment': e.ment,
                'Socl': e.socl,
                'HP': e.hp,
                'Y': e.y,
                'X': e.x
            }
        )
    print(f"PutItem {e} succeeded:")
    print(json.dumps(response, indent=4, cls=DecimalEncoder))

# Attempts to create a bucekt for this session
def create_bucket():
    print(f"Creating {bucket}...")
    try:
        s3.create_bucket(Bucket=bucket)
        print(f"{bucket} created succesfully\n")
    except ClientError as e:
        logging.error(e)
        print(f"{bucket} creation failed\n")
        return False
    return True

# Attempt to delete this session's bucket    
def delete():
    print(f"Deleting {bucket}...")
    try:
        response = s3.delete_bucket(Bucket=bucket)
        print(f"{bucket} deleted succesfully\n")
    except ClientError as e:
        logging.error(e)
        print(f"{bucket} deletion failed\n")
        return False
    return True

# Tries to put object into session bucket
def put(obj):
    print(f"Putting {obj} into {bucket}...")
    try:
        s3.put_object(Bucket=bucket, Key=obj.name)
        print(f"{obj} putteded succesfully\n")
    except ClientError as e:
        logging.error(e)
        print(f"{obj} putting failed\n")
        return False
    return True
    
# Tries to remove object from session bucket
def remove(obj):
    print(f"Deleting {obj} from {bucket}...")
    
    try:
        s3.delete_object(Bucket=bucket, Key=obj)
        print(f"{obj} deleted succesfully\n")
    except ClientError as e:
        logging.error(e)
        print(f"{obj} deletion failed\n")
        return False
    return True