package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Patient;

import java.io.IOException;
import java.util.List;

public class ViewAllPatientController extends Controller {

    public ChoiceBox<String> choiceBox;

    public void Back(ActionEvent actionEvent) throws IOException {
        loadScene("view/AdminDashboard.fxml",actionEvent);
    }

    public void Exit() {
        System.exit(0);
    }

    @FXML
    public void initialize() {
        List<Patient> patients = DatabaseHandler.loadAll(Patient.class);
            int counter = 0;
            for (Patient patient : patients) {counter++;

                    choiceBox.getItems().add(counter+"- Name : " + patient.getName() + " ----  " +" Email : " + patient.getEmail() + " ----  " + " Phone : " + patient.getPhone() + "  ---- " + " SSN: " + patient.getSsn());
            }
            choiceBox.getItems().add("View Patient");

            choiceBox.setValue("View Patient");

    }
}
