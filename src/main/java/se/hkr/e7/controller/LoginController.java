package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Singleton;

import java.util.stream.Stream;

public class LoginController extends Controller {

    public Button loginButton;
    public TextField ssnTextField;
    public TextField passwordTextField;
    public PasswordField passwordField;
    public CheckBox passwordCheckBox;
    public Button backButton;
    public Button exitButton;

    @FXML
    public void initialize() {
        exitButton.setOnAction(this::exit);
        backButton.setOnAction(actionEvent -> loadScene("view/Welcome.fxml", actionEvent));
        loginButton.setOnAction(this::login);
        Stream.of(ssnTextField, passwordField, passwordTextField).forEach(e -> e.setOnKeyPressed(this::onEnter));

        passwordTextField.setManaged(false);
        passwordTextField.setVisible(true);
        passwordTextField.managedProperty().bind(passwordCheckBox.selectedProperty());
        passwordTextField.visibleProperty().bind(passwordCheckBox.selectedProperty());
        passwordField.managedProperty().bind(passwordCheckBox.selectedProperty().not());
        passwordField.visibleProperty().bind(passwordCheckBox.selectedProperty().not());
        passwordTextField.textProperty().bindBidirectional(passwordField.textProperty());
    }

    private void login(ActionEvent actionEvent) {
        login((Node) actionEvent.getSource());
    }

    private void login(Node node) {
        Employee employee = DatabaseHandler.load(Employee.class, ssnTextField.getText());
        Patient patient = DatabaseHandler.load(Patient.class, ssnTextField.getText());

        if (employee != null && employee.checkPassword(passwordTextField.getText())) {
            Singleton.getInstance().setEmployee(employee);
            switch (employee.getRole()) {
                case ADMIN:
                    loadScene("view/AdminDashboard.fxml", node);
                    break;
                case ANALYSER:
                    loadScene("view/AnalyserDashboard.fxml", node);
                    break;
                case DOCTOR:
                    loadScene("view/DoctorDashboard.fxml", node);
                    break;
            }
        } else if (patient != null && patient.checkPassword(passwordTextField.getText())) {
            Singleton.getInstance().setCurrentUser(patient);
            loadScene("view/PatientDashboard.fxml", node);
        } else {
            showError("Login unsuccessful", "Please check your username and password.");
        }
    }

    private void onEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            login((Node) keyEvent.getSource());
        }
    }
}
