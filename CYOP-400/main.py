"""
Name: Blain, Justin
Class: DATA 300
Date: 08.27.24

Goal: S3 Exploration
"""

# Import
import sys
from input import secure_input
import boto3

# Exit
def finish():
    """
        Prints exit message and exits
        """
    print("So long, and thanks for all the fish!")
    sys.exit()

# Main
def main():
    """
       Main Menu
       """
    print("""
    Please make a selection from the following:
    1. \"Create\" S3 bucket
    2. \"Put\" object into bucket
    3. \"Remove\" an object from a bucket
    4. \"Delete\" a bucket
    5. \"Copy\" an object from one bucket into another
    6. \"Downloads\" an object from a bucket
    7. \"Exit\" the program""")

    select = secure_input('\n', ['Create','Put','Remove','Delete','Copy',
                                 'Downloads','Exit'])

    if select == 'Create':
        s3 = boto3.client('s3')
        input("Name of bucket: ")
        s3.create_bucket(Bucket='my-bucket')
    elif select == 'Put':
        print("put")
    elif select == 'Remove':
        print("remove")
    elif select == 'Delete':
        print("delete")
    elif select == 'Copy':
        print("copy")
    elif select == 'Downloads':
        print("dl")
    elif select == 'Exit':
        finish()

print('Hello and welcome!')
main()
