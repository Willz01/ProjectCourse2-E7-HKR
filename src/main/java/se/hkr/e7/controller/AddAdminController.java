package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Location;

import java.io.IOException;
import java.net.URL;

import static se.hkr.e7.model.DatabaseHandler.save;
import static se.hkr.e7.model.Location.*;

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
    public Label ssnLabel;
    public Label nameLabel;
    public Label passwordLabel;
    public Label addressLabel;
    public Label emailLabel;
    public Label phoneLabel;
    public Label SalaryLabel;
    public Label locationLabel;
    public Label saveLabel;


    @FXML
    public void initialize() {
        ssnLabel.setText("YYMMDDXXXX");


        choiceBox.getItems().add("Location");
        choiceBox.getItems().addAll(String.valueOf(BLEKINGE), String.valueOf(DALARNA), String.valueOf(GOTLAND), String.valueOf(GAVLEBORG), String.valueOf(HALLAND), String.valueOf(JAMTLAND),
                String.valueOf(JONKOPING), String.valueOf(KALMAR), String.valueOf(KRONOBERG), String.valueOf(NORRBOTTEN), String.valueOf(SKANE), String.valueOf(STOCKHOLM), String.valueOf(SODERMANLAND),
                String.valueOf(UPPSALA), String.valueOf(VARMLAND), String.valueOf(VASTERBOTTEN), String.valueOf(VASTERNORRLAND), String.valueOf(VASTMANLAND), String.valueOf(VASTRAGOTALAND),
                String.valueOf(OREBRO), String.valueOf(OSTERGOTLAND));
        choiceBox.setValue("Location");
    }

    public void Back(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("view/AdminDashboard.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    public void Exit() {
        System.exit(0);
    }

    public void Add() {
        nameLabel.setText("");
        ssnLabel.setText("");
        passwordLabel.setText("");
        emailLabel.setText("");
        SalaryLabel.setText("");
        phoneLabel.setText("");
        addressLabel.setText("");
        saveLabel.setText("");

        if (ssn.getText().matches("^([0-9]{2})([0-9]{2})([0-9]{2})([a-zA-Z0-9][0-9]{3})$")) {
            ssnLabel.setText("");
        }
        if (!ssn.getText().matches("^([0-9]{2})([0-9]{2})([0-9]{2})([a-zA-Z0-9][0-9]{3})$")) {
            ssnLabel.setText("input in YYMMDDXXXX form");
        }
        if (name.getText().equals("")) {
            nameLabel.setText("can't be empty");
        }
        if (address.getText().equals("")) {
            addressLabel.setText(" can't be empty");
        }
        if (password.getText().equals("")) {
            passwordLabel.setText(" can't be empty");
        }
        if (email.getText().equals("")) {
            emailLabel.setText(" can't be empty");
        }
        if (phone.getText().equals("")) {
            passwordLabel.setText(" can't be empty");
            if ((salary.getText().matches("^[0-9]+\\.?[0-9]*$"))) {
                salary.setText(" salary must be number");
            }
        }

        try {

            save(new Employee(ssn.getText(), password.getText(), name.getText(), email.getText(),
                    phone.getText(), address.getText(), Location.valueOf(choiceBox.getValue()), Employee.Role.ADMIN, Double.parseDouble(salary.getText())));
            saveLabel.setText("saved");
        } catch (Exception exception) {
            saveLabel.setText("did't save ");

        }
    }
}


