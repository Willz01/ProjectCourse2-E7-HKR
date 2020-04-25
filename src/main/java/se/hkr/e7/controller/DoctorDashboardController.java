package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class DoctorDashboardController extends Controller {
    public Button Back;
    public Button Exit;
    public Button viewPatient;
    public Button createCase;
    public Button viewResults;
    public Button ListOfCases;

    public void Back(ActionEvent actionEvent) throws IOException {
        loadScene("view/staffLogin.fxml",actionEvent);
    }

    public void Exit() {
        System.exit(0);
    }

    public void viewPatient(ActionEvent actionEvent) {
    }

    public void createCase(ActionEvent actionEvent) {
    }

    public void viewResults(ActionEvent actionEvent) {
    }

    public void ListOfCases(ActionEvent actionEvent) {
    }
}
