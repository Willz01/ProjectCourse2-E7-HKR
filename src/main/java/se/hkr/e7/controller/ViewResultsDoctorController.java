package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Result;

import java.util.List;


public class ViewResultsDoctorController extends Controller {

    public Button exitButton;
    public Button backButton;
    public TextArea textarea;

    @FXML
    public void initialize() {
        exitButton.setOnAction(this::exit);
        backButton.setOnAction(actionEvent -> loadScene("view/DoctorDashboard.fxml", actionEvent));
        StringBuilder stringBuilder = new StringBuilder();
        try {


            List<Result> results = DatabaseHandler.loadAll(Result.class);

            int counter = 0;
            for (Result result : results) {
                if (result.getStatus() == Result.Status.PENDING) {
                    counter++;

                    stringBuilder.append(counter).append(" - ")
                            .append("Status  : ")
                            .append(result.getStatus())
                            .append("     || Patient SSN : ")
                            .append(result.getPatient().getSsn())
                            .append("     || Result Date : ")
                            .append(result.getDate())
                            .append("     || Doctor Name : ")
                            .append(result.getExaminer().getName())


                            .append(result.getNote() != null ? "     || Note : " + result.getNote() : "")
                            .append(System.lineSeparator());

                }
            }
            for (Result result : results) {

                if (result.getStatus() == Result.Status.POSITIVE) {
                    counter++;
                    stringBuilder.append(counter).append(" - ")
                            .append("Status  : ")
                            .append(result.getStatus())
                            .append("     || Patient SSN : ")
                            .append(result.getPatient().getSsn())
                            .append("     || Result Date : ")
                            .append(result.getDate())
                            .append("     || Doctor Name : ")
                            .append(result.getExaminer().getName())


                            .append(result.getNote() != null ? "     || Note : " + result.getNote() : "")
                            .append(System.lineSeparator());
                }
            }

            for (Result result : results) {
                if (result.getStatus() == Result.Status.NEGATIVE) {
                    counter++;
                    stringBuilder.append(counter).append(" - ")
                            .append("Status  : ")
                            .append(result.getStatus())
                            .append("     || Patient SSN : ")
                            .append(result.getPatient().getSsn())
                            .append("     || Result Date : ")
                            .append(result.getDate())
                            .append("     || Doctor Name : ")
                            .append(result.getExaminer().getName())


                            .append(result.getNote() != null ? "     || Note : " + result.getNote() : "")
                            .append(System.lineSeparator());
                }
            }

            textarea.setText(String.valueOf(stringBuilder));

        } catch (Exception exception) {
            showError("Something went wrong");

        }
    }

}