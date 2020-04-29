package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import se.hkr.e7.model.Singleton;

public class DoctorDashboardController extends Controller {

    public Button viewPatientButton;
    public Button AddResultButton;
    public Button viewResultsButton;
    public Button listCasesButton;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/DoctorDashboard.fxml");
        viewPatientButton.setOnAction(actionEvent -> loadScene("view/ViewPatientsDoctor.fxml", actionEvent));
        AddResultButton.setOnAction(actionEvent -> loadScene("view/AddResult.fxml", actionEvent));
        viewResultsButton.setOnAction(actionEvent -> loadScene("view/ViewResultsDoctor.fxml", actionEvent));
        listCasesButton.setOnAction(actionEvent -> loadScene("view/AddPatientDoctor.fxml", actionEvent));
    }
}
