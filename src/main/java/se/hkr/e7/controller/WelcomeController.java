package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


import java.io.IOException;

public class WelcomeController extends Controller {

    public Button staffLoginButton;
    public Button patientLoginButton;
    public Button exitButton;

    @FXML
    public void initialize() {
        exitButton.setOnAction(this::exit);
        patientLoginButton.setOnAction(actionEvent -> {
            try {
                loadScene("view/patientLogin.fxml", actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        staffLoginButton.setOnAction(actionEvent -> {
            try {
                loadScene("view/staffLogin.fxml", actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

}
}
