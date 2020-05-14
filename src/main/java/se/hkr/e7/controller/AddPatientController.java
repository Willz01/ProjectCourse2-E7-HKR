package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Person;
import se.hkr.e7.model.Singleton;

public class AddPatientController extends Controller {

    public Button addButton;
    public TextField ssnTextField;
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

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AddPatient.fxml");
        addButton.setOnAction(this::addPatient);
    }

    private void addPatient(ActionEvent actionEvent) {
        nameLabel.setText("");
        ssnLabel.setText("");
        passwordLabel.setText("");
        emailLabel.setText("");
        phoneLabel.setText("");
        addressLabel.setText("");

        if (Person.isValidSsn(ssnTextField.getText())) {
            ssnLabel.setText("");
        } else {
            ssnLabel.setText("Input format YYMMDDXXXX");
        }

        if (name.getText().isBlank()) {
            nameLabel.setText("Field can't be empty");
        }
        if (address.getText().isBlank()) {
            addressLabel.setText("Field can't be empty");
        }
        if (password.getText().isBlank()) {
            passwordLabel.setText("Field can't be empty");
        }
        if (email.getText().isBlank()) {
            emailLabel.setText("Field can't be empty");
        }
        if (phone.getText().isBlank()) {
            phoneLabel.setText("Field can't be empty");
        }

        if (!Person.isValidPassword(password.getText())) {
            showError("Enter a valid password. The password should be at least 8 characters in length and have an " +
                    "uppercase and a lowercase letter as well as a number.");
            return;
        }

        if (!ssnTextField.getText().isBlank() && !password.getText().isBlank() && !name.getText().isBlank()
                && !email.getText().isBlank() && !phone.getText().isBlank() && !address.getText().isBlank()) {
            try {
                DatabaseHandler.save(new Patient(ssnTextField.getText(), password.getText(),
                        name.getText(), email.getText(), phone.getText(), address.getText()));
                showConfirmation("Success", "The patient was added.");
            } catch (Exception exception) {
                showError("Couldn't save", "There was an error adding the patient.");
            }
        }
    }
}
