package se.hkr.e7.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AddAdminController {
    @FXML
    public ChoiceBox<ObservableList<String>> choiceBox;
    public TextField ssn;
    public TextField name;
    public TextField address;
    public TextField password;
    public TextField email;
    public TextField phone;
    public TextField salary;
    public Button Add;


    ObservableList<String> location = FXCollections
            .observableArrayList("BLEKINGE", "DALARNA", "GOTLAND", "GÄVLEBORG", "HALLAND", "JÄMTLAND",
                    "JÖNKÖPING", "KALMAR", "KRONOBERG", "NORRBOTTEN", "SKÅNE", "STOCKHOLM", "SÖDERMANLAND",
                    "UPPSALA", "VÄRMLAND", "VÄSTERBOTTEN", "VÄSTERNORRLAND", "VÄSTMANLAND", "VÄSTRA_GÖTALAND",
                    "ÖREBRO", "ÖSTERGÖTLAND");


    @FXML
    public void initialize() {

        choiceBox.setValue(location);

    }

    public void Back(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("AdminDashboard.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void Add(ActionEvent actionEvent) {
    }
}
