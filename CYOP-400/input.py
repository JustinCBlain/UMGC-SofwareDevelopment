# Secure Input
def secure_input(input_prompt="Please make valid a selection:",
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
