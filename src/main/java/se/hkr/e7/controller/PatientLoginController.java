package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Singleton;

public class PatientLoginController extends Controller {

    public Button exitButton;
    public Button backButton;
    public Button loginButton;
    public TextField ssnTextField;
    public TextField passwordTextField;
    public Label passwordCheckLabel;
    public Label errorLabel;
    public PasswordField passwordField;
    public CheckBox checkBox;

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


        System.out.println(passwordTextField.getText());
    }

    private void login(ActionEvent actionEvent) {
        passwordCheckLabel.setText(null);
        errorLabel.setText(null);
        if (passwordTextField.getText().equals("") || ssnTextField.getText().equals("")) {
            showError("fields can not be empty ");
        } else {
            try {
                Patient patient = DatabaseHandler.load(Patient.class, ssnTextField.getText());

                if (patient.checkPassword(passwordTextField.getText())) {
                    Singleton.getInstance().setPatient(patient);
                    loadScene("view/PatientDashboard.fxml", actionEvent);

                }
                if (!patient.checkPassword(passwordTextField.getText())) {
                    showError("wrong password ");
                }

            } catch (Exception exception) {
                showError("Could not login , please check your password and ssn");
            }
        }
    }
}