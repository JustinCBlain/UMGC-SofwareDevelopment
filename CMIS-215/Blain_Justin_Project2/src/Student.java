/*
Name: Blain, Justin
CMIS 215 - 6384
Date: 2.1.2024

Golden Ticket Generator
One Computer, One Head, A Handful of Optimism
*/

//Import

public abstract class Student {
//Init
	private String name;
	private int creditHours;
	private int qualityPoints;
	private static float threshold;
	
	//Constructors
	protected Student () {
		name = "";
		creditHours = 0;
		qualityPoints = 0;
	}
	
	protected Student (String name, int creditHours, int qualityPoints) {
		this.name = name;
		this.creditHours = creditHours;
		this.qualityPoints = qualityPoints;
	}

//Getter
	public String getName() {
		return name;
	}

	public int getCreditHours() {
		return creditHours;
	}

	public int getQualityPoints() {
		return qualityPoints;
	}
	
	public static float getGpaThreshold() {
		return threshold;
	}

//Methods
	public float gpa() {
		float gpa = (float) qualityPoints / creditHours;
		return gpa;
	}
	
	public static float setGpaThreshold (float avgGpa) {
		threshold = ((4 - avgGpa) / 2) + avgGpa;
		return threshold;
	}
	
	public abstract boolean eligibleForHonorSociety();
	
	public abstract String toString();	
}