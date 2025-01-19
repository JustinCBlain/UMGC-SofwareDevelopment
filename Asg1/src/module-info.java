/*
Name: Blain, Justin
CMIS 141/7385
Date: (04/04/2022)

A method to calculate a telephone order.
*/

//declarations
package Asg1;

//import
import java.util.Scanner;

//main class
public class HelloWorld {
	public static void main(String[] args) {

		//init
		Scanner scanstr = new Scanner(System.in);
		Scanner scanint = new Scanner(System.in);
		Scanner scanfloat = new Scanner(System.in);
		
		//input
		System.out.println("Hello, Thank you for calling Buy-n-Large automated ordering service!\n"
				+ "If you're ready, go ahead and enter you customer ID. \n"
				+ "If you're not sure what to enter, go ahead and say or enter your Social Security Number here:");
		String custID = scanstr.nextLine();
		
		System.out.println("Great. \nNow go ahead and tell me what you'd like to buy:");
		String itemName = scanstr.nextLine();
		
		System.out.println("Great. \nUsing the keypad please enter its price in decimal format:");
		float price = scanfloat.nextFloat();

		System.out.println("Great. \nUsing the keypad please enter its the quantity needed as a whole number:");
		int quant = scanint.nextInt();

		System.out.println("Great. \nUsing the keypad please enter any discounts in percents expressed as a decimal.\n"
				+ "Remember! Honesty is a virtue!:");
		float discount = scanfloat.nextFloat();
		
		//output
		System.out.println("Great. \n Let me print your receipt:");
		System.out.println(); 
		String receipt = "ORDER DATA:\n"
				+ "\nCustomer ID - " + custID
				+ "\nItem - " + itemName
				+ "\nPrice - " + price
				+ "\nQuantity - " + quant
				+ "\nDiscount - " + discount
				+ "\n\nTotal BEFORE discount - " + price * quant
				+ "\nTotal AFTER discount - " + price * quant * (1.0 - discount)
				;
		System.out.println(receipt);
		
		//terminate
		System.out.println("Thanks for shopping at Buy-n-Large\n"
				+ "We hope you have a great day!");
		scanstr.close();
		scanint.close();
		scanfloat.close();
		}

}
