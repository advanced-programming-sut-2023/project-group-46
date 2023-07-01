module Stronghold {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.fasterxml.jackson.databind;
    requires com.google.gson;
    requires org.json;
    requires java.base;


    exports org.example;
    opens org.example to javafx.fxml, com.google.gson , jdk.jsobject;


}