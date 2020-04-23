package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Patient;
import se.hkr.e7.Singleton;

import java.io.IOException;
import java.net.URL;

public class PatientLoginController {

    public TextField ssnText;
    public TextField passwordText;
    public Label error1;
    public Label passwordCheck;

    public void Back(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("Welcome.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Exit(ActionEvent actionEvent) {
        System.exit(0);
    }


    public void patientLogin(ActionEvent actionEvent) throws IOException {
        passwordCheck.setText(null);
        error1.setText(null);

        if (passwordText.getText().equals("") || ssnText.getText().equals("")) {
            error1.setText("fields can not be empty ");
        } else {
            try {
                Patient patient = DatabaseHandler.load(Patient.class, ssnText.getText());

                if (patient.getSsn() != null && patient.checkPassword(passwordText.getText())) {
                    Singleton.getInstance().setSsn(ssnText.getText());
                    Node node = (Node) actionEvent.getSource();
                    Scene currScene = node.getScene();
                    Stage stage = (Stage) currScene.getWindow();
                    URL resource = getClass().getClassLoader().getResource("PatientDashboard.fxml");
                    assert resource != null;
                    Parent root = FXMLLoader.load(resource);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }

                if (patient.getSsn() != null && !patient.checkPassword(passwordText.getText())) {
                    passwordCheck.setText("wrong password ");
                }
            } catch (Exception exception) {
                error1.setText("could not login , please check your password and ssn ");
            }
        }
    }
}