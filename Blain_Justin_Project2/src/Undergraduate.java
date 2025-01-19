/*
Name: Blain, Justin
CMIS 215 - 6384
Date: 2.1.2024

Golden Ticket Generator
An Unethical Amount of Debt
*/

public class Undergraduate extends Student{

//Init
	private String year;
	
//Constructors
	
	public Undergraduate() {
		super();
		this.year = "";
	}

	public Undergraduate(String name, int creditHours, int qualityPoints, String year) {
		super(name, creditHours, qualityPoints);
		this.year = year;
	}
	
//Getter
	public String getYear() {
		return year;
	}
	
	
//Methods
	public boolean eligibleForHonorSociety() {
		boolean eligible = 	((gpa() > getGpaThreshold()) 
							& year.matches("Junior|Senior"));
		return eligible;
	}
	
	public String toString() {
		String studentString = 	getName() + " " 
								+ String.format("%.2f",gpa()) + " "
								+ year;
		return studentString;
	}
}
