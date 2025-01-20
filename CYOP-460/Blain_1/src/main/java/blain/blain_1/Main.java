package blain.blain_1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button("Click me!");
        btn.setOnAction(e -> System.out.println("Hello, JavaFX!"));

        Scene scene = new Scene(btn, 300, 200);
        primaryStage.setTitle("My JavaFX App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
