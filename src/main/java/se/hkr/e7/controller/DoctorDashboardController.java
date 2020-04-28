package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DoctorDashboardController extends Controller {
    public Button exitButton;
    public Button backButton;
    public Button viewPatientButton;
    public Button addResultButton;
    public Button viewResultsButton;
    public Button addPatientButton;

    @FXML
    public void initialize() {
        exitButton.setOnAction(this::exit);
        backButton.setOnAction(actionEvent -> loadScene("view/StaffLogin.fxml", actionEvent));
        viewPatientButton.setOnAction(actionEvent -> loadScene("view/ViewPatientsDoctor.fxml", actionEvent));
        addResultButton.setOnAction(actionEvent  ->  loadScene("view/AddResult.fxml", actionEvent));
        viewResultsButton.setOnAction(actionEvent -> loadScene("view/ViewResultsDoctor.fxml", actionEvent));
        addPatientButton.setOnAction(actionEvent -> loadScene("view/AddPatientDoctor.fxml", actionEvent));
    }
}
