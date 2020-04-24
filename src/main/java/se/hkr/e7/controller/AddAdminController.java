package se.hkr.e7.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import se.hkr.e7.Employee;
import se.hkr.e7.Location;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import static se.hkr.e7.DatabaseHandler.save;

public class AddAdminController {
    @FXML
    public ChoiceBox<Location> choiceBox;
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
        ssnLabel.setText("YYYYMMDDXXXX");

        ObservableList<Location> languages //
                = FXCollections.observableArrayList(Location.BLEKINGE, Location.DALARNA, Location.GOTLAND, Location.GAVLEBORG, Location.HALLAND, Location.JAMTLAND,
                Location.JONKOPING, Location.KALMAR, Location.KRONOBERG, Location.NORRBOTTEN, Location.SKANE, Location.STOCKHOLM, Location.SODERMANLAND,
                Location.UPPSALA, Location.VARMLAND, Location.VASTERBOTTEN, Location.VASTERNORRLAND, Location.VASTMANLAND, Location.VASTRAGOTALAND,
                Location.OREBRO, Location.OSTERGOTLAND, Location.Non);


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

    boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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

        if (ssn.getText().matches("^(19|20)([0-9]{2})([01-12]{2})([0-31]{2})([a-zA-Z0-9][0-9]{3})$")) {
            ssnLabel.setText("");
        }
        if (!ssn.getText().matches("^(19|20)([0-9]{2})([01-12]{2})([0-31]{2})([a-zA-Z0-9][0-9]{3})$")) {
            ssnLabel.setText("enter YYYYMMDDXXXX");
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
            if (!isDouble(salary.getText())) {
                salary.setText(" salary must be number");
            }
        }


        try {

            save(new Employee(ssn.getText(), password.getText(), name.getText(), email.getText(),
                    phone.getText(), address.getText(), Location.STOCKHOLM, Employee.Role.ADMIN, Double.parseDouble(salary.getText())));
       saveLabel.setText("saved");
        } catch (Exception exception) {
            saveLabel.setText("did't save ");

        }
    }
}


