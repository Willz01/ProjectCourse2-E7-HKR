package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import se.hkr.e7.Singleton;

public class HelpController extends Controller {

    public Text helpHeading;
    public Text helpContent;

    @FXML
    public void initialize() {
        switch (Singleton.getInstance().getCurrentScene()) {
            case "view/Login.fxml":
                helpHeading.setText("Login");
                break;
            case "view/AdminDashboard.fxml":
                helpHeading.setText("Admin Dashboard");
                break;
            case "view/DoctorDashboard.fxml":
                helpHeading.setText("Doctor Dashboard");
                break;
            case "view/PatientDashboard.fxml":
                helpHeading.setText("Patient Dashboard");
                break;
            case "view/AddEmployee.fxml":
                helpHeading.setText("Add Employee");
                break;
            case "view/AddPatient.fxml":
                helpHeading.setText("Add Patient");
                break;
            case "view/AddResult.fxml":
                helpHeading.setText("Add Result");
                break;
            case "view/AnalyserDashboard.fxml":
                helpHeading.setText("Analyser Dashboard");
                break;
            case "view/AnalyserLineChart.fxml":
                helpHeading.setText("Analyser Line Chart");
                break;
            case "view/ChangeInfo.fxml":
                helpHeading.setText("Update Info");
                break;
            case "view/RemoveAccount.fxml":
                helpHeading.setText("Removing account");
                break;
            case "view/ViewPatients.fxml":
                helpHeading.setText("View patients");
                break;
            case "view/ViewResults.fxml":
                helpHeading.setText("View results");
                break;
            case "view/ViewStaff.fxml":
                helpHeading.setText("View staffs");
                break;
            default:
                helpHeading.setText("Help Menu");
                helpContent.setText("Help menu could not be loaded.");
        }
    }
}
