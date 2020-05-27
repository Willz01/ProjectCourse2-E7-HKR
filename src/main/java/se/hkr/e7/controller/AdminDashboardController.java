package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;
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
        searchButton.setOnAction(this::search);
    }

    public void search(ActionEvent actionEvent) {
        if (Person.isValidSsn(searchText.getText())) {
            Person person = DatabaseHandler.load(Person.class, searchText.getText());

            if (person == null) {
                showError("Entered SSN doesn't exist in the system.");
                return;
            }

            Singleton.getInstance().setPerson(person);
            loadScene("view/Search.fxml");
        } else {
            showError("SSN must be valid 10 digits as YYMMDDXXXX.");
        }
    }
}



