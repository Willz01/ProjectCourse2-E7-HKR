package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Singleton;

public class LoginController extends Controller {

    public Button exitButton;
    public Button backButton;
    public Button loginButton;
    public TextField ssnTextField;
    public TextField passwordTextField;
    public PasswordField passwordField;
    public CheckBox passwordCheckBox;

    @FXML
    public void initialize() {
        exitButton.setOnAction(this::exit);
        backButton.setOnAction(actionEvent -> loadScene("view/Welcome.fxml", actionEvent));
        loginButton.setOnAction(this::login);

        passwordTextField.setManaged(false);
        passwordTextField.setVisible(true);
        passwordTextField.managedProperty().bind(passwordCheckBox.selectedProperty());
        passwordTextField.visibleProperty().bind(passwordCheckBox.selectedProperty());
        passwordField.managedProperty().bind(passwordCheckBox.selectedProperty().not());
        passwordField.visibleProperty().bind(passwordCheckBox.selectedProperty().not());
        passwordTextField.textProperty().bindBidirectional(passwordField.textProperty());
    }

    private void login(ActionEvent actionEvent) {
        Employee employee = DatabaseHandler.load(Employee.class, ssnTextField.getText());
        Patient patient = DatabaseHandler.load(Patient.class, ssnTextField.getText());

        if (employee != null && employee.checkPassword(passwordTextField.getText())) {
            Singleton.getInstance().setEmployee(employee);
            switch (employee.getRole()) {
                case ADMIN:
                    loadScene("view/AdminDashboard.fxml", actionEvent);
                    break;
                case ANALYSER:
                    loadScene("view/AnalyserDashboard.fxml", actionEvent);
                    break;
                case DOCTOR:
                    loadScene("view/DoctorDashboard.fxml", actionEvent);
                    break;
            }
        } else if (patient != null && patient.checkPassword(passwordTextField.getText())) {
            Singleton.getInstance().setCurrentUser(patient);
            loadScene("view/PatientDashboard.fxml", actionEvent);
        } else {
            showError("Login unsuccessful", "Please check your username and password.");
        }
    }
}
