package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Location;
import se.hkr.e7.model.Person;

public class AddAccountController extends Controller {

    public ChoiceBox<Location> locationChoiceBox;
    public ChoiceBox<Employee.Role> roleChoiceBox;
    public TextField ssnTextField;
    public TextField nameTextField;
    public TextField addressTextField;
    public TextField passwordTextField;
    public TextField emailTextField;
    public TextField phoneTextField;
    public TextField salaryTextField;
    public Button addButton;
    public Button backButton;
    public Button exitButton;

    @FXML
    public void initialize() {
        locationChoiceBox.getItems().setAll(Location.values());
        roleChoiceBox.getItems().setAll(Employee.Role.values());

        addButton.setOnAction(this::addAccount);
        backButton.setOnAction(actionEvent -> loadScene("view/AdminDashboard.fxml", actionEvent));
        exitButton.setOnAction(this::exit);
    }

    private void addAccount(ActionEvent actionEvent) {

        if (!Person.isValidSsn(ssnTextField.getText())) {
            showError("SSN must be valid 10 digits as YYMMDDXXXX.");
            return;
        }

        if (!Person.isValidEmail(emailTextField.getText())) {
            showError("Please enter valid email address.");
            return;
        }

        if (nameTextField.getText().isEmpty()) {
            showError("Name can't be empty.");
            return;
        }

        if (addressTextField.getText().isEmpty()) {
            showError("Address can't be empty.");
            return;
        }

        if (passwordTextField.getText().length() < 8) {
            showError("Please enter a password at least eight characters in length.");
            return;
        }

        if (!Person.isValidPhone(phoneTextField.getText())) {
            showError("Please enter valid phone number.");
            return;
        }

        if (!Person.isValidSalary(salaryTextField.getText())) {
            showError("Salary must be number.");
            return;
        }

        if (locationChoiceBox.getValue() == null) {
            showError("Please select Location.");
            return;
        }

        if (roleChoiceBox.getValue() == null) {
            showError("Please select a role.");
            return;
        }

        DatabaseHandler.save(new Employee(
                ssnTextField.getText(),
                passwordTextField.getText(),
                nameTextField.getText(),
                emailTextField.getText(),
                phoneTextField.getText(),
                addressTextField.getText(),
                locationChoiceBox.getValue(),
                roleChoiceBox.getValue(),
                Double.parseDouble(salaryTextField.getText())
        ));
        showConfirmation("Finished successfully!", "The account was created.");
    }
}
