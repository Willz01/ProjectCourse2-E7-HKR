package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import se.hkr.e7.Singleton;

public class PatientDashboardController extends Controller {

    public Button changeInfoButton;
    public Button viewResultsButton;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/PatientDashboard.fxml");
        changeInfoButton.setOnAction(actionEvent -> loadScene("view/ChangeInfo.fxml"));
        viewResultsButton.setOnAction(actionEvent -> loadScene("view/ViewResults.fxml"));
    }
}
