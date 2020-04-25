package se.hkr.e7.controller;

import javafx.event.ActionEvent;

import javafx.scene.control.Button;

import java.io.IOException;

public class AddPatientController extends Controller {

    public Button Back;

    public void Back(ActionEvent actionEvent) throws IOException {
        loadScene("view/AdminDashboard.fxml",actionEvent);
    }



    public void Exit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
