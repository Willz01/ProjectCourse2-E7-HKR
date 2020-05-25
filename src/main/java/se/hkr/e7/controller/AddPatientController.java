package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Person;

public class AddPatientController extends Controller {

    public Button addButton;
    public TextField ssnTextField;
    public TextField nameTextField;
    public TextField passwordTextField;
    public TextField addressTextField;
    public TextField emailTextField;
    public TextField phoneTextField;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AddPatient.fxml");
        addButton.setOnAction(this::addPatient);
    }

    private void addPatient(ActionEvent actionEvent) {
        if (!Person.isValidSsn(ssnTextField.getText())) {
            showError("The SSN is no valid");
            return;
        }

        if (nameTextField.getText().isBlank()) {
            showError("Enter a name");
            return;
        }

        if (addressTextField.getText().isBlank()) {
            showError("Enter an address");
            return;
        }

        if (!Person.isValidPassword(passwordTextField.getText())) {
            showError("Enter a valid password. The password should be at least 8 characters in length and have an " +
                    "uppercase and a lowercase letter as well as a number.");
            return;
        }

        if (!Person.isValidEmail(emailTextField.getText())) {
            showError("Enter a valid email");
            return;
        }

        if (!Person.isValidPhone(phoneTextField.getText())) {
            showError("Enter a valid phone number");
            return;
        }

        try {
            Patient patient = Patient.load(ssnTextField.getText());

            if (patient != null && patient.isEnabled()) {
                showError("The account already exists.");
                return;
            }

            if (patient == null) {
                DatabaseHandler.save(new Patient(
                        ssnTextField.getText(),
                        passwordTextField.getText(),
                        nameTextField.getText(),
                        emailTextField.getText(),
                        phoneTextField.getText(),
                        addressTextField.getText()
                ));
            } else {
                patient.setName(nameTextField.getText());
                patient.updatePassword(passwordTextField.getText());
                patient.setEmail(emailTextField.getText());
                patient.setPhone(phoneTextField.getText());
                patient.setAddress(addressTextField.getText());
                patient.setEnabled(true);
                DatabaseHandler.save(patient);
            }

            showConfirmation("Success", "The patient was added.");
            ssnTextField.setText("");
            passwordTextField.setText("");
            nameTextField.setText("");
            emailTextField.setText("");
            phoneTextField.setText("");
            addressTextField.setText("");
        } catch (Exception exception) {
            showError("Couldn't save ", "There was an error adding the patient.");
        }
    }
}