package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Singleton;

public class PatientLoginController extends Controller {

    public Button exitButton;
    public Button backButton;
    public Button loginButton;
    public TextField ssnTextField;
    public Label errorLabel;
    public Label passwordCheckLabel;
    public PasswordField passwordField;
    public TextField passwordTextField;
    public javafx.scene.control.CheckBox checkBox;

    @FXML
    public void initialize() {
        exitButton.setOnAction(this::exit);
        backButton.setOnAction(actionEvent -> loadScene("view/Welcome.fxml", actionEvent));
        loginButton.setOnAction(this::login);
        passwordTextField.setManaged(false);
        passwordTextField.setVisible(true);
        passwordTextField.managedProperty().bind(checkBox.selectedProperty());
        passwordTextField.visibleProperty().bind(checkBox.selectedProperty());

        passwordField.managedProperty().bind(checkBox.selectedProperty().not());
        passwordField.visibleProperty().bind(checkBox.selectedProperty().not());
        passwordTextField.textProperty().bindBidirectional(passwordField.textProperty());
    }

    private void login(ActionEvent actionEvent) {
        passwordCheckLabel.setText(null);
        errorLabel.setText(null);

        if (passwordField.getText().equals("") || ssnTextField.getText().equals("")) {
            showError("Fields cant be empty", "Please enter a ssn and a password.");
        } else {
            try {
                Patient patient = DatabaseHandler.load(Patient.class, ssnTextField.getText());

                if (patient.getSsn() != null && patient.checkPassword(passwordField.getText())) {
                    loadScene("view/PatientDashboard.fxml", actionEvent);
                    Singleton.getInstance().setPatient(patient);
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