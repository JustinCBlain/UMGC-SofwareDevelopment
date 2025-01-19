module blain.blain2 {
  requires javafx.controls;
  requires javafx.fxml;


  opens blain.blain2 to javafx.fxml;
  exports blain.blain2;
}