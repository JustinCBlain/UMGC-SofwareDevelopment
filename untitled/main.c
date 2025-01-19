// Name: Blain, Justin
// Class: CYOP 425
// Date: 09.28.24
//
// Goal: Secure C
// Modified from "Module 6 Assignment.c" resource

#include <stdio.h>
#include <string.h>

// Function prototypes
void fillPassword(size_t, char[]);
void showResults(char);
void showMenu(void);  // Explicit void

// Define a variable to hold a password and the copy
char password[15];

int main(void)
{
    // Welcome the User
    printf_s("Welcome to the C Array Program!\n");

    // Variables
    int cont = 'y'; // To continue with loop

    // Display menu and Get Selection
    while (cont != 'E' && cont != 'e') {
        // Display the Menu
        showMenu();

        // Get the user selection
        cont = getchar();
        while (getchar() != '\n') {} // Clear input buffer

        // Display the menu response
        showResults(cont);
    }

    // Call the Copy routine
    fillPassword(sizeof(password), password);

    // Display variable values
    printf_s("password is %s\n", password);

    // Pause before exiting
    printf_s("Confirm your exit! Press any key.\n");
    char confirm = getchar();  // Wait for key press
    return 0;
}

// Make a String of '1's
void fillPassword(const size_t n, char dest[]) {
    // Should be n-1
    for (size_t j = 0; j < n - 1; j++) {  // Corrected loop bound
        dest[j] = '1';
    }
    dest[n - 1] = '\0';  // Ensure null-termination
}

/* Display the Results*/
void showResults(const char value) {
    switch (value){
    case 'F':
    case 'f':
        printf_s("Welcome to the Football season!\n");
        break;  // Missing break added
    case 'S':
    case 's':
        printf_s("Welcome to the Soccer season!\n");
        break;
    case 'B':
    case 'b':
        printf_s("Welcome to the Baseball season!\n");
        break;
    case 'E':
    case 'e':
        printf_s("Exiting the Menu system!\n");
        break;
    default:
        printf_s("Please enter a valid selection\n");
    }
}

/* Display the Menu*/
void showMenu(void) {
    printf_s("Enter a selection from the following menu.\n");
    printf_s("B. Baseball season.\n");
    printf_s("F. Football season.\n");
    printf_s("S. Soccer season.\n");
    printf_s("E. Exit the system.\n");
}

// In the showResults() function, the case for 'F' (Football) doesn't have a break statement. This will cause
// "fall-through" behavior, where after selecting 'F', the message for 'S' (Soccer) is also displayed.
// Fix: Add a break after the Football case to prevent unintended fall-through

// The loop in the fillPassword() function uses for (size_t j = 0; j < n; j++). This writes outside the bounds of the
// array because it doesn't leave space for the null terminator (\0).
// Fix: Change the loop condition to j < n - 1 and ensure the null terminator is added after the loop.

// The memcpy(cpassword, password, sizeof(password)); copies the entire size of the password array. While this isn't an
// immediate problem with the given data, it's better to ensure the string is copied safely, respecting its length and
// null terminator.
// Fix: Use strncpy() or limit the copy length to strlen(password) + 1 to include the null terminator safely.

// getchar() might leave the newline character (\n) in the input buffer, which can cause issues when reading subsequent
// inputs.
// Fix: After using getchar() for menu selection, we should consume any extra newline characters using another getchar()
// to avoid issues with subsequent input.

// The function prototype should match the intended usage exactly. If the function accepts no arguments, it should
// explicitly state void as a parameter.
// Fix: Ensure void is used in the showMenu() function declaration.

// The variable cVar is defined but never used meaningfully in the program. You can either remove it or find a purpose
// for it.
// Fix: For clarity, remove cVar if it’s not necessary, as it seems redundant in the current context.