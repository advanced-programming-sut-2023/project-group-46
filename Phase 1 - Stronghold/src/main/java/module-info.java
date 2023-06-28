module Stronghold {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.fasterxml.jackson.databind;
    requires com.google.gson;
    requires org.json;

    exports View;
    opens View to javafx.fxml, com.google.gson;
    exports Controller;
    opens Controller to javafx.fxml, com.google.gson;
    exports Model;
    opens Model to javafx.fxml, com.google.gson;
    exports Enums;
    opens Enums to javafx.fxml, com.google.gson;
}