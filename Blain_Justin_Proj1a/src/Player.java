/*
Name: Blain, Justin
CMIS 215 - 6384
Date: 1.20.2024

Tall, Dark, and Younger Than Average
Player Class
*/

//Import
import java.util.Scanner;

public class Player {
	
//Init
	Scanner scanint = new Scanner(System.in);
	Scanner scanstr = new Scanner(System.in);
	public String name;
	public Height height;
	public int age;

//Constructors
	public Player () {
		name = "";
		height = new Height();
		age = 0;
	}
	
	public Player (String name, Height height, int age) {
		this.name = name;
		this.height = height;
		this.age = age;
	}
	
//Getters
	public String getName() {
		return name;
	}
	
	public Height getHeight() {
		return height;
	}

	public int getAge() {
		return age;
	}
	
//Methods
	public Player add () {
		System.out.println("What is the name of this player?");
		String givenName = scanstr.nextLine();
		name = givenName;
		
		Height givenHeight = new Height();
		height = givenHeight.add();
		
		System.out.println("And the age of this player?");
		int givenAge = scanint.nextInt();
		age = givenAge;
		Player newPlayer = new Player(name, height, age);		
		return newPlayer;
	}
	
	public String toString() {
		String heightText = new String();
		String playerString = new String();
		heightText = height.toString();
		playerString = 	"Name:" + name + "\n" +
						"Height:" + heightText + "\n" +
						"Age:" + Integer.toString(age);
		return playerString; 
	}
}
