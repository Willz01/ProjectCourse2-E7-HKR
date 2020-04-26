package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminDashboardController extends Controller {
    public Button exitButton;
    public Button backButton;
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
        exitButton.setOnAction(this::exit);
        backButton.setOnAction(actionEvent -> {
            try {
                loadScene("view/StaffLogin.fxml", actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        addAdminButton.setOnAction(actionEvent -> {
            try {
                loadScene("view/AddAdmin.fxml", actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        addPatientButton.setOnAction(actionEvent -> {
            try {
                loadScene("view/AddPatient.fxml", actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        addStaffButton.setOnAction(actionEvent -> {
            try {
                loadScene("view/AddStaff.fxml", actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        viewResultsButton.setOnAction(actionEvent -> {
            try {
                loadScene("view/ViewResults.fxml", actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        viewPatientButton.setOnAction(actionEvent -> {
            try {
                loadScene("view/ViewPatients.fxml", actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        viewStaffButton.setOnAction(actionEvent -> {
            try {
                loadScene("view/ViewStaff.fxml", actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        removeStaffButton.setOnAction(actionEvent -> {
            try {
                loadScene("view/RemoveStaff.fxml", actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        removeAdminButton.setOnAction(actionEvent -> {
            try {
                loadScene("view/RemoveAdmin.fxml", actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        searchButton.setOnAction(actionEvent -> System.out.println("Not implemented yet"));
    }
}
