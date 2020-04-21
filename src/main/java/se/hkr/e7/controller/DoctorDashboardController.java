package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DoctorDashboardController {
    public Button Back;
    public Button Exit;
    public Button viewPatient;
    public Button createCase;
    public Button viewResults;
    public Button ListOfCases;

    public void Back(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("staffLogin.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void viewPatient(ActionEvent actionEvent) {
    }

    public void createCase(ActionEvent actionEvent) {
    }

    public void viewResults(ActionEvent actionEvent) {
    }

    public void ListOfCases(ActionEvent actionEvent) {
    }
}
