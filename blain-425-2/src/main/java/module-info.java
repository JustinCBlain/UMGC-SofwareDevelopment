module blain.example.blain4252 {
  requires javafx.controls;
  requires javafx.fxml;


  opens blain.example.blain4252 to javafx.fxml;
  exports blain.example.blain4252;
}