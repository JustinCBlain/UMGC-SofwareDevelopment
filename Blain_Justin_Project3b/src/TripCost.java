/*
Name: Blain, Justin
CMIS 215 - 6384
Date: 2.7.2024

ItineraryBot
Calculatron
*/

public class TripCost {
//Init
	private double distance;
	private double gasCost;
	private double gasMPG;
	private double days;
	private double hotelCost;
	private double foodCost;
	private double attractionsCost;
	private double total;
	private String distanceUnit;
	private String gasCostUnit;
	private String gasMPGUnit;
	
	
//Constructors
	public TripCost() {
		this.distance = 0;
		this.gasCost = 0;
		this.gasMPG = 0;
		this.days = 0;
		this.hotelCost = 0;
		this.foodCost = 0;
		this.attractionsCost = 0;
		this.total = 0;
		this.distanceUnit = "";
		this.gasCostUnit = "";
		this.gasMPGUnit = "";
	}
	public TripCost(double distance, double gasCost, double gasMPG, double days, double hotelCost, double foodCost,
			double attractionsCost, double total, String distanceUnit, String gasCostUnit, String gasMPGUnit) {
		this.distance = distance;
		this.gasCost = gasCost;
		this.gasMPG = gasMPG;
		this.days = days;
		this.hotelCost = hotelCost;
		this.foodCost = foodCost;
		this.attractionsCost = attractionsCost;
		this.total = total;
		this.distanceUnit = distanceUnit;
		this.gasCostUnit = gasCostUnit;
		this.gasMPGUnit = gasMPGUnit;
	}
	
//Methods
	public double Convert(String inputUnit, Double inputValue) {
		double outputValue;
		if (inputUnit.equals("kilometer")){
			outputValue = inputValue * 0.621371;
		}
		if (inputUnit.equals("dollars/kilometer")){
			outputValue = inputValue * 0.264172052;
		}
		if (inputUnit.equals("kilometers/liter")){
			outputValue = inputValue * 0.425144;
		}
		else {
			outputValue = inputValue;
		}
		return outputValue;
	}
	
	public double  Calculate(double distance, double gasCost, double gasMPG, double days, double hotelCost, double foodCost,
			double attractionsCost) {
		distance = this.Convert(distanceUnit, distance);
		gasCost = this.Convert(gasCostUnit, gasCost);
		gasMPG = this.Convert(gasMPGUnit, gasMPG);
		gasCost = (distance/gasMPG)*gasCost;
		total = gasCost + (hotelCost + foodCost) * days + attractionsCost;
		return total;
	}
	//immutable
	//constructor the computes and returns total trip cost.
}

//14.10 grid pane? hbox
//16.3 buttons
//16.6 text fields
//16.8 combo box