/*
Name: Blain, Justin
CMIS 215 - 6384
Date: 2.1.2024

Golden Ticket Generator
Way Too Much Free Time
*/

public class Graduate extends Student{

//Init
	private String degree;
	
//Constructors
	
	public Graduate() {
		super();
		this.degree = "";
	}

	public Graduate(String name, int creditHours, int qualityPoints, String degree) {
		super(name, creditHours, qualityPoints);
		this.degree = degree;
	}
	
//Getter
	public String getdegree() {
		return degree;
	}
	
	
//Methods
	public boolean eligibleForHonorSociety() {
		boolean eligible = 	((gpa() > getGpaThreshold()) 
							& degree.matches("Masters"));
		return eligible;
	}
	
	public String toString() {
		String studentString = 	getName() + " " 
				+ String.format("%.2f",gpa()) + " "
				+ degree;
		return studentString;
	}
}