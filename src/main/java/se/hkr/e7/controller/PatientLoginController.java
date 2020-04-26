package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Patient;

public class PatientLoginController extends Controller {

    public Button exitButton;
    public Button backButton;
    public Button loginButton;
    public TextField ssnTextField;
    public TextField passwordTextField;
    public Label errorLabel;
    public Label passwordCheckLabel;

    @FXML
    public void initialize() {
        exitButton.setOnAction(this::exit);
        backButton.setOnAction(actionEvent -> loadScene("view/Welcome.fxml", actionEvent));
        loginButton.setOnAction(this::login);
    }

    private void login(ActionEvent actionEvent) {
        passwordCheckLabel.setText(null);
        errorLabel.setText(null);

        if (passwordTextField.getText().equals("") || ssnTextField.getText().equals("")) {
            errorLabel.setText("fields can not be empty ");
        } else {
            try {
                Patient patient = DatabaseHandler.load(Patient.class, ssnTextField.getText());

                if (patient.getSsn() != null && patient.checkPassword(passwordTextField.getText())) {
                    loadScene("view/PatientDashboard.fxml", actionEvent);
                }

                if (patient.getSsn() != null && !patient.checkPassword(passwordTextField.getText())) {
                    passwordCheckLabel.setText("wrong password ");
                }
            } catch (Exception exception) {
                errorLabel.setText("could not login , please check your password and ssn ");
            }
        }
    }
}