/*
Name: Blain, Justin
CMIS 215 - 6384
Date: 2.1.2024

Golden Ticket Generator
Main Class
*/

//Import
import java.util.ArrayList;
import java.util.Scanner;

public class Project_2 {

	public static void main(String[] args) throws Exception {
	//Greeting
		System.out.println("Really...?\n"
							+ "You woke up me up just to help run your school?\n"
							+ "Fine! fine. Let\'s see what we got.");
	
	//Init
		try {
			System.out.println("\nDebug: I'm trying!");
			java.io.File file = new java.io.File("students.txt");
			if (!file.exists()) throw new Exception("404");
			System.out.println("Debug: I got it!");
			System.out.println("Debug: Initiating variables!");
			Scanner input = new Scanner(file);
			ArrayList<Student> studentList = new ArrayList<Student>();
			float totalGpa = 0;
			float avgGpa = 0;
			
		//Input
			System.out.println("\nDebug: Alright, let's take a look at this doc of yours.");
			while (input.hasNext()) {
				String name = input.next();
				int creditHours = input.nextInt();
				int qualityPoints = input.nextInt();
				String progress = input.next();
				if (progress.matches("Freshman|Sophomore|Junior|Senior")) {
					Undergraduate newUndergrad = new Undergraduate(name, creditHours, qualityPoints, progress);
					studentList.add (newUndergrad);
					totalGpa = totalGpa + newUndergrad.gpa();
				}
				if (progress.matches("Masters|Doctorate")) {
					Graduate newGrad = new Graduate(name, creditHours, qualityPoints, progress);
					studentList.add(newGrad);
					totalGpa = totalGpa + newGrad.gpa();
				}
				System.out.println("Debug: Got it. Let's get " + name + " enrolled");
			}
			System.out.println("\nDebug: Alrigh, here are the students I have so far:");
		   	for (Student thisStudent : studentList) {
			   System.out.println(thisStudent.toString());
		   	}
		   	
		//Process
		   	System.out.println("\nDebug: So that means the average GPA of these students is:");
			avgGpa = totalGpa / studentList.size();
			System.out.println("Debug: " + String.format("%.2f", avgGpa));
			System.out.println("Debug: Which means the cut off is:");
			Student.setGpaThreshold(avgGpa);
			System.out.println("Debug: " + String.format("%.2f", Student.getGpaThreshold()));
			ArrayList<Student> elligibleList = new ArrayList<Student>();
			System.out.println("\nDebug: So: ");
			for (Student thisStudent : studentList)  {
				boolean elligible = thisStudent.eligibleForHonorSociety();
				System.out.println("Debug: " + 	thisStudent.getName() + 
												" with a GPA of:" + String.format("%.2f",thisStudent.gpa()) +
												" isss...");
		   		if (elligible) {
		   			System.out.println("Debug: in! ");
		   			elligibleList.add(thisStudent);
		   		}
		   		else {
		   			System.out.println("Debug: out :(");
		   		}
		   	}
		
		//OutPut
			System.out.println("\nDebug: Our students who are Juniors, Seniors, or Masters students with GPAs above " 
								+ String.format("%.2f",Student.getGpaThreshold()) + " are:");
		   	for (Student thisStudent : elligibleList) {
			   System.out.println(thisStudent.toString());
		   	}
		   	System.out.println("\nDebug: Hooray!");
			  
		//Signoff
		   	System.out.println("Debug: Alright, I'm going back to bed now.");
		   	input.close();
		}
		
		catch (Exception ex) {
			System.out.println("Debug: File not found");
		}
		
		finally {
		System.out.println("Debug: Goodnight!");
		}
	}
}
