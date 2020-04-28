package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import se.hkr.e7.model.Singleton;

public class RemoveAdminController extends Controller {

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/RemoveAdmin.fxml");
    }
}
