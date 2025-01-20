"""
Name: Blain, Justin
Class: DATA 300
Date: 04.17.24

Goal: Data Exploration
"""

# Import
import sys
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

# Init
# Ingest
try:
    pop = pd.read_csv("PopChange.csv")
    house = pd.read_csv("Housing.csv")
except OSError:
    print("Data error: Unable to read file!")
    sys.exit()

# Trim dfs down to desired columns
pop = pop.drop(columns=['Id', 'Geography', 'Target Geo Id', 'Target Geo Id2'])
house = house.drop(columns=['NUNITS', 'WEIGHT'])

# Force lowercase column headers
pop.columns = pop.columns.str.casefold()
house.columns = house.columns.str.casefold()


# FilterInput
def filter_input(input_prompt="Please make valid a selection:",
                 input_options=None, input_type=str):
    """
    Ingests user input; rejects and reprompts as necessary.

    Parameters
    ----------
    input_prompt : str
        default : "Please make a valid selection:"
        Displayed to prompt the user for input
    input_type : type
        default : int
        Filters input to ensure requested type is given
    input_options : list
        default : []
        Rejects input if not in range or in specified options list
        If empty, all inputs passing type check returned

    Returns
    -------
    input_type
        Valid Input

    """
    input_value = False
    while not input_value:
        try:
            input_value = input(input_prompt)
            input_value = input_type(input_value)

        # Type Check
        except ValueError:
            print("\nType Error!")
            print(f"Expected:{input_type}, Received: {type(input_value)}")
            input_value = False
            continue

    # Expected Option Checks

        # Returns input if options unspecified
        if input_options is None:
            return input_value
        if input_type == str:
            input_options = [string.casefold() for string in input_options]
            input_value = input_value.casefold()

        # Attempts to interpret options as a range
        try:
            if input_value not in range(input_options[0], input_options[1]):
                print("\nSelection Error!")
                print(f"Expected number between {input_options[0]} and "
                      f"{input_options[1]}, Received: {input_value}")
                input_value = False

        # Checks against specified options
        except TypeError:
            if input_value not in input_options:
                print(f"\nSelection Error!"
                      f"\nExpected one of:{input_options}"
                      f", Received: {input_value}")
                input_value = False

    # Success!
    return input_value


# Display stats
def stats(col):
    """Displays Summary statistics and histogram for DataFrame Column"""
    # Numeric Stats
    print("\nStats")
    print(f"\n\tCount: {len(col)}")
    print(f"\tMean: {col.mean().round(2)}")
    print(f"\tStd Dev: {col.std().round(2)}")
    print(f"\tMin: {col.min()}")
    print(f"\tMax: {col.max()}")

    # Histogram
    print("\n\tHistogram displaying...now.")
    sns.set_theme(style='darkgrid')
    sns.displot(col, kde=True)
    plt.show()


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
    1. Analyze the \"Population\" Data File
    2. Analyze the \"Housing\" Data File
    3. \"Exit\" the program""")

    select = filter_input('\n', ['population', 'housing', 'exit'])

    if select == 'population':
        sub_menu(pop)
    elif select == 'housing':
        sub_menu(house)
    elif select == 'exit':
        finish()


print('Hello and welcome!')
main()
