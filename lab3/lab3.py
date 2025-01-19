"""
Name: Blain, Justin
Class: SDEV 300
Date: 03.26.24

Goal: State Database
"""

# Import
import csv
import operator
import sys

from PIL import Image
import matplotlib.pyplot as plot

# Ingest
states_list = []
with open("states.csv", encoding="utf-8") as states_file:
    reader = csv.reader(states_file)
    for row in reader:
        new_state = {"code": row[0],
                     "name": row[1],
                     "capitol": row[2],
                     "population": int(row[3].replace(",", "")),
                     "flower": row[4]}
        states_list.append(new_state)


def sortify(raw_dict, attribute='name'):
    """
    Sorts any collection(?) of dictionaries by a given attribute.

    Parameters
    ----------
    raw_dict: collection
    attribute : str
        key by which to sort

    Returns
    -------
    list
        sorted dictionaries
    """
    sorted_states_list = sorted(list(raw_dict),
                                key=operator.itemgetter(attribute))
    return sorted_states_list


def filter_input(input_prompt="Please make valid a selection:",
                 input_type=str, input_options=None):
    """
    Ingests user input; rejects and reprompts as necessary.

    Returns
    -------
    str or int
        Valid Input

    """
    if input_options is None:
        input_options = []
    input_value = input(input_prompt)
    if isinstance(input_value, str) and input_type == str:
        input_options = [string.lower() for string in input_options]
        input_value = input_value.lower()
    while input_value not in input_options:
        print("Invalid input")
        input_value = input(input_prompt)
    return input_value


def display(state):
    """
    Displays attributes of a state (or other dict)

    Parameters
    ----------
    state: dict
    """
    next(iter(state))
    for key, value in state.items():
        if isinstance(value, int):
            value = format(value, ',d')
        print(f"{key.capitalize()}: {value}")


def show_one():
    """
       Searches for and displays attributes of a state and its flower
       """
    codes_list = [d['code'] for d in states_list]
    search = filter_input('Please enter the 2 letter code of the '
                          'state you\'re looking for:',
                          input_options=codes_list)
    state = next(state for state in states_list
                 if state['code'].casefold() == search.casefold())
    display(state)
    flower_pic = Image.open(str("state_flowers/" + state['code'] + ".jpg"))
    flower_pic.show(title=state['flower'])
    choice = filter_input("Would you like to \"return\" or \"edit\" "
                          "this state?", input_options=['return', 'edit'])
    if choice == 'return':
        main()
    elif choice == 'edit':
        edit(state)


def show_all():
    """
       Displays alphabetized list of state summaries
       """
    new_list = sortify(states_list)
    for state in new_list:
        display(state)
        print()
    main()


def edit(state):
    """
       Edits attributes of a state (or other dict)

       Parameters
       ----------
       state: dict
       """
    target_attr = filter_input("What attribute would you like to modify?",
                               input_options=state.keys())
    new_value = input("What would you like the new value to be?")
    target_type = type(state[target_attr])
    try:
        new_value = target_type(new_value)
    except ValueError:
        print("Type mismatch")
    finally:
        state[target_attr] = new_value
    display(state)
    main()


def show_populous():
    """
       Displays graph of top five most populous states.
       """
    new_list = sortify(states_list, 'population')
    top_five = new_list[-5:]
    pop_list = []
    name_list = []
    for state in top_five:
        pop_list.append(state['population'])
        name_list.append(state['name'])

    plot.bar(name_list, pop_list)
    plot.xlabel("States")
    plot.ylabel("Population")
    plot.show()
    main()


def prog_exit():
    """
       Prints exit message and exits.
       """
    print("So long, and thanks for all the fish!")
    sys.exit()


def main():
    """
       Main Menu
       """
    print("Please make a selection from the following")
    print("""
    1. \"Display\" all U.S.States
    2. \"Search\" for and edit a specific state\'s info
    3. \"Graph\" the most populous states
    4. \"Exit\" the program""")

    select = filter_input('', input_options=['display', 'search',
                                             'graph', 'exit'])
    if select == 'display':
        show_all()
    elif select == 'search':
        show_one()
    elif select == 'graph':
        show_populous()
    elif select == 'exit':
        prog_exit()


print('Hello and welcome!')
main()
