/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdev425_2;

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

// NEW NEW NEW ... these libraries are required for our audit date stamp
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author jim Adopted from Oracle's Login Tutorial Application
 * https://docs.oracle.com/javafx/2/get_started/form.htm
 */

public class SDEV425_2 extends Application {
   
// NEW NEW NEW ... setting up the variables so that we can call them for auditing purposes
    DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
// NEW NEW NEW ... setting a global integer for password attempts
    int attempts = 0;
// NEW NEW NEW ... this is needed for our audit logging    
    String filename = "AuditLog.txt";

    @Override
    public void start(Stage primaryStage) {
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

        // Create some text to place in the scene
        Text scenetitle = new Text("Welcome. Login to continue.");
        // Add text to grid 0,0 span 2 columns, 1 row
        grid.add(scenetitle, 0, 0, 2, 1);

        // Create Label
        Label userName = new Label("User Name:");
        // Add label to grid 0,1
        grid.add(userName, 0, 1);

        // Create Textfield
        TextField userTextField = new TextField();
        // Add textfield to grid 1,1
// NEW NEW NEW seeting a textbox name to help direct the users to enter their username      
        userTextField.setText("User Name");
        grid.add(userTextField, 1, 1);

// NEW NEW NEW ... we neede to use PW for printing our audit file, we need to rename this label
        // Create Label
        Label pwd = new Label("Password:");
        // Add label to grid 0,2
        grid.add(pwd, 0, 2);

        // Create Label
        Label attmp = new Label("Attempts Remaing:");
        // Add label to grid 0,2
        //attmp.setText(attempts);
        grid.add(attmp, 0, 3);
              
        // Create Passwordfield
        PasswordField pwBox = new PasswordField();
        // Add Password field to grid 1,2
// NEW NEW NEW seeting a password box **** to help direct the users to enter their username
        pwBox.setText("Password");
        grid.add(pwBox, 1, 2);

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
/* NEW NEW NEW the next 3 lines is needed to write the auditlog.txt file with 
file input and output we need a TRY and CATCH for errors*/
                try {
                    FileWriter fw = new FileWriter("auditlog.txt");
                    PrintWriter pw = new PrintWriter(fw);
// NEW NEW NEW new boolean expression to help with our login attempt management                
                boolean isLoggedIn = false;
                boolean isValid = authenticate(userTextField.getText(), pwBox.getText());
                // If valid clear the grid and Welcome the user
                
// NEW NEW NEW modified the IF statement to account for login attempts, in this case we are allowing 5 attempts           
                if (attempts <5 && isValid) {
                    grid.setVisible(false);
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
                    isLoggedIn = true;
// NEW NEW NEW uncomment the line below if you would lie to print the number of attempts to your console, this will not display for the users                   
                   //System.out.println("Accessed on " + dtf.format(now) +" " + userTextField.getText());
                   pw.println("Success Login " + dtf.format(now) +" " + userTextField.getText());
                   pw.close();
                   // If Invalid Ask user to try again
// NEW NEW NEW this is a new else if statement, this is used for login attempts, once we reach 5 filed attempts we exit the loop                
                } else if (attempts!=5) { 
// NEW NEW NEW enable the next line if you want to print to the console for testing                    
                   //System.out.println("Invalid Password " +attempts+ " attempts left");
                    final Text actiontarget = new Text();
                    grid.add(actiontarget, 1, 6);
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Please try again.");
// NEW NEW NEW uncomment the line below if you would lie to print the number of attempts to your console, this will not display for the users 
                    //System.out.println("Accessed on " + dtf.format(now) +" " + userTextField.getText());
                    pw.println("Failed Login " + dtf.format(now) +" " + userTextField.getText());
                    pw.close();
// NEW NEW NEW, we need to incement the number of attempts so that we can exit the program when too many attempts have been reached                    
                    attempts++;
// NEW NEW NEW, the next 4 lines are needed to exit the scene if the user failes too many login tries and the return is needed for the loop                    
                }else{
                 System.exit(0);
                }
                return;        
               }
/* NEW NEW NEW the next 2 lines is needed to write the auditlog.txt file with 
file input and output we need a TRY and CATCH for errors*/
                catch (IOException ex) {
                    Logger.getLogger(SDEV425_2.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean authenticate(String user, String pword) {
        boolean isValid = false;
        if (user.equalsIgnoreCase("sdevadmin")
                && pword.equals("425!pass")) {
            isValid = true;
        }

        return isValid;
    }

}