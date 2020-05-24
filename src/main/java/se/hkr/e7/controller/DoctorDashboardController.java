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
        viewPatientButton.setOnAction(actionEvent -> loadScene("view/ViewPatientsDoctor.fxml", actionEvent));
        addResultButton.setOnAction(actionEvent -> loadScene("view/AddResult.fxml", actionEvent));
        viewResultsButton.setOnAction(actionEvent -> loadScene("view/ViewResultsDoctor.fxml", actionEvent));
        addPatientButton.setOnAction(actionEvent -> loadScene("view/AddPatient.fxml", actionEvent));
        edit.setOnAction(actionEvent -> loadScene("view/ChangeInfo.fxml", actionEvent));
    }
}
