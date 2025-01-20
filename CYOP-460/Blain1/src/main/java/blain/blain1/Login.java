/*
Name: Blain, Justin
Class: CYOP 460 - 7380
Date: 10.12.2024

Login Demo
*/

package blain.blain1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Login extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    //Layout
    GridPane pane = new GridPane();
    pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
    pane.setHgap(5.5);
    pane.setVgap(5.5);

    FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("login" +
        ".fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 320, 240);

    stage.setTitle("Hello!");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}