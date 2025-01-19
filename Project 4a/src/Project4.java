/*
Name: Blain, Justin
CMIS 215 - 6384
Date: 2.29.2024

What time is it?
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

public class Project4 extends Application{

	public static void main(String[] args) throws InvalidTime {
		@Override
		public void start(Stage primaryStage) {
		//Layout
			GridPane pane = new GridPane();
			pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		    pane.setHgap(5.5); 
		    pane.setVgap(5.5);
		    
			pane.add(new Label("Start Time"), 1, 0);
			pane.add(new Label("End Time"), 2, 0);
			
			pane.add(new Label("Time Interval 1"), 0, 1);
			TextField start1 = new TextField("10:30 AM");
			pane.add(start1, 1, 1);
			TextField end1 = new TextField("12:30 PM");
			pane.add( end1, 2, 1);
			
			pane.add(new Label("Time Interval 2"), 0, 2);
			TextField start2 = new TextField("11:05 AM");
			pane.add( start2, 1, 2);
			TextField end2 = new TextField("01:00 AM");
			pane.add( end2, 2, 2);
			
			Button compareIntervals = new Button("Compare Intervals");
			pane.add(compareIntervals, 1, 3);
			
			TextField checkTime = new TextField("");
			pane.add( checkTime, 2, 4);
			
			Button checkTime = new Button("Check Time");
			pane.add(checkTime, 1, 5);
			
			TextField output = new TextField();
			output.setEditable(false);
			pane.add(output, 1, 6);	
		}
		
		compareIntervals.setOnAction(new EventHandler<ActionEvent>() {		
		}
			@Override
			public void handle(ActionEvent arg0)
			{
				
			}
		
		checkTime.setOnAction(new EventHandler<ActionEvent>() {		
		}
		
			@Override
			public void handle(ActionEvent arg0)
			{
				
			}
			
		Time t1 = new Time (6,10,"AM");
		Time t2 = new Time ("1:18 PM");
		System.out.println(t1.toString());
		System.out.println(t2.toString());
		System.out.println(t1.compareTo(t2));
		
		// TODO Auto-generated method stub
		// TODO check if after  than interval one start and lesser than interval 1 end
		// TODO same thing for intwervals for 2
		// TODO then if in 1, 2, or both
		
	}

}
