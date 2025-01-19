package blain.ebonyhorse;/*
Name: Blain, Justin
Class: CYOP 460 7380
Date: 11.1.2024

Login App
*/

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main extends Application {
    private static final Logger logger = Logger.getAnonymousLogger();
    final Alert a = new Alert(Alert.AlertType.NONE);

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
            clear(user_input, pass_input);
        });

        login.setOnAction(_ -> {
            login(user_input, pass_input);
        });

        //Setup
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Log every attempt
    public static void log(boolean success, String user, String pass) {
        File file = new File("Logs.txt");
        Instant time = Instant.now();

        System.out.println("Trying write...");
        try (FileWriter fw = new FileWriter(file, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(time + "," + success + "," + user + "," + pass);
            System.out.println("Write successful!");
        } catch (IOException e) {
            System.out.println("Write Failing...");
            System.out.println("IO Exception");
            System.out.println("Error writing to file: " + e.getMessage());
            logger.log(Level.SEVERE, "File not found: ", e);
            System.out.println("Write failed successfully!");
        }
    }

    // Validate credentials and display result
    private void login(TextField user_input, TextField pass_input) {
        String user = String.valueOf(user_input.getText());
        String pass = String.valueOf(pass_input.getText());
        String msg = "Denied!";
        boolean success = false;

        if (Objects.equals(user, "user") &&
            Objects.equals(pass, "pass")) {
            success = true;
            msg = ("Welcome " + user + "!");
        }

        log(success, user, pass);
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setContentText(msg);
        a.show();
    }

    // Clear fields
    static void clear(TextField user_input, TextField pass_input) {
        user_input.clear();
        pass_input.clear();
    }
}


