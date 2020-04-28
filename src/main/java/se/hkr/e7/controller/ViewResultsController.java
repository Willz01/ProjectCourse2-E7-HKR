package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Result;
import se.hkr.e7.model.Singleton;

import java.util.List;


public class ViewResultsController extends Controller {

    public ChoiceBox<String> choiceBox;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/ViewResults.fxml");
        List<Result> results = DatabaseHandler.loadAll(Result.class);

        for (Result result : results) {
            if (result.getStatus() == Result.Status.PENDING) {
                choiceBox.getItems().add(result.getStatus() + " ----  " + "Patient SSN:  " + result.getPatient().getSsn() + " ---- " + " Date : " + result.getDate() + "  ---- " + " Doctor name: " + result.getExaminer().getName() + "  ---- " + " Doctor SSN: " + result.getExaminer().getSsn());
            }
        }
        for (Result result : results) {
            if (result.getStatus() == Result.Status.POSITIVE) {
                choiceBox.getItems().add(result.getStatus() + " ----  " + "Patient SSN:  " + result.getPatient().getSsn() + " ---- " + " Date : " + result.getDate() + "  ---- " + " Doctor name: " + result.getExaminer().getName() + "  ---- " + " Doctor SSN: " + result.getExaminer().getSsn());
            }
        }

        for (Result result : results) {
            if (result.getStatus() == Result.Status.NEGATIVE) {
                choiceBox.getItems().add(result.getStatus() + " ----  " + "Patient SSN:  " + result.getPatient().getSsn() + " ---- " + " Date : " + result.getDate() + "  ---- " + " Doctor name: " + result.getExaminer().getName() + "  ---- " + " Doctor SSN: " + result.getExaminer().getSsn());
            }
        }

        choiceBox.getItems().add("View Results");
        choiceBox.setValue("View Results");
    }
}
