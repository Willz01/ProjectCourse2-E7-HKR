package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Singleton;

import java.util.stream.Stream;

public class StaffLoginController extends Controller {

    public Button exitButton;
    public Button backButton;
    public Button loginButton;
    public TextField ssnTextField;
    public TextField passwordTextField;
    public Label passwordCheckLabel;
    public Label errorLabel;
    public PasswordField passwordField;
    public CheckBox checkBox;

    @FXML
    public void initialize() {
        exitButton.setOnAction(this::exit);
        backButton.setOnAction(actionEvent -> loadScene("view/Welcome.fxml", actionEvent));
        loginButton.setOnAction(this::login);
        Stream.of(ssnTextField, passwordField, passwordTextField).forEach(e -> e.setOnKeyPressed(this::onEnter));

        passwordTextField.setManaged(false);
        passwordTextField.setVisible(true);
        passwordTextField.managedProperty().bind(checkBox.selectedProperty());
        passwordTextField.visibleProperty().bind(checkBox.selectedProperty());

        passwordField.managedProperty().bind(checkBox.selectedProperty().not());
        passwordField.visibleProperty().bind(checkBox.selectedProperty().not());
        passwordTextField.textProperty().bindBidirectional(passwordField.textProperty());
    }

    private void login(ActionEvent actionEvent) {
        login((Node) actionEvent.getSource());
    }

    private void login(Node node) {
        passwordCheckLabel.setText(null);
        errorLabel.setText(null);
        if (passwordTextField.getText().equals("") || ssnTextField.getText().equals("")) {
            showError("fields can not be empty ");
        } else {
            try {
                Employee employee = DatabaseHandler.load(Employee.class, ssnTextField.getText());
                Singleton.getInstance().setEmployee(employee);


                if (employee.getRole() == Employee.Role.ADMIN && employee.checkPassword(passwordTextField.getText())) {
                    loadScene("view/AdminDashboard.fxml", node);
                }

                if (employee.getSsn() != null && !employee.checkPassword(passwordTextField.getText())) {
                    showError("wrong password ");
                }

                if (employee.getRole() == Employee.Role.DOCTOR && employee.checkPassword(passwordTextField.getText())) {
                    loadScene("view/DoctorDashboard.fxml", node);
                }

                if (employee.getRole() == Employee.Role.ANALYSER && employee.checkPassword(passwordTextField.getText())) {
                    loadScene("view/AnalyserDashboard.fxml", node);
                }
            } catch (Exception exception) {
                showError("Could not login , please check your password and ssn");
            }
        }
    }

    private void onEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            login((Node) keyEvent.getSource());
        }
    }
}
