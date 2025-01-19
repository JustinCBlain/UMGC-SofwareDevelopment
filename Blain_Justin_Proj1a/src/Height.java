/*
Name: Blain, Justin
CMIS 215 - 6384
Date: 1.20.2024

Tall, Dark, and Younger Than Average
Height Class
*/

//Import
import java.util.Scanner;

public class Height {
	
//Init
	 Scanner scanint = new Scanner(System.in);
	 Scanner scanstr = new Scanner(System.in);
	 int feet = 0;
	 int inches = 0;

	
//Constructors
	public Height (){
	}
	
	public Height (int feet, int inches){
		this.feet = feet;
		this.inches = inches;
	}
	
//Methods	
	public Height add(){
		System.out.println("What is the height of this player?");
		System.out.println("(Please enter feet as a whole number.)");
		int givenFeet = scanint.nextInt();
		System.out.println("(Please enter inches as a whole number.)");
		int givenInches = scanint.nextInt();
		
		int feet = givenFeet;
		int inches = givenInches;
		Height newHeight = new Height(feet, inches);		
		return newHeight;
	}
	
	public int toInches() {
		int totalInches = (feet*12) + inches;
		return totalInches;
	}
	
	public String toString() {
		String heightString = new String();
		if (inches >= 12) {
			int totalInches = toInches();
			feet = totalInches/12;
			inches = totalInches%12;
			heightString =	Integer.toString(feet) + "\'" +
							Integer.toString(inches) + "\"";
			return heightString;
		} else { 
			heightString =	Integer.toString(feet) + "\'" +
							Integer.toString(inches) + "\"";		
			return heightString;
		}
	}
}
