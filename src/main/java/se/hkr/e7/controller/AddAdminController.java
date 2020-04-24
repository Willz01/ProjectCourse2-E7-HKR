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
    public ChoiceBox<String> choiceBox;
    public TextField ssn;
    public TextField name;
    public TextField address;
    public TextField password;
    public TextField email;
    public TextField phone;
    public TextField salary;
    public Button Add;


    ObservableList<String> list = FXCollections
            .observableArrayList("BLEKINGE", "DALARNA", "GOTLAND", "GAVLEBORG", "HALLAND", "JAMTLAND",
                    "JONKOPING", "KALMAR", "KRONOBERG", "NORRBOTTEN", "SKANE", "STOCKHOLM", "SODERMANLAND",
                    "UPPSALA", "VARMLAND", "VASTERBOTTEN", "VÄSTERNORRLAND", "VÄSTMANLAND", "VASTRA_GOTALAND",
                    "OREBRO", "OSTERGÖTLAND");


    @FXML
    public void initialize() {
        choiceBox.setValue("location");

        choiceBox.getItems().addAll(list);
        System.out.println(list);

    }

    public void Back(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("AdminDashboard.fxml");
        assert resource != null;
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    public void Exit() {
        System.exit(0);
    }

    public void Add(ActionEvent actionEvent) {
    }
}
