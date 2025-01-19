package blain.blain1;
/*
Name: Blain, Justin
CMIS 215 - 6384
Date: 2.7.2024

ItineraryBot
Calculatron
*/

final class TripCost {
  //Init
  private double distance;
  private double gasCost;
  private double gasMPG;
  private double days;
  private double hotelCost;
  private double foodCost;
  private double attractionsCost;
  private String distanceUnit;
  private String gasCostUnit;
  private String gasMPGUnit;


  //Constructors
  protected TripCost() {
    this.distance = 0;
    this.gasCost = 0;
    this.gasMPG = 0;
    this.days = 0;
    this.hotelCost = 0;
    this.foodCost = 0;
    this.attractionsCost = 0;
    this.distanceUnit = "";
    this.gasCostUnit = "";
    this.gasMPGUnit = "";
  }
  protected TripCost(double distance, double gasCost, double gasMPG, double days,
                     double hotelCost, double foodCost, double attractionsCost,
                     String distanceUnit, String gasCostUnit, String gasMPGUnit) {
    this.distance = distance;
    this.gasCost = gasCost;
    this.gasMPG = gasMPG;
    this.days = days;
    this.hotelCost = hotelCost;
    this.foodCost = foodCost;
    this.attractionsCost = attractionsCost;
    this.distanceUnit = distanceUnit;
    this.gasCostUnit = gasCostUnit;
    this.gasMPGUnit = gasMPGUnit;
  }

  //Methods
  private double Convert(String inputUnit, Double inputValue) {
    double outputValue;
    if (inputUnit.equals("Kilometers")){
      outputValue = inputValue * 0.621371;
      return outputValue;
    }
    if (inputUnit.equals("Dollars/Liter")){
      outputValue = inputValue * 3.78541;
      return outputValue;
    }
    if (inputUnit.equals("Kilometers/Liter")){
      outputValue = inputValue * 0.425144;
      return outputValue;
    }
    else {
      return inputValue;
    }
  }

  protected double Calculate() {
    distance = this.Convert(distanceUnit, distance);
    gasCost = this.Convert(gasCostUnit, gasCost);
    gasMPG = this.Convert(gasMPGUnit, gasMPG);
    gasCost = (distance/gasMPG)*gasCost;
    final double total = gasCost + (hotelCost + foodCost) * days +
        attractionsCost;
    return total;
  }
}