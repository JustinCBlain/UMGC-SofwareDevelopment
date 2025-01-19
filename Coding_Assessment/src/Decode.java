/*
Name: Blain, Justin
Data Annotation
Date: 2.16.2024

Coding Assessment
*/

//Import
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class Decode {

	public static void main(String[] args) throws FileNotFoundException {
		
	//Init
		java.io.File file = new java.io.File("message.txt");
		Scanner input = new Scanner(file);
		HashMap<Integer, String> wordMap = new HashMap<>();
		ArrayList<String> sortedWords = new ArrayList<>();
		ArrayList<ArrayList<String>> pyramidList = new ArrayList<>();

	//Input and store as converted ArrayList
		while (input.hasNext()) {
			int key = input.nextInt();
			String word = input.next();
			wordMap.put(key,word);
		}
		System.out.println("Sorted Map: " + wordMap + "\n");
		sortedWords.addAll(wordMap.values());
		System.out.println("Word List: " + sortedWords + "\n");
		
	//Create a pyramidList out of stepLists
		
		System.out.println("Pyramid:");
		for (int i = 1, k = 0; k < sortedWords.size(); i++) {
			ArrayList <String> stepList = new ArrayList<String>();
			for (int j = 1; j <= i; j++, k++ ) {
				stepList.add(sortedWords.get(k));
			}
			System.out.println(stepList);
			pyramidList.add(stepList);
		}
		
	//Print last word from each stepList
		System.out.println("\nSolution: ");
		pyramidList.forEach((stepList) -> 
			System.out.println(stepList.get(stepList.size()-1)));
		
	//Close
		input.close();
	}
}