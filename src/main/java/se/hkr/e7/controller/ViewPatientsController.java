package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Patient;

import java.util.List;

public class ViewPatientsController extends Controller {

    public Button exitButton;
    public Button backButton;
    public ChoiceBox<String> choiceBox;

    @FXML
    public void initialize() {
        exitButton.setOnAction(this::exit);
        backButton.setOnAction(actionEvent -> loadScene("view/AdminDashboard.fxml", actionEvent));

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
