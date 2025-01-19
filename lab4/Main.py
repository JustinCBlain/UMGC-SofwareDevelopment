"""
Name: Blain, Justin
Class: DATA 300
Date: 08.27.24

Goal: S3 Exploration
"""

# Import
import sys
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

# Init




def sub_menu(df):
    """Given any DataFrame, offers to display stats on any of its columns or
    exit or return to the main menu."""
    # Generate and communicate column options
    print("\nWould you like to summarize one of the following columns:")
    for i in df.columns:
        print(f"\t\"{i.capitalize()}\"")
    print("Or would you like to \"exit\" the program?")
    print("Or would you like to \"return\" to the main menu?")

    # Generate and ingest column and navigation options
    options = df.columns.tolist()
    options.extend(('exit', 'return'))
    select = filter_input('\n', options)
    if select == 'exit':
        finish()
    elif select == 'return':
        main()

    # Display and loop
    stats(df[select])
    sub_menu(df)


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
    print("Please make a selection from the following:")
    print("""
    1. \"Create\" S3 bucket
    2. \"Put\" object into bucket
    3. \"Remove\" an object from a bucket
    4. \"Delete\" a bucket
    5. \"Copy\" an object from one bucket into another
    6. \"Downloads\" an object from a bucket
    7. \"Exit\" the program""")

    select = filter_input('\n', ['population', 'housing', 'exit'])

    if select == 'population':
        sub_menu(pop)
    elif select == 'housing':
        sub_menu(house)
    elif select == 'exit':
        finish()

print('Hello and welcome!')
main()
