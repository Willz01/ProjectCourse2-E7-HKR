package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import se.hkr.e7.Singleton;

public class DoctorDashboardController extends Controller {

    public Button viewPatientButton;
    public Button addResultButton;
    public Button viewResultsButton;
    public Button addPatientButton;
    public Button edit;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/DoctorDashboard.fxml");
        viewPatientButton.setOnAction(actionEvent -> loadScene("view/ViewPatients.fxml"));
        addResultButton.setOnAction(actionEvent -> loadScene("view/AddResult.fxml"));
        viewResultsButton.setOnAction(actionEvent -> loadScene("view/ViewResults.fxml"));
        addPatientButton.setOnAction(actionEvent -> loadScene("view/AddPatient.fxml"));
        edit.setOnAction(actionEvent -> loadScene("view/ChangeInfo.fxml"));
    }
}
