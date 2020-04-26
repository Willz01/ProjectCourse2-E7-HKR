package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Patient;

import java.io.IOException;

public class PatientLoginController extends Controller {

    public TextField ssnText;
    public PasswordField passwordField;
    public Label error1;
    public Label passwordCheck;
    public TextField passwordText;
    public CheckBox CheckBox;
    public Button loginButton;

    public void Back(ActionEvent actionEvent) throws IOException {
        loadScene("view/Welcome.fxml", actionEvent);
    }

    public void Exit() {
        System.exit(0);
    }


    @FXML
    public void initialize() {
        passwordText.setManaged(false);
        passwordText.setVisible(true);
        passwordText.managedProperty().bind(CheckBox.selectedProperty());
        passwordText.visibleProperty().bind(CheckBox.selectedProperty());

        passwordField.managedProperty().bind(CheckBox.selectedProperty().not());
        passwordField.visibleProperty().bind(CheckBox.selectedProperty().not());
        passwordText.textProperty().bindBidirectional(passwordField.textProperty());

    }

    public void patientLogin(ActionEvent actionEvent) {
        passwordCheck.setText(null);
        error1.setText(null);
        if (passwordField.getText().equals("") || ssnText.getText().equals("")) {
            showError("Fields cant be empty", "Please enter a ssn and a password.");
        } else {
            try {
                Patient patient = DatabaseHandler.load(Patient.class, ssnText.getText());

                if (patient.getSsn() != null && patient.checkPassword(passwordField.getText())) {
                    loadScene("view/PatientDashboard.fxml", actionEvent);
                }

                if (patient.getSsn() != null && !patient.checkPassword(passwordField.getText())) {
                    showError("Wrong password");
                }
            } catch (Exception exception) {
                showError("could not login , please check your password and ssn");
            }
        }

    }
}