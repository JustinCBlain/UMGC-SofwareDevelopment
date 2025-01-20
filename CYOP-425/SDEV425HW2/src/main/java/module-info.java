module blain.sdev425hw2 {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.web;

  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  requires net.synedra.validatorfx;
  requires org.kordamp.bootstrapfx.core;
  requires eu.hansolo.tilesfx;
  requires annotations;
  requires java.logging;

  opens blain.sdev425hw2 to javafx.fxml;
  exports blain.sdev425hw2;
}