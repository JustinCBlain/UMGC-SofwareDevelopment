/*
Name: Blain, Justin
CMIS 141 - 6981
Date: 8.3.2022

FIRST array of sunshine
*/

//import
import java.util.Scanner;

public class Blain_Justin_Asgn6 {
	
	public static void main(String[] args) {
	
	//Init
	Scanner scanint = new Scanner(System.in);
	Scanner scanstr = new Scanner(System.in);
	
	//Greeting
	System.out.println("Goooooood Morning Racers! And welcome FIRST Robotics competition 2022. \n"
			+ "I'm your host A. Ray now let's look at the scores.\n");
	System.out.println("Now, Cohost, remind our viewers just how many teams were competing this year?\n"
			+ "(Whole numbers only please.)");
	
	//Input
	int length = scanint.nextInt();
	System.out.println("That's right Cohost. " + length + " teams competeing today!\n");
	
	String[] teamNames;
	int[] teamScores;
	teamNames = new String[length];
	teamScores = new int[length];
	
	//Team entry
	int entryIter;
	for ( entryIter = 0; entryIter < length; entryIter ++) {
		System.out.println("And what was the name of team number " + ( entryIter + 1 ) + "?");
		teamNames[entryIter] = scanstr.nextLine();
		System.out.println("And their score? (400 - 1000)");
		teamScores [entryIter] = scanint.nextInt();
		System.out.println("Excellent!\n");
	}
	
	//Calculations
	int maxIter;
	int maxIndex = 0;
	int max;
	max = teamScores[0];
	for ( maxIter = 1; maxIter < teamScores.length; maxIter++ ) {
		if (teamScores[maxIter] > max) {
			max = teamScores[maxIter];
			maxIndex = maxIter;
		}
	}
	
	int minIter; // I assume the assignment also meant to say lowest score.
	int minIndex = 0;
	int min;
	min = teamScores[0];
	for ( minIter = 1; minIter < teamScores.length; minIter++ ) {
		if (teamScores[minIter] < min) {
			min = teamScores[minIter];
			minIndex = minIter;
		}
	}
	
	//Output
	System.out.println("That's right folks! The scores today are:\n");
	int tallyIter;
	for (tallyIter = 0; tallyIter < teamNames.length; tallyIter++)
		System.out.println("We have " + teamNames[tallyIter] + " with " + teamScores[tallyIter] + " points.");
	
	System.out.println("\nWhich means that the loser today is " + teamNames[minIndex] + 
			". Only scoring " + teamScores[minIndex] + ".");
	System.out.println("Everyone please be sure to jeer and mock them mercilessly.\n");
	System.out.println("And the arrayning champions of the FIRST robotics competition scoring a whopping " + teamScores[maxIndex] +
			" points is...\n" + teamNames[maxIndex] + "!!!");
	System.out.println("Everyone please be sure reinforce the cycle of acheivment based self esteem!");
	
	//Sign off
	System.out.println("\nThanks everyone for coming for another exciting robotics competion. See ya next time.");
	scanint.close();
	scanstr.close();
	
	}
}