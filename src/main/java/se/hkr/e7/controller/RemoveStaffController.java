package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class RemoveStaffController extends Controller {

    public Button exitButton;
    public Button backButton;

    @FXML
    public void initialize() {
        exitButton.setOnAction(actionEvent ->
        {
            System.exit(0);
        });        backButton.setOnAction(actionEvent -> {
            try {
                loadScene("view/AdminDashboard.fxml", actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
