package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import se.hkr.e7.Singleton;

public class LogoutController extends Controller {
    public Button logoutButton;
    Node node;

    @FXML
    public void initialize() {
        if (Singleton.getInstance().getCurrentScene().equals("view/Login.fxml") || Singleton.getInstance().getCurrentScene().equals("view/Welcome.fxml")) {
            logoutButton.setDisable(true);
        }
        logoutButton.setOnAction(actionEvent -> {
            loadScene("view/Login.fxml", node);
        });
    }
}
