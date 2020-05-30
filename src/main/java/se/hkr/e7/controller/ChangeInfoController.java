package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Person;

public class ChangeInfoController extends Controller {

    public Button saveButton;
    public TextField nameTextField;
    public TextField emailTextField;
    public TextField phoneTextField;
    public TextField addressTextField;
    public PasswordField passwordTextField;
    public Label nameLabel;
    public Label emailLabel;
    public Label phoneLabel;
    public Label addressLabel;
    public Label passwordLabel;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/ChangeInfo.fxml");
        saveButton.setOnAction(this::save);

        Person currentUser = Singleton.getInstance().getCurrentUser();
        nameTextField.setText(currentUser.getName());
        emailTextField.setText(currentUser.getEmail());
        phoneTextField.setText(currentUser.getPhone());
        addressTextField.setText(currentUser.getAddress());
    }

    public void save(ActionEvent event) {
        nameLabel.setText(null);
        passwordLabel.setText(null);
        emailLabel.setText(null);
        addressLabel.setText(null);
        phoneLabel.setText(null);
        boolean error = false;

        if (nameTextField.getText().isEmpty()) {
            nameLabel.setText("Can't be empty");
            error = true;
        }

        if (phoneTextField.getText().isEmpty()) {
            phoneLabel.setText("Can't be empty ");
            error = true;
        }

        if (addressTextField.getText().isEmpty()) {
            addressLabel.setText("Can't be empty");
            error = true;
        }

        if (emailTextField.getText().isEmpty()) {
            emailLabel.setText("Cant be empty");
            error = true;
        }

        if (!Employee.isValidEmail(emailTextField.getText())) {
            emailLabel.setText("Email is not valid");
            error = true;
        }

        if (!Employee.isValidPhone(phoneTextField.getText())) {
            phoneLabel.setText("Phone is not valid");
            error = true;
        }

        if (!error) {
            try {
                Person person = Singleton.getInstance().getCurrentUser();

                if (!passwordTextField.getText().isBlank()) {
                    person.updatePassword(passwordTextField.getText());
                }

                person.setName(nameTextField.getText());
                person.setEmail(emailTextField.getText());
                person.setPhone(phoneTextField.getText());
                person.setAddress(addressTextField.getText());

                DatabaseHandler.save(person);

                showConfirmation("Complete", "Update information successfully.");
            } catch (IllegalArgumentException e) {
                showError(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                showError("Something went wrong.");
            }
        } else {
            showError("Please enter valid information");
        }
    }
}