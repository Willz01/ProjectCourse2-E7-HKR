package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Result;

import java.util.List;


public class ViewResultsDoctorController extends Controller {

    public ChoiceBox<String> choiceBox;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/ViewResultsDoctor.fxml");
        List<Result> results = DatabaseHandler.loadAll(Result.class);

        for (Result result : results) {
            if (result.getStatus() == Result.Status.PENDING) {
                choiceBox.getItems().add(result.getStatus() + " ----  " + "Patient SSN:  " + result.getPatient().getSsn() + " ---- " + " Date : " + result.getDateTime() + "  ---- " + " Doctor name: " + result.getExaminer().getName() + "  ---- " + " Doctor SSN: " + result.getExaminer().getSsn());
            }
        }
        for (Result result : results) {
            if (result.getStatus() == Result.Status.POSITIVE) {
                choiceBox.getItems().add(result.getStatus() + " ----  " + "Patient SSN:  " + result.getPatient().getSsn() + " ---- " + " Date : " + result.getDateTime() + "  ---- " + " Doctor name: " + result.getExaminer().getName() + "  ---- " + " Doctor SSN: " + result.getExaminer().getSsn());
            }
        }

        for (Result result : results) {
            if (result.getStatus() == Result.Status.NEGATIVE) {
                choiceBox.getItems().add(result.getStatus() + " ----  " + "Patient SSN:  " + result.getPatient().getSsn() + " ---- " + " Date : " + result.getDateTime() + "  ---- " + " Doctor name: " + result.getExaminer().getName() + "  ---- " + " Doctor SSN: " + result.getExaminer().getSsn());
            }
        }

        choiceBox.getItems().add("View Results");
        choiceBox.setValue("View Results");
    }
}
