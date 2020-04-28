package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import se.hkr.e7.model.Singleton;

public class AdminDashboardController extends Controller {
    public Button addAdminButton;
    public Button addPatientButton;
    public Button addStaffButton;
    public Button viewResultsButton;
    public Button viewPatientButton;
    public Button viewStaffButton;
    public Button removeStaffButton;
    public Button removeAdminButton;
    public Button searchButton;
    public TextField searchText;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AdminDashboard.fxml");
        addAdminButton.setOnAction(actionEvent -> loadScene("view/AddAdmin.fxml", actionEvent));
        addPatientButton.setOnAction(actionEvent -> loadScene("view/AddPatient.fxml", actionEvent));
        addStaffButton.setOnAction(actionEvent -> loadScene("view/AddStaff.fxml", actionEvent));
        viewResultsButton.setOnAction(actionEvent -> loadScene("view/ViewResults.fxml", actionEvent));
        viewPatientButton.setOnAction(actionEvent -> loadScene("view/ViewPatients.fxml", actionEvent));
        viewStaffButton.setOnAction(actionEvent -> loadScene("view/ViewStaff.fxml", actionEvent));
        removeStaffButton.setOnAction(actionEvent -> loadScene("view/RemoveStaff.fxml", actionEvent));
        removeAdminButton.setOnAction(actionEvent -> loadScene("view/RemoveAdmin.fxml", actionEvent));
        searchButton.setOnAction(actionEvent -> System.out.println("Not implemented yet"));
    }
}
