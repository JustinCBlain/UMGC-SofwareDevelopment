/*
Name: Blain, Justin
CMIS 141 - 6981
Date: 7.19.2022

Fun with patterns
*/
import java.util.Scanner;
import java.lang.math;

public class Blain_Justin_Asgn4 {

	public static void main(String[] args) {

		//Get, restrict, and check input
		Scanner scanint = new Scanner (System.in);
		System.out.println("Please enter an integer between 1 and 9:");
		int x = scanint.nextInt();
		if (x < 1 || x > 9) {
			System.out.println("I'm sorry but you've thrown off the emporers groove.");
		} else {
		
		//Output triangle
		int row = 0;
		int column;
		while (row <= x) {
			column = 1;
			while (column <= row) {
				System.out.print(column + " ");
				column ++;
			}
			System.out.println("\n");
			row ++;
		}
		
		//Output Separation
		System.out.println("\n");
		
		//Output block
		int size = (x * 2);
		row = 1;
		while (row < size) {
			column = 1;
			while (column < size) {
				int width = Math.abs(row - x) + 1;
				int height = Math.abs(column - x) + 1;
				if (height < width) {
					System.out.print(width);
				} else {
					System.out.print(height);
				}
				System.out.print(" ");
				column ++;
			}
			System.out.println("\n");
			row ++;
		}
		}
	//Close scanner
	scanint.close();
	}

}
