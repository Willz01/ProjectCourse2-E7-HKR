package se.hkr.e7.controller;

import javafx.event.ActionEvent;

import java.io.IOException;

public class WelcomeController extends Controller {

    public void StaffLogin(ActionEvent actionEvent) throws IOException {
        loadScene("view/StaffLogin.fxml",actionEvent);
    }

    public void PatientLogin(ActionEvent actionEvent) throws IOException {
       loadScene("view/patientLogin.fxml",actionEvent);
    }

    public void Exit() {
        System.exit(0);
    }
}
