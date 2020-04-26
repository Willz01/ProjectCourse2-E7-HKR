package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AddStaffController extends Controller {

    public Button backButton;
    public Button exitButton;

    @FXML
    public void initialize() {
        backButton.setOnAction(actionEvent -> loadScene("view/AdminDashboard.fxml", actionEvent));
        exitButton.setOnAction(this::exit);
    }
}
