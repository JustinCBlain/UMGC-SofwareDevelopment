/*
Name: Blain, Justin
Class: CYOP 460 7380
Date: 11.1.2024

Login App
*/

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.java.HelperFuncs;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //Layout
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        pane.add(new Label("User:"), 0, 0);
        TextField user_input = new TextField();
        pane.add(user_input, 1, 0);

        pane.add(new Label("Password:"), 0, 1);
        TextField pass_input = new TextField();
        pane.add(pass_input, 1, 1);

        Button login = new Button("Login");
        pane.add(login, 0, 2);

        Button clear = new Button("Clear");
        pane.add(clear, 1, 2);

        //Interaction
        clear.setOnAction(_ -> {
            HelperFuncs.clear(user_input, pass_input);
        });

        login.setOnAction(_ -> {
            HelperFuncs.login(user_input, pass_input);
        });

        //Setup
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
