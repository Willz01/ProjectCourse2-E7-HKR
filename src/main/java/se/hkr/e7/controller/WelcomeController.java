package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WelcomeController extends Controller {

    public Button exitButton;
    public Button staffLoginButton;
    public Button patientLoginButton;

    @FXML
    public void initialize() {
        exitButton.setOnAction(this::exit);
        staffLoginButton.setOnAction(actionEvent -> loadScene("view/StaffLogin.fxml", actionEvent));
        patientLoginButton.setOnAction(actionEvent -> loadScene("view/patientLogin.fxml", actionEvent));
    }
}
