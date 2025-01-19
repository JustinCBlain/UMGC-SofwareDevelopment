/*
Name: Blain, Justin
CMIS 215 - 6384
Date: 2.29.2024

nope.
Exception Class
*/
public class InvalidTime extends Exception{
	
	private String error = "Unknown Error";
			
	public InvalidTime(String error) {
		super("Invalid Time: " + error);
		this.error = error;
	}	
	
	public String getError() {
		return error;
	}
}
