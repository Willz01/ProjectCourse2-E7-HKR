package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import se.hkr.e7.model.*;

public class AdminDashboardController extends Controller {

    public Button addPatientButton;
    public Button addEmployeeButton;
    public Button viewResultsButton;
    public Button viewPatientButton;
    public Button viewStaffButton;
    public Button removeAccountButton;
    public Button searchButton;
    public TextField searchText;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AdminDashboard.fxml");
        addEmployeeButton.setOnAction(actionEvent -> loadScene("view/AddEmployee.fxml", actionEvent));
        addPatientButton.setOnAction(actionEvent -> loadScene("view/AddPatient.fxml", actionEvent));
        viewResultsButton.setOnAction(actionEvent -> loadScene("view/ViewResults.fxml", actionEvent));
        viewPatientButton.setOnAction(actionEvent -> loadScene("view/ViewPatients.fxml", actionEvent));
        viewStaffButton.setOnAction(actionEvent -> loadScene("view/ViewStaff.fxml", actionEvent));
        removeAccountButton.setOnAction(actionEvent -> loadScene("view/RemoveAccount.fxml", actionEvent));
    }

    public void search(ActionEvent actionEvent) {
        if (Person.isValidSsn(searchText.getText())) {
            try {
                Patient patient = DatabaseHandler.load(Patient.class, searchText.getText());
                Singleton.getInstance().setPatient(patient);
            } catch (Exception e) {
                showError("Not a Patient", "");
            }
            try {
                Employee employee = DatabaseHandler.load(Employee.class, searchText.getText());
                Singleton.getInstance().setEmployee(employee);
            } catch (Exception e) {
                showError("Not Staff");
            } finally {
                loadScene("view/Search.fxml", actionEvent);
            }
        } else {
            showError("input in YYMMDDXXXX form");
        }
    }
}



