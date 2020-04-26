package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Result;
import se.hkr.e7.model.Singleton;

public class PatientDashboardController extends Controller {

    public Button exitButton;
    public Button backButton;
    public TextArea resultTextArea;

    @FXML
    public void initialize() {
        exitButton.setOnAction(this::exit);
        backButton.setOnAction(actionEvent -> loadScene("view/PatientLogin.fxml", actionEvent));

        try {
            Patient patient = DatabaseHandler.load(Patient.class, Singleton.getInstance().getSsn());
            StringBuilder stringBuilder = new StringBuilder();
            for (Result testResult : patient.getTestResults()) {
                stringBuilder.append(testResult.getDate())
                        .append(", The examiner: ")
                        .append(testResult.getExaminer().getName())
                        .append(", || Status: ")
                        .append(testResult.getStatus())
                        .append(testResult.getNote() != null ? " ," + testResult.getNote() : "")
                        .append(System.lineSeparator());
            }
            resultTextArea.setText(stringBuilder.toString());
        } catch (Exception exception) {
            resultTextArea.setText("Could not find result please press back and try again.");
        }
    }
}
