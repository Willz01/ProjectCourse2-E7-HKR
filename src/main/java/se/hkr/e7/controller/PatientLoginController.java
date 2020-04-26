package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Singleton;

import java.io.IOException;

public class PatientLoginController extends Controller {

    public TextField ssnText;
    public TextField passwordText;
    public Label passwordCheck;
    public Label error1;
    public PasswordField passwordField;
    public CheckBox CheckBox;


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

    public void StaffLogin(ActionEvent actionEvent) {

        passwordCheck.setText(null);
        error1.setText(null);
        if (passwordText.getText().equals("") || ssnText.getText().equals("")) {
            showError("fields can not be empty ");
        } else {
            try {
                Patient patient = DatabaseHandler.load(Patient.class, ssnText.getText());
                Singleton.getInstance().setPatient(patient);


                if (patient.checkPassword(passwordText.getText())) {
                    loadScene("view/PatientDashboard.fxml",actionEvent);
                }

                if (patient.getSsn() != null && !patient.checkPassword(passwordText.getText())) {
                    showError("wrong password ");
                }
                
            } catch (Exception exception) {
                showError("Could not login , please check your password and ssn");
            }
        }
    }

    public void Back(ActionEvent actionEvent) throws IOException {
        loadScene("view/Welcome.fxml",actionEvent);
    }

    public void Exit() {
        System.exit(0);
    }
}