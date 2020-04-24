package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Patient;
import se.hkr.e7.Result;
import se.hkr.e7.Singleton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientDashboardController implements Initializable {

    public TextArea resultText;
    public Button Back;
    public Button Exit;

    public void Back(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("view/patientLogin.fxml");
        assert resource != null;
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

