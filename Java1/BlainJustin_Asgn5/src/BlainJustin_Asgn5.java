/*
Name: Blain, Justin
CMIS 141 - 6981
Date: 7.19.2022

Math Methods
*/

//import
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class BlainJustin_Asgn5 {
	
	public static void main(String[] args) {
		
	//Greeting
	System.out.println("Smiley day to ya!");
	System.out.println("Welcome to the Miscilaneous Math Methodizer!\n");	
	
	//init
	menu();
	}
	
	//Method: Menu
	static void menu() {
	
	Scanner scanchar = new Scanner(System.in);	

	//Prompt for type of calculation
	System.out.println("\n\n--- MAIN MENU ---");
	
	System.out.println("Please enter A to convert Cubic Feet to Bushels.");
	System.out.println("Please enter B to convert Miles to Kilometers.");
	System.out.println("Please enter C to calculate you Acedmic Achievments.");
	System.out.println("Please enter D to Exit.");
	char operator = scanchar.next().charAt(0);

	
	//Call corresponding method
	switch (operator) {
		case 'A' :
		case 'a' : bushels();
		case 'B' : 
		case 'b' : kilometers();
		case 'C' : 
		case 'c' : graduation();
		case 'D' : 
		case 'd' : System.out.println("Have a smiley day!");
			scanchar.close();
			break;
		default  : System.out.println("I'm sorry but you've thrown off the emporor's groove.");
			break;
	} 
}
	
	//Method: Bushels
	static void bushels() {	
		Scanner scanfloat = new Scanner(System.in);
		System.out.println("\nPlease input the number of Cubic Feet to be converted to US Bushels:");
		float input = scanfloat.nextFloat();
		double conversion = 0.803564;
		System.out.println("That would be " + input * conversion + " US Bushels. \n" );
		//scanfloat.close(); #DANGER
		menu();
	}
	
	//Method: Kilometers
	static void kilometers () {
		Scanner scanfloat = new Scanner(System.in);
		System.out.println("\nPlease input the number of Miles to be converted to Kilometers:");
		float input = scanfloat.nextFloat();
		double conversion = 1.60934;
		System.out.println("That would be " + input * conversion + " Kilometers. \n" );
		//scanfloat.close(); #DANGER
		menu();
	}		
	
	//Method: Graduation
	static void graduation() {
		Scanner scanfloat = new Scanner(System.in);
		System.out.println("\nPlease input Grade Point Average:");
		float input = scanfloat.nextFloat();
			if ( input < 3.5 ) {
				System.out.println("Congratulations on graduating!");
			} else if ( input <= 3.7 ){
				System.out.println("Congratulations on graduating Cum Laude!");
			} else if ( input <= 3.9 ){
				System.out.println("Congratulations on graduating Magna Cum Laude!");
			} else {
				System.out.println("Congratulations on graduating Summa Cum Laude!");
			}
		//scanfloat.close(); #DANGER
		menu();
	}
}
	