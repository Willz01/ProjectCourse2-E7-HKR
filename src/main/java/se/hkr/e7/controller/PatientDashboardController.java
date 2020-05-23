package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Result;

public class PatientDashboardController extends Controller {

    public TextArea resultTextArea;
    public Button edit;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/PatientDashboard.fxml");
        edit.setOnAction(actionEvent -> loadScene("view/ChangeInfo.fxml"));
        try {
            Patient currentUser = (Patient) Singleton.getInstance().getCurrentUser();
            StringBuilder stringBuilder = new StringBuilder();
            for (Result testResult : currentUser.getTestResults()) {
                stringBuilder.append(testResult.getDateTime())
                        .append(", The examiner: ")
                        .append(testResult.getExaminer().getName())
                        .append(", || Status: ")
                        .append(testResult.getStatus())
                        .append(testResult.getNote() != null ? " ," + testResult.getNote() : "")
                        .append(System.lineSeparator());
            }
            resultTextArea.setText(stringBuilder.toString());
        } catch (Exception exception) {
            resultTextArea.setText("Couldn't find result, Please try again.");
        }
    }
}
