module blain.login {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  requires org.kordamp.bootstrapfx.core;

  opens blain.login to javafx.fxml;
  exports blain.login;
}