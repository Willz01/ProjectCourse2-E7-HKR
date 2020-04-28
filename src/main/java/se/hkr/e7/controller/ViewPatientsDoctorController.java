package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Patient;


import java.util.List;

public class ViewPatientsDoctorController extends Controller {

    public Button exitButton;
    public Button backButton;
    public TextArea textarea;

    @FXML
    public void initialize() {
        exitButton.setOnAction(this::exit);
        backButton.setOnAction(actionEvent -> loadScene("view/DoctorDashboard.fxml", actionEvent));

        List<Patient> patients = DatabaseHandler.loadAll(Patient.class);


        StringBuilder stringBuilder = new StringBuilder();
        int counter = 0;
        for (Patient patient : patients) {
            counter++;
            stringBuilder.append(counter).append(" - ")
                    .append("Name  : ")
                    .append(patient.getName())
                    .append("   || Email : ")
                    .append(patient.getEmail())
                    .append("   || Phone : ")
                    .append(patient.getPhone())
                    .append("   || SSN : ")
                    .append(patient.getSsn())

                    .append(patient.getAddress() != null ? ", || Address : " + patient.getAddress() : "")
                    .append(System.lineSeparator());

        }
        textarea.setText(String.valueOf(stringBuilder));
    }
}
