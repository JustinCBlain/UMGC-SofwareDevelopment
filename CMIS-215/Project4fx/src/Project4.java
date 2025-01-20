/*
Name: Blain, Justin
CMIS 215 - 6384
Date: 2.29.2024

What time is it?
Main Class
 */

//Import
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Project4 extends Application{
	//Layout
	@Override public void start(Stage primaryStage) {
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		pane.setHgap(5.5);
		pane.setVgap(5.5);

		Label start = new Label("Start Time");
		GridPane.setHalignment(start, HPos.CENTER);
		pane.add(start, 1, 0);

		Label end = new Label("End Time");
		GridPane.setHalignment(end, HPos.CENTER);
		pane.add(end, 2, 0);

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
		pane.add(compareIntervals, 0, 3);
		GridPane.setColumnSpan(compareIntervals, 3);
		compareIntervals.setPrefWidth(500);

		pane.add(new Label("Time to Check"), 0, 4);
		TextField inputTime = new TextField("");
		pane.add(inputTime, 1, 4);
		GridPane.setColumnSpan(inputTime, 3);

		Button checkTime = new Button("Check Time");
		pane.add(checkTime, 0, 5);
		GridPane.setColumnSpan(checkTime, 3);
		checkTime.setPrefWidth(500);

		TextField output = new TextField();
		output.setEditable(false);
		pane.add(output, 0, 6);
		GridPane.setColumnSpan(output, 3);

		//Interaction
		compareIntervals.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0){
				try {
					Time startTime1 = new Time (String.valueOf(start1.getText()));
					Time endTime1 = new Time (String.valueOf(end1.getText()));
					Interval interval1 = new Interval(startTime1,endTime1);

					Time startTime2 = new Time (String.valueOf(start2.getText()));
					Time endTime2 = new Time (String.valueOf(end2.getText()));
					Interval interval2 = new Interval(startTime2,endTime2);

					if (interval1.subInterval(interval2)) {
						output.setText("Interval 2 is a sub-interval of interval 1");
					}
					else if (interval2.subInterval(interval1)) {
						output.setText("Interval 1 is a sub-interval of interval 2");
					}
					else if (interval2.overlaps(interval1)) {
						output.setText("The intervals overlap");
					}
					else {
						output.setText("The intervals are disjoint");
					}
				}
				catch (InvalidTime ex) {
					ex.printStackTrace();
				}
			}
		});

		checkTime.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0){
				try {
					Time startTime1 = new Time (String.valueOf(start1.getText()));
					Time endTime1 = new Time (String.valueOf(end1.getText()));
					Interval interval1 = new Interval(startTime1,endTime1);

					Time startTime2 = new Time (String.valueOf(start2.getText()));
					Time endTime2 = new Time (String.valueOf(end2.getText()));
					Interval interval2 = new Interval(startTime2,endTime2);

					Time testTime = new Time (String.valueOf(inputTime.getText()));

					if (interval1.within(testTime) && interval2.within(testTime)) {
						output.setText("Both intervals contains the time" +
								testTime.toString());
					}
					else if (interval1.within(testTime)) {
						output.setText("Only interval 1 contains the time" +
								testTime.toString());
					}
					else if (interval2.within(testTime)) {
						output.setText("Only interval 2 contains the time" +
								testTime.toString());
					}
					else {
						output.setText("Neither intervals contains the time" +
								testTime.toString());
					}
				}
				catch (InvalidTime ex) {
					ex.printStackTrace();
				}
			}
		});

		//Setup
		Scene scene = new Scene (pane);
		primaryStage.setTitle("Time Interval Checker");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) throws InvalidTime {
		Application.launch(args);
	}
}
