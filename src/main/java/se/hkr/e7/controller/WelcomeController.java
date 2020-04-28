package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import se.hkr.e7.model.Singleton;

public class WelcomeController extends Controller {

    public Button staffLoginButton;
    public Button patientLoginButton;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/Welcome.fxml");
        staffLoginButton.setOnAction(actionEvent -> loadScene("view/StaffLogin.fxml", actionEvent));
        patientLoginButton.setOnAction(actionEvent -> loadScene("view/PatientLogin.fxml", actionEvent));
    }
}
