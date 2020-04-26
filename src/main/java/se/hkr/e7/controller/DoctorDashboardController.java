package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DoctorDashboardController extends Controller {
    public Button exitButton;
    public Button backButton;
    public Button viewPatientButton;
    public Button createCaseButton;
    public Button viewResultsButton;
    public Button listCasesButton;

    @FXML
    public void initialize() {
        exitButton.setOnAction(this::exit);
        backButton.setOnAction(actionEvent -> loadScene("view/StaffLogin.fxml", actionEvent));
        viewPatientButton.setOnAction(actionEvent -> System.out.println("Not implemented"));
        createCaseButton.setOnAction(actionEvent -> System.out.println("Not implemented"));
        viewResultsButton.setOnAction(actionEvent -> System.out.println("Not implemented"));
        listCasesButton.setOnAction(actionEvent -> System.out.println("Not implemented"));
    }
}
