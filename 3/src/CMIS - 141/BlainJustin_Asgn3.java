/*
Name: Blain, Justin
CMIS 141 - 6981
Date: 6.25.2022

A method to calculate xp.
*/

//import
import java.util.Scanner;

//main class
public class BlainJustin_Asgn3 {
	public static void main(String[] args) {

		//init
		Scanner scanstr = new Scanner(System.in);
		Scanner scanint = new Scanner(System.in);
		Scanner scanchar = new Scanner(System.in);
		int response = 1;
		
		//input
		System.out.println("Would you like to calculate xp for a player? Please enter 1 for yes or 0 for no:");
		response = scanint.nextInt();
		while (response == 1 ) {
			System.out.println("Players's name:");
			String name = scanstr.nextLine();
			System.out.println("Please enter XP amounts in increments of 5 between 10 and 100:"
					+ "\nLevel1 Level2 Level3 Engagment:");
			int L1 = scanint.nextInt();
			if ((L1 > 100 || L1 < 10) || (L1 % 5 != 0)) {
				System.out.println("Inputs must be whole numbers between 10 and 100 in increments of 5. /nLevel 1:");
				break;
			} else {
				System.out.println("Confirmed. Level 2:");
			}
			
			int L2 = scanint.nextInt();
			if ((L2 > 100 || L2 < 10) || (L2 % 5 != 0)) {
				System.out.println("Inputs must be whole numbers between 10 and 100 in increments of 5.");
				break;
			} else {
				System.out.println("Confirmed. Level 3:");
			}
			int L3 = scanint.nextInt();
			if ((L3 > 100 || L3 < 10) || (L3 % 5 != 0)) {
				System.out.println("Inputs must be whole numbers between 10 and 100 in increments of 5.");
				break;
			} else {
				System.out.println("Confirmed. Engagment Score:");
			}
			 
			int ES = scanint.nextInt();
			if ((ES > 100 || ES < 10) || (ES % 5 != 0)) {
				System.out.println("Inputs must be whole numbers between 10 and 100 in increments of 5.");
				break; 
			}  else {
				System.out.println("Confirmed. Calculating...");
			}

			//output
			System.out.println(name + "'s Total XP is:");
			System.out.println((L1 * 1.2) + (L2 * 1.3) + (L3 * 1.5) + (ES * 1.6)); //parentheses used here for readability
			
			//Begin again?
			System.out.println("Would you like to calculate xp for another player (1= yes/ 0 = no):");
			response = scanint.nextInt();
		}
		System.out.println("You got it. Ending...");
		
		//terminate
		scanstr.close();
		scanint.close();
		scanchar.close();
	}

}
