package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import se.hkr.e7.Singleton;

public class LogoutController extends Controller {
    public Button logoutButton;

    @FXML
    public void initialize() {
        logoutButton.setOnAction(actionEvent -> {
            Singleton.getInstance().clear();
            loadScene("view/Login.fxml");
        });
    }
}
