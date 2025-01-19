module blain.ebonyhorse {
    requires javafx.controls;
    requires javafx.fxml;


    opens blain.ebonyhorse to javafx.fxml;
    exports blain.ebonyhorse;
}