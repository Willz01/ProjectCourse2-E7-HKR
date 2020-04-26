package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AnalyserDashboardController extends Controller {
    public Button exitButton;
    public Button backButton;

    @FXML
    public void initialize() {
        exitButton.setOnAction(actionEvent ->
        {
            System.exit(0);
        });        backButton.setOnAction(actionEvent -> {
            try {
                loadScene("view/StaffLogin.fxml", actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}