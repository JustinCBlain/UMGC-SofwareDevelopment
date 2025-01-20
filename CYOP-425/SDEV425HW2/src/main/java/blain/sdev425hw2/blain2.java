/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blain.sdev425hw2;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.logging.Level;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.logging.Logger;


/**
 *
 * @author jim Adopted from Oracle's Login Tutorial Application
 * https://docs.oracle.com/javafx/2/get_started/form.htm
 */
public class blain2 extends Application {
    private static final Logger logger = Logger.getAnonymousLogger();

    @Override
    public void start(Stage primaryStage) throws IOException {
        File file = new File("Logs.csv");
        primaryStage.setTitle("SDEV425 Login");
        // Grid Pane divides your window into grids
        GridPane grid = new GridPane();
        // Align to Center
        // Note Position is geometric object for alignment
        grid.setAlignment(Pos.CENTER);
        // Set gap between the components
        // Larger numbers mean bigger spaces
        grid.setHgap(10);
        grid.setVgap(10);

        // Display System Use message
        Text scenetitle = new Text("""
            Welcome.
            Logging in to this system constitutes acceptance of the following:
            All user input and interaction with this application is monitored and logged for \
            security purposes. Authorized users may log in using this portal.
            Access by any and all unauthorized users is considered illicit \
            use.""");

        // Add text to grid 0,0 span 2 columns, 1 row
        grid.add(scenetitle, 0, 0, 2, 1);

        // Generate server accessible MFA PIN
        Random rand = new Random();
        int pin = 1000 + rand.nextInt(8999);
        System.out.println("Authentication key:" + pin);

        // Create Label
        Label userName = new Label("User Name:");
        // Add label to grid 0,1
        grid.add(userName, 0, 1);

        // Create Textfield
        TextField userTextField = new TextField();
        // Add textfield to grid 1,1
        grid.add(userTextField, 1, 1);

        // Create Label
        Label pw = new Label("Password:");
        // Add label to grid 0,2
        grid.add(pw, 0, 2);

        // Create Passwordfield
        PasswordField pwBox = new PasswordField();
        // Add Password field to grid 1,2
        grid.add(pwBox, 1, 2);

        // Create Label
        Label key = new Label("Auth Key:");
        // Add label to grid 0,3
        grid.add(key, 0, 3);

        // Create PINField
        PasswordField pinBox = new PasswordField();
        // Add PIN field to grid 1,3
        grid.add(pinBox, 1, 3);

        // Create Login Button
        Button btn = new Button("Login");
        // Add button to grid 1,4
        grid.add(btn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        // Set the Action when button is clicked
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                // Authenticate the user
                boolean isValid = authenticate(userTextField.getText(), pwBox.getText());
                // If valid clear the grid and Welcome the user
                if (isValid && pin == Integer.parseInt(pinBox.getText())) {
                    GridPane grid2 = new GridPane();
                    // Align to Center
                    // Note Position is geometric object for alignment
                    grid2.setAlignment(Pos.CENTER);
                     // Set gap between the components
                    // Larger numbers mean bigger spaces
                    grid2.setHgap(10);
                    grid2.setVgap(10);
                    Text scenetitle = new Text("Welcome " + userTextField.getText() + "!");
                    // Add text to grid 0,0 span 2 columns, 1 row
                    grid2.add(scenetitle, 0, 0, 2, 1);
                    Scene scene = new Scene(grid2, 500, 400);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                   // If Invalid Ask user to try again
                } else {                    
                    final Text actiontarget = new Text();
                    grid.add(actiontarget, 1, 6);
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Please try again in 30 seconds.");
                }

            }
        });
        // Set the size of Scene
        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param user the username entered
     * @param pword the password entered
     * @return isValid true for authenticated
     */

    //Init variables for Login Limitation
    Instant lastTime= null;
    Instant newTime = null;
    int attempts = 0;

    public boolean authenticate(String user, String pword) {
      boolean isValid = false;
      if (newTime != null){
          lastTime = newTime;
      }
      newTime = Instant.now();

      // Increment number of Attempts attempted within 30 seconds of each other.
      if (lastTime != null &&
          newTime.minus(Duration.ofSeconds(30)).isBefore(lastTime)){
        attempts++;
      } else {
      attempts = 0;
      }

      // Return valid if username and password are correct and attempts < 5
      if (user.equalsIgnoreCase("sdevadmin")
          && pword.equals("425!pass") && attempts < 5) {
        isValid = true;
      }
      log(attempts, newTime, isValid, user, pword);
      return isValid;
    }

    // Log every attempt
    public static void log(int attempts, Instant time, boolean isValid, String user,
                           String pword) {
        File file = new File("Logs.csv");
        String ip = "123.123.1.1"; // Simulated IP Logging
        System.out.println("Trying write");
        try (FileWriter fw = new FileWriter(file, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(attempts + "," + time + "," + ip + "," + isValid + "," + user + "," + pword);
        } catch (IOException e) {
            System.out.println("IO Exception");
            System.out.println("Error writing to file: " + e.getMessage());
            logger.log(Level.SEVERE, "File not found: ", e);
        }
    }
}
