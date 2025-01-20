package problemsWork;

import java.util.*;

public class Excercise11_7 {

	public static void main(String[] args) {
	
	ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
	shuffle(list);
	}
	
	public static ArrayList<Integer> shuffle(ArrayList<Integer> list) {
		ArrayList<Integer> shuffledList = new ArrayList<Integer>();
		Random rand = new Random();
		int randomNum = rand.nextInt((shuffledList.size()-0)+1);
		for (int i = 0; i < list.size(); i++) {
			randomNum = rand.nextInt((shuffledList.size()-0)+1);
			shuffledList.add(randomNum, list.get(i));
		}
		System.out.println(shuffledList);
		return shuffledList;
	}
}
