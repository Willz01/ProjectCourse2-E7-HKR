package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WelcomeController extends Controller {

    public Button exitButton;
    public Button loginButton;

    @FXML
    public void initialize() {
        exitButton.setOnAction(this::exit);
        loginButton.setOnAction(actionEvent -> loadScene("view/Login.fxml", actionEvent));
    }
}
