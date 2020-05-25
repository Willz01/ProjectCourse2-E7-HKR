package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Person;

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
        addEmployeeButton.setOnAction(actionEvent -> loadScene("view/AddEmployee.fxml"));
        addPatientButton.setOnAction(actionEvent -> loadScene("view/AddPatient.fxml"));
        viewResultsButton.setOnAction(actionEvent -> loadScene("view/ViewResults.fxml"));
        viewPatientButton.setOnAction(actionEvent -> loadScene("view/ViewPatients.fxml"));
        viewStaffButton.setOnAction(actionEvent -> loadScene("view/ViewStaff.fxml"));
        removeAccountButton.setOnAction(actionEvent -> loadScene("view/RemoveAccount.fxml"));
    }

    public void search(ActionEvent actionEvent) {
        if (Person.isValidSsn(searchText.getText())) {
            Patient patient = DatabaseHandler.load(Patient.class, searchText.getText());
            Employee employee = DatabaseHandler.load(Employee.class, searchText.getText());

            if (patient == null && employee == null) {
                showError("Entered SSN doesn't exist in the system.");
                return;
            }

            Singleton singleton = Singleton.getInstance();
            if (patient == null) {
                singleton.setEmployee(employee);
                singleton.setPatient(null);
            } else {
                singleton.setPatient(patient);
                singleton.setEmployee(null);
            }

            loadScene("view/Search.fxml");
        } else {
            showError("SSN must be valid 10 digits as YYMMDDXXXX.");
        }
    }
}



