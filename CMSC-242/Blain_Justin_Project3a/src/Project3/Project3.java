/*
Name: Blain, Justin
CMIS 215 - 6384
Date: 2.7.2024

ItineraryBot
Main Class
*/

//Import
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.stage.Stage;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Project3 extends Application {
  @Override
  public void start(Stage primaryStage) {
    //Layout
    GridPane pane = new GridPane();
    pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
    pane.setHgap(5.5);
    pane.setVgap(5.5);

    pane.add(new Label("Distance:"), 0, 0);
    TextField inputDist = new TextField("0.0");
    pane.add(inputDist, 1, 0);
    ComboBox<String> distanceUnit = new ComboBox<>();
    distanceUnit.getItems().addAll("Miles", "Kilometers");
    distanceUnit.setValue("Miles");
    pane.add(distanceUnit, 2, 0);

    pane.add(new Label("Gasoline Cost:"), 0, 1);
    TextField inputGasCost = new TextField("0.0");
    pane.add(inputGasCost, 1, 1);
    ComboBox<String> gasCostUnit = new ComboBox<>();
    gasCostUnit.getItems().addAll("Dollars/Gallon", "Dollars/Liter");
    gasCostUnit.setValue("Dollars/Gallon");
    pane.add(gasCostUnit, 2, 1);

    pane.add(new Label("Gas Milage:"), 0, 2);
    TextField inputMPG = new TextField("0.0");
    pane.add(inputMPG, 1, 2);
    ComboBox<String> gasMPGUnit = new ComboBox<>();
    gasMPGUnit.getItems().addAll("Miles/Gallon", "Kilometers/Liter");
    gasMPGUnit.setValue("Miles/Gallon");
    pane.add(gasMPGUnit, 2, 2);

    pane.add(new Label("Number of Days:"), 0, 3);
    TextField inputDays = new TextField("0.0");
    pane.add(inputDays, 1, 3);

    pane.add(new Label("Hotel Cost:"), 0, 4);
    TextField inputHotel = new TextField("0.0");
    pane.add(inputHotel, 1, 4);

    pane.add(new Label("Food Cost:"), 0, 5);
    TextField inputFood = new TextField("0.0");
    pane.add(inputFood, 1, 5);

    pane.add(new Label("Attractions:"), 0, 6);
    TextField inputAttractions = new TextField("0.0");
    pane.add(inputAttractions, 1, 6);

    Button calculate = new Button("Calculate!");
    pane.add(calculate, 1, 7);

    pane.add(new Label("Total Trip Cost:"), 0, 8);
    TextField outputTotal = new TextField();
    outputTotal.setEditable(false);
    pane.add(outputTotal, 1, 8);

    //Interaction
    calculate.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent arg0)
      {
        TripCost tripCost = new TripCost(
            Double.valueOf(inputDist.getText()),
            Double.valueOf(inputGasCost.getText()),
            Double.valueOf(inputMPG.getText()),
            Double.valueOf(inputDays.getText()),
            Double.valueOf(inputHotel.getText()),
            Double.valueOf(inputFood.getText()),
            Double.valueOf(inputAttractions.getText()),
            distanceUnit.getValue(),
            gasCostUnit.getValue(),
            gasMPGUnit.getValue());
        outputTotal.setText(String.format("$%.2f",tripCost.Calculate()));
      }
    });

    //Setup
    Scene scene = new Scene (pane);
    primaryStage.setTitle("Trip Cost Estimator");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
