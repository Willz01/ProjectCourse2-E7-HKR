package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Mail;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Person;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public class AddPatientController extends Controller {

    public Button addButton;
    public TextField ssnTextField;
    public TextField nameTextField;
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

            String password = Mail.generatePassword(10);


            if (patient == null) {
                patient = new Patient(
                        ssnTextField.getText(),
                        password,
                        nameTextField.getText(),
                        emailTextField.getText(),
                        phoneTextField.getText(),
                        addressTextField.getText()
                );
                DatabaseHandler.save(patient);
            } else {
                patient.setName(nameTextField.getText());
                patient.updatePassword(password);
                patient.setEmail(emailTextField.getText());
                patient.setPhone(phoneTextField.getText());
                patient.setAddress(addressTextField.getText());
                patient.setEnabled(true);
                DatabaseHandler.save(patient);
            }

            try {

                Mail.send("New account information",
                        String.format("Dear %s,<br>Your account has been created and your password is: <br> %s <br> Best regards.",
                                patient.getName(), password), patient);
                showConfirmation("Success", "The patient was added, password was sent to the patient email.");
            } catch (UnsupportedEncodingException | MessagingException e) {
                showError("Patient was added, Email could not be sent and the password is: " + password);
            }

            ssnTextField.setText("");
            nameTextField.setText("");
            emailTextField.setText("");
            phoneTextField.setText("");
            addressTextField.setText("");
        } catch (Exception exception) {
            showError("Couldn't save ", "There was an error adding the patient.");


        }
    }
}