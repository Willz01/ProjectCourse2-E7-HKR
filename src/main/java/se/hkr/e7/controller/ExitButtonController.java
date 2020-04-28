package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ExitButtonController extends Controller {
    public Button exitButton;

    @FXML
    public void initialize() {
        exitButton.setOnAction(this::exit);
    }
}
