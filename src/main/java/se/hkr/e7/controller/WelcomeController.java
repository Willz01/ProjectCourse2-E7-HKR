package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
<<<<<<< HEAD
=======
import org.jetbrains.annotations.NotNull;
>>>>>>> wills/new-structure

import java.io.IOException;
import java.net.URL;

public class WelcomeController {

<<<<<<< HEAD
    public void StaffLogin(ActionEvent actionEvent) throws IOException {
=======

    public Button StaffLogin;
    public Button PatientLogin;
    public Button cancel;

    public void StaffLogin(@NotNull ActionEvent actionEvent) throws IOException {
>>>>>>> wills/new-structure
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("StaffLogin.fxml");
        assert resource != null;
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

<<<<<<< HEAD
    public void PatientLogin(ActionEvent actionEvent) throws IOException {
=======
    public void PatientLogin(@NotNull ActionEvent actionEvent) throws IOException {
>>>>>>> wills/new-structure
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("patientLogin.fxml");
        assert resource != null;
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Exit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
