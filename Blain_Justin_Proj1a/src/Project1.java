/*
Name: Blain, Justin
CMIS 215 - 6384
Date: 1.20.2024

Tall, Dark, and Younger Than Average
Main Class
*/

//Import
import java.util.Scanner;


public class Project1 {

	public static void main(String[] args) {
		
	//Init
		Scanner scanint = new Scanner(System.in);
		Scanner scanstr = new Scanner(System.in);
			
	//Greeting
		System.out.println("Good morning.\n");
				
	//Ingest
		System.out.println("How many players?");
		int len = scanint.nextInt();
		System.out.println("Roger that!\n");
		ArrayList<Player> playerList = new ArrayList<Player>(len);
		for (int i = 0; i < len; i++) {
			Player newPlayer = new Player();
			newPlayer.add();
			playerList.add(newPlayer);
			if (i<len-1) {
				System.out.println("\nAnd for the next player;");//check
			}
		}
		
	//Process
		//Average Age
		int ageTotal = 0;
		for (int iter = 0; iter < len; iter++) {
		    Player thisPlayer = new Player();
		    thisPlayer = playerList.get(iter);
			int thisAge = thisPlayer.age;
			ageTotal = thisAge + ageTotal; 
		}
		double avgAge = ageTotal / len;
		System.out.println("\nThe average age of these player is:" + avgAge + "\n");
		
		//Tallest Player Finder
		int tallestIter = 0;
		int tallestInches = 0;
		for (int iter = 0; iter < len; iter++) {
		    Player thisPlayer = new Player();
		    thisPlayer = playerList.get(iter);
			int thisAge = thisPlayer.age;
			if (thisAge <= avgAge) {
				int thisInches = thisPlayer.height.toInches();
				if (thisInches > tallestInches) {
					tallestInches = thisInches;
					tallestIter = iter;
				}
				
			}
		}
		
		//Result
		Player tallestPlayer = new Player();
		tallestPlayer = playerList.get(tallestIter);
		System.out.println("The tallest player younger the average age is:");
		System.out.println(tallestPlayer.toString());

	//Sign off
		scanint.close();
		scanstr.close();
		System.out.println("\n Good night.");
		}
	}