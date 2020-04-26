package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Patient;

import java.io.IOException;

public class AddPatientController extends Controller {

    public Button backButton;
    public Button exitButton;
    public Button addButton;
    public TextField ssn;
    public TextField name;
    public TextField password;
    public TextField address;
    public TextField email;
    public TextField phone;
    public Label ssnLabel;
    public Label nameLabel;
    public Label passwordLabel;
    public Label addressLabel;
    public Label emailLabel;
    public Label phoneLabel;
    public Label saveLabel;

    @FXML
    public void initialize() {
        exitButton.setOnAction(this::exit);
        backButton.setOnAction(actionEvent -> {
            try {
                loadScene("view/AdminDashboard.fxml", actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        addButton.setOnAction(this::addPatient);
    }

    private void addPatient(ActionEvent actionEvent) {
        nameLabel.setText("");
        ssnLabel.setText("");
        passwordLabel.setText("");
        emailLabel.setText("");
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
            phoneLabel.setText(" can't be empty");


            try {

                DatabaseHandler.save(new Patient(ssn.getText(), password.getText(), name.getText(), email.getText(),
                        phone.getText(), address.getText()));
                saveLabel.setText("saved");
            } catch (Exception exception) {
                saveLabel.setText("did't save ");

            }
        }
    }
}
