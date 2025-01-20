package sdev425;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SDEV425_1 {
  // Initiate Logger
  private static final Logger logger = Logger.getAnonymousLogger();

  public static void main(String[] args) {
    // Validate File Name and Email inputs as expected formats
    String filePattern = "^([\\w-]+\\.)+[\\w-]{2,4}$";
    Pattern fileRegex = Pattern.compile(filePattern);

    String emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{3}$";
    Pattern emailRegex = Pattern.compile(emailPattern);

    // Prompt user to input file name. Reject unless formatted correctly
    try (Scanner cli = new Scanner(System.in)) {
      String fileName;
      while (true) {
        System.out.println("File?");
        fileName = cli.nextLine();
        Matcher matcher = fileRegex.matcher(fileName);
        if (matcher.matches()) {
          fileName = "src/sdev425/" + fileName;
          break;
        } else {
          System.out.println("Invalid file name format. Please try again.");
        }
      }

      // Scan and print each valid email
      File file = new File(fileName);
      System.out.println("Trying write");
      try (FileWriter fr = new FileWriter(file, true)) {
        fr.write("test@umgc.edu");
      } catch (IOException e) {
        System.out.println("IO Exception");
        logger.log(Level.SEVERE, "File not found: ", e);
      }

      System.out.println("Trying read");
      try (Scanner input = new Scanner(file)) {
        while (input.hasNext()) {
          String line = input.nextLine();
          Matcher matcher = emailRegex.matcher(line);
          if (matcher.matches()) {
            System.out.println(line);
          } else {
            System.out.println("Invalid email format.");
          }
        }

      //Log Exceptions
      } catch (FileNotFoundException e) {
        System.out.println("File not found.");
        logger.log(Level.SEVERE, "File not found: ", e);
      }
    }
  }
}
