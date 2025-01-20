module blain.blain_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens blain.blain_1 to javafx.fxml;
    exports blain.blain_1;
}