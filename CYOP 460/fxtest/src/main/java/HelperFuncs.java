package main.java;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelperFuncs {
    // Log every attempt
    public static void log(boolean success, String user, String pass) {
        final Logger logger = Logger.getAnonymousLogger();
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
    public static boolean login(TextField user_input, TextField pass_input) {
        String user = String.valueOf(user_input.getText());
        String pass = String.valueOf(pass_input.getText());
        String msg = "Denied!";
        boolean success = false;

        if ("test".equals(user) &&
            "pass".equals(pass)) {
            success = true;
            msg = ("Welcome " + user + "!");
        }

        log(success, user, pass);

        final Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(msg);
        a.show();
        return success;
    }

    // Clear fields
    public static void clear(TextField user_input, TextField pass_input) {
        user_input.clear();
        pass_input.clear();
    }
}
