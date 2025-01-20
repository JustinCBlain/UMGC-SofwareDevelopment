module blain.blain1 {
  requires javafx.controls;
  requires javafx.fxml;


  opens blain.blain1 to javafx.fxml;
  exports blain.blain1;
}