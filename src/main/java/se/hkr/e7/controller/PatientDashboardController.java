package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Result;
import se.hkr.e7.model.Singleton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientDashboardController extends Controller implements Initializable  {

    public TextArea resultText;
    public Button Back;
    public Button Exit;

    public void Back(ActionEvent actionEvent) throws IOException {
       loadScene("view/patientLogin.fxml",actionEvent);
    }

    public void Exit() {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
            resultText.setText(stringBuilder.toString());
        } catch (Exception exception) {
            resultText.setText("could not find result please press back and try again ");
        }
    }
}

