package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Patient;

public class AddPatientDoctorController extends Controller {

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
        Singleton.getInstance().addSceneHistory("view/AddPatientDoctor.fxml");
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
        if (!(ssn.getText().isBlank()) && !(password.getText().isBlank()) && !(name.getText().isBlank()) && !(email.getText().isBlank()) &&
                !(phone.getText().isBlank()) && !(address.getText().isBlank())) {
            try {

                DatabaseHandler.save(new Patient(ssn.getText(), password.getText(), name.getText(), email.getText(),
                        phone.getText(), address.getText()));
                showConfirmation("", "Saved");
            } catch (Exception exception) {
                showError("Couldn't save ", "This SSN is already in the System ");

            }
        }
    }
}

