/*
Name: Blain, Justin
CMIS 141/7385
Date: 04/26/2022

A method to perform basic arithmetic.
*/

//declarations
package Asg2;

//import
import java.util.Scanner;

//main class
public class HelloWorld {
	public static void main(String[] args) {

		//init
		Scanner scanstr = new Scanner(System.in);
		Scanner scanint = new Scanner(System.in);
		Scanner scanchar = new Scanner(System.in);
		
		//input
		System.out.println("Please enter a number:\n");
		int x = scanint.nextInt();
		
		System.out.println("Please enter another number:\n");
		int y = scanint.nextInt();
		
		scanchar.next().charAt(0)
		
		//output
		
		
		//terminate
		System.out.println(x + y);
		scanstr.close();
		scanint.close();
		scanchar.close();
		}

}
