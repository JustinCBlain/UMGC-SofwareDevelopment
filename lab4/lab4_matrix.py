"""
Name: Blain, Justin
Class: SDEV 300
Date: 04.02.24

Goal: Enter The Matrix
"""

# Import
import re
import sys
import numpy


def filter_input(input_prompt="Please make valid a selection:",
                 input_options=None, input_type=str, input_format=".*"):
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
    input_format : regex expression
        default : ".*"
        Specifies format of input

    Returns
    -------
    input_type
        Valid Input
    Error
        Failure message

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

        # Format Check
        input_format = re.compile(input_format)
        if input_format.match(str(input_value)):
            pass
        else:
            print("\nFormat Error!")
            print("Expected a valid format")
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


def matrix_input():
    """
        Ingests 3x3 matrix one value at a time.

        Returns
        -------
        numpy array
            3x3 array of float values
        """
    array = []
    for row in range(3):
        row = []
        for _ in range(3):
            value = filter_input("", input_type=float)
            row.append(value)
        array.append(row)
    array = numpy.array(array)
    return array


def matrix_menu(matrix_1, matrix_2):
    """
        Conduct matrix operations on two arrays

        """
    # Prompt user for operation
    print("Let's work:")
    print("""Would you like to:
        1. \"Add\" the matrices
        2. \"Sub\"tract the matrices
        3. Perform \"matrix mult\"iplication
        4. Perform element by \'element mult\"iplication
        5. \"Overwrite\" the currently entered matrices
        5. Return to the \"main\" menu
        6. \"Exit\" the program""")

    select = filter_input('\n', input_options=['add', 'sub', 'matrix mult',
                                               'element mult', 'overwrite',
                                               'main', 'exit'])

    # Perform selection operation
    if select == 'add':
        result = numpy.array(matrix_1) + numpy.array(matrix_2)
        print(summary(result))
    elif select == 'sub':
        result = numpy.array(matrix_1) - numpy.array(matrix_2)
        print(summary(result))
    elif select == 'matrix mult':
        result = numpy.matmul(matrix_1, matrix_2)
        print(summary(result))
    elif select == 'element mult':
        result = numpy.array(matrix_1) * numpy.array(matrix_2)
        print(summary(result))
    elif select == 'overwrite':
        matrix()
    elif select == 'main':
        main()
    elif select == 'exit':
        finish()
    matrix_menu(matrix_1, matrix_2)


def summary(mat):
    """
        Displays summary data about a matrix: results, transpose,
        and row and column means.

        Parameters
        ----------
        mat : array
            matrix to be summarized

        """
    print(f"The result is\n{mat}\n")
    print(f"The transpose is\n{numpy.transpose(mat)}\n")
    print(f"The rows means are: {numpy.mean(mat, axis=0)}")
    print(f"The columns means are: {numpy.mean(mat, axis=1)}")


def matrix():
    """
        Ingests 2 user defined 3x3 matrices.

        """

    # Ingest and store matrices
    print("Please enter the nine values for matrix 1 one digit at a time")
    matrix_1 = matrix_input()
    print(f"First entry:\n{matrix_1}")
    print("Please enter the nine values for matrix 2 one digit at a time")
    matrix_2 = matrix_input()
    print(f"Second entry:\n{matrix_2}")
    matrix_menu(matrix_1, matrix_2)


def finish():
    """
        Prints exit message and exits

        """
    print("So long, and thanks for all the fish!")
    sys.exit()


def main():
    """
       Main Menu
       """
    print("Please make a selection from the following")
    print("""
    1. Check a \"phone\" number's format
    2. Check a \"zip\" code's format
    3. Enter the \"matrix\"
    4. \"Exit\" the program""")

    select = filter_input('\n', input_options=['phone', 'zip',
                                               'matrix', 'exit'])

    # Checks if input is of ###-###-#### format
    # (allows for space or dotes inplace of dashes and optional parentheses)
    if select == 'phone':
        num = filter_input("Please enter a phone number (###-###-####)",
                           input_format="[(]?\\d{3}[)]?[-. ]\\d{3}[-. ]\\d{4}")
        print(f"{num} is a valid phone number.")
        main()

    # Checks if input is of #####-#### format
    elif select == 'zip':
        zip_code = filter_input("Please enter a zip code (#####-####)",
                                input_format="\\d{5}[-]\\d{4}")
        print(f"{zip_code} is a valid zip code.")
        main()

    elif select == 'matrix':
        matrix()
    elif select == 'exit':
        finish()


print('Hello and welcome!')
main()
