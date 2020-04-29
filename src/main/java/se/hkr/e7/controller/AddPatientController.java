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
    public Label saveLabel;

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
        saveLabel.setText("");

        if (Person.isValidSsn(ssnTextField.getText())) {
            ssnLabel.setText("");
        } else {
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
        }
        if (!(ssnTextField.getText().equals("")) && !(password.getText().equals("")) && !(name.getText().equals("")) && !(email.getText() == "") &&
                !(phone.getText().equals("")) && !(address.getText().equals(""))) {
            try {

                DatabaseHandler.save(new Patient(ssnTextField.getText(), password.getText(), name.getText(), email.getText(),
                        phone.getText(), address.getText()));
                showConfirmation("", "saved");
            } catch (Exception exception) {
                showError("did't save ", "this ssn is already in the System ");

            }
        }
    }
}

