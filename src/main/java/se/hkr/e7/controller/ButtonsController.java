package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import se.hkr.e7.model.Singleton;

public class ButtonsController extends Controller {
    public Button backButton;

    @FXML
    public void initialize() {
        backButton.setOnAction(actionEvent -> loadScene(Singleton.getInstance().getPreviousScene(), actionEvent));
    }
}
