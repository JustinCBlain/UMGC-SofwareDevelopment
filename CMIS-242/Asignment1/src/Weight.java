/*
Name: Blain, Justin
CMIS 242-6385
Date: 9.25.2022

Weights and Measures
*/

public class Weight{ 
	
	//attributes
	private int OUNCES_IN_A_POUND = 16;
	private int pounds = 1;
	private double ounces = 12.00;
	private int oiap = OUNCES_IN_A_POUND; //much easier

	//methods
	private double toOunces() {
		int total = pounds * oiap + ounces;
		System.out.println ("test" + total);
	}

	private boolean lessThan() {
		if (ounces < OUNCES_IN_POUND) {
			return true;
		}
	}
	
	private void normalize(){
		if (pounds > oaip){
			int overage = ounces / oaip;
			pounds = pounds + overage;
			ounces = ounces + oiap * overage;
		}
	}
	
	public String toString(){
		System.out.println ("test");
		return "string";
		}

}
//