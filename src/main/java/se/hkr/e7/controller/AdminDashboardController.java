package se.hkr.e7.controller;

import com.sun.javafx.stage.EmbeddedWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Singleton;

import java.net.URL;

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
        backButton.setOnAction(actionEvent -> loadScene("view/StaffLogin.fxml", actionEvent));
        addAdminButton.setOnAction(actionEvent -> loadScene("view/AddAdmin.fxml", actionEvent));
        addPatientButton.setOnAction(actionEvent -> loadScene("view/AddPatient.fxml", actionEvent));
        addStaffButton.setOnAction(actionEvent -> loadScene("view/AddStaff.fxml", actionEvent));
        viewResultsButton.setOnAction(actionEvent -> loadScene("view/ViewResults.fxml", actionEvent));
        viewPatientButton.setOnAction(actionEvent -> loadScene("view/ViewPatients.fxml", actionEvent));
        viewStaffButton.setOnAction(actionEvent -> loadScene("view/ViewStaff.fxml", actionEvent));
        removeStaffButton.setOnAction(actionEvent -> loadScene("view/RemoveStaff.fxml", actionEvent));
        removeAdminButton.setOnAction(actionEvent -> loadScene("view/RemoveAdmin.fxml", actionEvent));

    }

    public void search(ActionEvent actionEvent) {

        if (!searchText.getText().matches("^([0-9]{2})([0-9]{2})([0-9]{2})([a-zA-Z0-9][0-9]{3})$")) {
            showError("input in YYMMDDXXXX form");
        }
        if (searchText.getText().matches("^([0-9]{2})([0-9]{2})([0-9]{2})([a-zA-Z0-9][0-9]{3})$")) {
            try {
                Patient patient = DatabaseHandler.load(Patient.class, searchText.getText());
                Singleton.getInstance().setPatient(patient);

            }catch (Exception e){
                showError("Not a Patient", "");

            }try {
                Employee employee = DatabaseHandler.load(Employee.class, searchText.getText());
                Singleton.getInstance().setEmployee(employee);

            }catch (Exception e){
                showError("Not Staff");

            }finally {
                loadScene("view/Search.fxml",actionEvent);
            }

        }
    }
}
