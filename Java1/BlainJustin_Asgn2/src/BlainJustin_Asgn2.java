/*
v Name: Blain, Justin
CMIS 141 - 6981
Date: 6.25.2022


A method to perform basic arithmetic.
*/

import java.util.Scanner;

//main class
public class BlainJustin_Asgn2 {
	public static void main(String[] args) {
		//init
				Scanner scanstr = new Scanner(System.in);
				Scanner scanfloat = new Scanner(System.in);
				Scanner scanchar = new Scanner(System.in);
				
				//input
				System.out.println("Please enter a number between 200 and 1000:");
				float x = scanfloat.nextFloat();
				if (1000 >= x && x >= 200) {
					
					System.out.println("Please enter another number between 200 and 1000:");
					float y = scanfloat.nextFloat();
					if (1000 >= y && y >= 200) {
						
						System.out.println("Please enter an operation symbol, \'+\', \'-\', \'*\', or \'/\':");
						char operator = scanchar.next().charAt(0);
						
						//output
						switch (operator) {
						
						case '+' : System.out.println( "The sum of " + x + " and " + y + " is: " + (x+y));
							break;
						case '-' : System.out.println( "The difference between " + x + " and " + y + " is: " + (x-y));
							break;
						case '*' : System.out.println( "The product of " + x + " and " + y + " is: " + (x*y));
							break;
						case '/' : System.out.println( "The quotient of " + x + " and " + y + " is: " + (x/y)); 
							break; 
						default  : System.out.println("I'm sorry but you've thrown off the emporor's groove.");
							break;
						}
							
					} else { System.out.println("I'm sorry but you've thrown off the emporor's groove.");}
			
				} else { System.out.println("I'm sorry but you've thrown off the emporor's groove.");}
				
				//terminate
				scanstr.close();
				scanfloat.close();
				scanchar.close();
	}
}