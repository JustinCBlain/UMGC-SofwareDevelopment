package blain.blain1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginController {
    @FXML
    private Label loginText;
    @FXML
    protected void onHelloButtonClick() {
        if (authorized == true) {
            loginText.setText("Accepted!");
        } else {
            loginText.setText("Denied!");
        }
    }
}