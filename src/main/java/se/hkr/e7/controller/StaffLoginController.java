package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Singleton;

import java.io.IOException;

public class StaffLoginController extends Controller {
    public TextField ssnText;
    public TextField passwordText;
    public Label passwordCheck;
    public Label error1;
    public PasswordField passwordField;
    public CheckBox CheckBox;


    @FXML
    public void initialize() {
        passwordText.setManaged(false);
        passwordText.setVisible(true);
        passwordText.managedProperty().bind(CheckBox.selectedProperty());
        passwordText.visibleProperty().bind(CheckBox.selectedProperty());

        passwordField.managedProperty().bind(CheckBox.selectedProperty().not());
        passwordField.visibleProperty().bind(CheckBox.selectedProperty().not());
        passwordText.textProperty().bindBidirectional(passwordField.textProperty());

    }

    public void StaffLogin(ActionEvent actionEvent) {

        passwordCheck.setText(null);
        error1.setText(null);
        if (passwordText.getText().equals("") || ssnText.getText().equals("")) {
            showError("fields can not be empty ");
        } else {
            try {
                Employee employee = DatabaseHandler.load(Employee.class, ssnText.getText());
                Singleton.getInstance().setEmployee(employee);


                if (employee.getRole() == Employee.Role.ADMIN && employee.checkPassword(passwordText.getText())) {
                    loadScene("view/AdminDashboard.fxml",actionEvent);
                }

                if (employee.getSsn() != null && !employee.checkPassword(passwordText.getText())) {
                    showError("wrong password ");
                }

                if (employee.getRole() == Employee.Role.DOCTOR && employee.checkPassword(passwordText.getText())) {
                    loadScene("view/DoctorDashboard.fxml",actionEvent);
                }

                if (employee.getRole() == Employee.Role.ANALYSER && employee.checkPassword(passwordText.getText())) {
                    loadScene("view/AnalyserDashboard.fxml",actionEvent);
                }
            } catch (Exception exception) {
                showError("Could not login , please check your password and ssn");
            }
        }
    }

    public void Back(ActionEvent actionEvent) throws IOException {
       loadScene("view/Welcome.fxml",actionEvent);
    }

    public void Exit() {
        System.exit(0);
    }
}
