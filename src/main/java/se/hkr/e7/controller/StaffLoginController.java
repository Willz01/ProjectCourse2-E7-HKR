package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import se.hkr.e7.Employee;
import se.hkr.e7.Role;

import java.io.IOException;
import java.net.URL;

public class StaffLoginController {
    public TextField ssnText;
    public TextField passwordText;
    public Label passwordCheck;
    public Label error1;


    public void StaffLogin(ActionEvent actionEvent) throws IOException {

        passwordCheck.setText(null);error1.setText(null);

        if (passwordText.getText().equals("") || ssnText.getText().equals("")) {
            error1.setText("fields can not be empty ");

        } else {
            try {
                Employee employee = Employee.load(ssnText.getText(), Employee.class);


                if (employee.getRole() == Role.ADMIN && employee.checkPassword(passwordText.getText())) {

                    Node node = (Node) actionEvent.getSource();
                    Scene currScene = node.getScene();
                    Stage stage = (Stage) currScene.getWindow();
                    URL resource = getClass().getClassLoader().getResource("AdminDashboard.fxml");
                    Parent root = FXMLLoader.load(resource);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                if (employee.getSsn() != null && !employee.checkPassword(passwordText.getText())) {
                    passwordCheck.setText("wrong password ");
                }


                if (employee.getRole() == Role.DOCTOR && employee.checkPassword(passwordText.getText())) {

                    Node node = (Node) actionEvent.getSource();
                    Scene currScene = node.getScene();
                    Stage stage = (Stage) currScene.getWindow();
                    URL resource = getClass().getClassLoader().getResource("DoctorDashboard.fxml");
                    Parent root = FXMLLoader.load(resource);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                if (employee.getRole() == Role.ANALYSER && employee.checkPassword(passwordText.getText())) {

                    Node node = (Node) actionEvent.getSource();
                    Scene currScene = node.getScene();
                    Stage stage = (Stage) currScene.getWindow();
                    URL resource = getClass().getClassLoader().getResource("AnalyserDashboard.fxml");
                    Parent root = FXMLLoader.load(resource);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }

            } catch (Exception exception) {
                error1.setText("could not login , please check your password and ssn ");

            }
        }
    }


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


}
