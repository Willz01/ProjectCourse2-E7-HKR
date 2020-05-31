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
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Mail;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Person;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.stream.Stream;

public class LoginController extends Controller {

    public Button loginButton;
    public TextField ssnTextField;
    public TextField passwordTextField;
    public PasswordField passwordField;
    public CheckBox passwordCheckBox;
    public Label passwordResetLabel;

    @FXML
    public void initialize() {
        Singleton.getInstance().clear();
        Singleton.getInstance().addSceneHistory("view/Login.fxml");
        passwordResetLabel.setOnMouseEntered(mouseEvent -> passwordResetLabel.setUnderline(true));
        passwordResetLabel.setOnMouseExited(mouseEvent -> passwordResetLabel.setUnderline(false));
        loginButton.setOnAction(this::login);
        Stream.of(ssnTextField, passwordField, passwordTextField).forEach(e -> e.setOnKeyPressed(this::onEnter));
        passwordResetLabel.setOnMouseClicked(this::resetPassword);

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

        if (employee != null && employee.isEnabled() && employee.checkPassword(passwordTextField.getText())) {
            Singleton.getInstance().setCurrentUser(employee);
            switch (employee.getRole()) {
                case ADMIN:
                    loadScene("view/AdminDashboard.fxml");
                    break;
                case ANALYSER:
                    loadScene("view/AnalyserDashboard.fxml");
                    break;
                case DOCTOR:
                    loadScene("view/DoctorDashboard.fxml");
                    break;
            }
        } else if (patient != null && patient.isEnabled() && patient.checkPassword(passwordTextField.getText())) {
            Singleton.getInstance().setCurrentUser(patient);
            loadScene("view/PatientDashboard.fxml");
        } else {
            showError("Login unsuccessful", "Please check username and password.");
        }
    }

    private void onEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            login((Node) keyEvent.getSource());
        }
    }

    public void resetPassword(MouseEvent mouseEvent) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Password Reset");
        dialog.setHeaderText("");
        ButtonType sendButtonType = new ButtonType("Send", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(sendButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField ssnTextField = new TextField();
        ssnTextField.setPromptText("SSN");
        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Email");

        grid.add(new Label("SSN:"), 0, 0);
        grid.add(ssnTextField, 1, 0);
        grid.add(new Label("Email:"), 0, 1);
        grid.add(emailTextField, 1, 1);

        Node sendButton = dialog.getDialogPane().lookupButton(sendButtonType);
        sendButton.setDisable(true);
        ssnTextField.textProperty().addListener((observable, oldValue, newValue) ->
                sendButton.setDisable(newValue.trim().isEmpty()));

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(ssnTextField::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton != sendButtonType) {
                return null;
            }

            try {
                Person person = Person.load(Person.class, ssnTextField.getText());

                if (person == null) {
                    showError("Wrong SSN");
                    return null;
                }

                if (!person.getEmail().equals(emailTextField.getText())) {
                    showError("Wrong email address");
                } else {
                    String password = Mail.generatePassword(10);
                    person.updatePassword(password);
                    DatabaseHandler.save(person);
                    Mail.send("Password Reset",
                            String.format("Dear %s,<br>Your new password is %s <br>Kind regards", person.getName(), password),
                            person);
                    showConfirmation("Success", "Email has been sent.");
                }
            } catch (IllegalArgumentException e) {
                showError(e.getMessage());
            } catch (UnsupportedEncodingException | MessagingException e) {
                showError("Email could not be sent.");
            }

            return new Pair<>(ssnTextField.getText(), emailTextField.getText());
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
        });
    }

}
