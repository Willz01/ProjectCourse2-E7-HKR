package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Result;

import java.io.IOException;
import java.util.List;


public class ViewResultsController extends Controller {

    public Button exitButton;
    public Button backButton;
    public ChoiceBox choiceBox;

    @FXML
    public void initialize() {
        exitButton.setOnAction(this::exit);
        backButton.setOnAction(actionEvent -> loadScene("view/AdminDashboard.fxml", actionEvent));

        List<Result> results = DatabaseHandler.loadAll(Result.class);

        for (Result result : results) {
            if (result.getStatus()== Result.Status.PENDING)
                choiceBox.getItems().add(result.getStatus()+" ----  "+"Patient SSN:  "+result.getPatient().getSsn()+" ---- "+" Date : "+result.getDate()+"  ---- "+" Doctor name: "+result.getExaminer().getName()+"  ---- "+" Doctor SSN: "+result.getExaminer().getSsn());
        }
        for (Result result : results) {
            if (result.getStatus()== Result.Status.POSITIVE)
                choiceBox.getItems().add(result.getStatus()+" ----  "+"Patient SSN:  "+result.getPatient().getSsn()+" ---- "+" Date : "+result.getDate()+"  ---- "+" Doctor name: "+result.getExaminer().getName()+"  ---- "+" Doctor SSN: "+result.getExaminer().getSsn());

        }
        for (Result result : results) {
            if (result.getStatus()== Result.Status.NEGATIVE)
                choiceBox.getItems().add(result.getStatus()+" ----  "+"Patient SSN:  "+result.getPatient().getSsn()+" ---- "+" Date : "+result.getDate()+"  ---- "+" Doctor name: "+result.getExaminer().getName()+"  ---- "+" Doctor SSN: "+result.getExaminer().getSsn());
        }
        choiceBox.getItems().add("View Results");

        choiceBox.setValue("View Results");

    }


}
