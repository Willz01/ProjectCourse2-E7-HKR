package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Patient;

import java.io.IOException;

public class PatientLoginController extends Controller {

    public TextField ssnText;
    public TextField passwordText;
    public Label error1;
    public Label passwordCheck;

    public void Back(ActionEvent actionEvent) throws IOException {
       loadScene("view/Welcome.fxml",actionEvent);
    }

    public void Exit() {
        System.exit(0);
    }


    public void patientLogin(ActionEvent actionEvent) {
        passwordCheck.setText(null);
        error1.setText(null);

        if (passwordText.getText().equals("") || ssnText.getText().equals("")) {
            showError(new Exception("Fields cant be empty"));
        } else {
            try {
                Patient patient = DatabaseHandler.load(Patient.class, ssnText.getText());

                if (patient.getSsn() != null && patient.checkPassword(passwordText.getText())) {
                    loadScene("view/PatientDashboard.fxml",actionEvent);
                }

                if (patient.getSsn() != null && !patient.checkPassword(passwordText.getText())) {
                    showError(new Exception("Wrong password"));
                }
            } catch (Exception exception) {
                showError(new Exception("could not login , please check your password and ssn"));
                    }
                    }
                }




    private void showError(Exception e) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("input error please retry");
        error.setContentText(e.getMessage());
        error.showAndWait();
    }
}