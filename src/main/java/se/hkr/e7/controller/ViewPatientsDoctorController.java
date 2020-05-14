package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Patient;

import java.util.List;

public class ViewPatientsDoctorController extends Controller {

    public ChoiceBox<String> choiceBox;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/ViewPatientsDoctor.fxml");
        List<Patient> patients = DatabaseHandler.loadAll(Patient.class);
        int counter = 0;

        for (Patient patient : patients) {
            counter++;
            choiceBox.getItems().add(counter + "- Name : " + patient.getName() + " ----  " + " Email : " + patient.getEmail() + " ----  " + " Phone : " + patient.getPhone() + "  ---- " + " SSN: " + patient.getSsn());
        }

        choiceBox.getItems().add("View Patient");
        choiceBox.setValue("View Patient");
    }
}
