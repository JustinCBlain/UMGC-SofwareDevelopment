/*
Name: Blain, Justin
CMIS 141 - 6981
Date: 7.19.2022

Math Methods
*/
import java.util.Scanner;
//import java.lang.math;

public class BlainJustin_Asgn5 {
	
	public static void main(String[] args) {
				
		//init
		Scanner scanfloat = new Scanner(System.in);
		Scanner scanchar = new Scanner(System.in);
		
		//Greeting
		System.out.println("Smiley day to ya!");
		System.out.println("Welcome to the Miscilaneous Math Methodizer!\n");
		
		//Prompt for type of calculation
		System.out.println("Please enter A to convert Cubic Feet to Bushels.");
		System.out.println("Please enter B to convert Miles to Kilometers.");
		System.out.println("Please enter C to calculate you acedmic achievments.");
		System.out.println("Please enter D to convert to Exit.");
		char operator = scanchar.next().charAt(0);
		
		//Call corresponding method
		switch (operator) {
			case 'A' : bushels();
			case 'B' : kilometers();
			case 'C' : graduation();
			case 'D' : exit();
			default  : System.out.println("I'm sorry but you've thrown off the emporor's groove.");
				break;
		} 
	}

		
		//Method: Bushels
		static void bushels() {
			
			System.out.println("bushles");
		}
		
		//Method: Kilometers
		static void kilometers () {
			
			System.out.println("KM");
		}		
		
		//Method: Graduation
		static void graduation() {
			
			System.out.println("Grad");
		}		
		
		//Method: Exit
		static void exit() {
			
			System.out.println("Exit");
		}
		
		/*
			Prompt for cubic feet
			Multiply by 0.803564
			Output result
			Menu

		//Method Kilometers
			Prompt for miles
			Multiply by 1.60934
			Output result
			Menu

		//Method Graduation
			Prompt for GPA
			Switch 
				< 3.5		Grad
				3.5 - 3.7	Cum
				3.8 - 3.9	Magna
				> 4.0		Summa
			Output result
			Menu

		//Exit
		Thank you
		End program
		*/

		//System.out.println("test");
	scanfloat.close();
	scanchar.close();

}