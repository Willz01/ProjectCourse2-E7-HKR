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
            default:
                helpHeading.setText("Help Menu");
                helpContent.setText("Help menu could not be loaded.");
        }
    }
}
