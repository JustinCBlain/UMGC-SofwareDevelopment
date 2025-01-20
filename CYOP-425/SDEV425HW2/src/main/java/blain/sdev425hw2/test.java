/*
package blain.sdev425hw2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class test {
  public static void main(String[] args) {
    //System.out.println(file.exists());
    //System.out.println(file.canWrite());
    try (FileWriter fw = new FileWriter("test.txt", true);
         PrintWriter pw = new PrintWriter(fw)) {
      pw.write("test");
      pw.flush();
      System.out.println("Debug");
    } catch (IOException e) {
      System.out.println("Error writing to file: " + e.getMessage());
    }
  }
*/