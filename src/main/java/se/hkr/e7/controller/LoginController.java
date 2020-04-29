package se.hkr.e7.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Singleton;

import java.util.Optional;
import java.util.stream.Stream;

public class LoginController extends Controller {

    public Button loginButton;
    public TextField ssnTextField;
    public TextField passwordTextField;
    public PasswordField passwordField;
    public CheckBox passwordCheckBox;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/StaffLogin.fxml");
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

    public void reset(MouseEvent mouseEvent) {

        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");
        dialog.setHeaderText("Look, a Custom Login Dialog");


// Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("SSN");
        PasswordField password = new PasswordField();
        password.setPromptText("Email");

        grid.add(new Label("SSN:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Email:"), 0, 1);
        grid.add(password, 1, 1);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
        });
        }


}
