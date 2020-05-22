package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.hibernate.HibernateException;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Location;
import se.hkr.e7.model.Person;

public class AddEmployeeController extends Controller {

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

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AddEmployee.fxml");
        locationChoiceBox.getItems().setAll(Location.values());
        roleChoiceBox.getItems().setAll(Employee.Role.values());

        addButton.setOnAction(this::addAccount);
    }

    private void addAccount(ActionEvent actionEvent) {

        if (!Person.isValidSsn(ssnTextField.getText())) {
            showError("SSN must be valid 10 digits as YYMMDDXXXX.");
            return;
        }

        if (!Person.isValidEmail(emailTextField.getText())) {
            showError("Enter a valid email address.");
            return;
        }

        if (nameTextField.getText().isEmpty()) {
            showError("Name field can't be empty.");
            return;
        }

        if (addressTextField.getText().isEmpty()) {
            showError("Address field can't be empty.");
            return;
        }

        if (!Person.isValidPassword(passwordTextField.getText())) {
            showError("Enter a valid password. The password should be at least 8 characters in length and have an " +
                    "uppercase and a lowercase letter as well as a number.");
            return;
        }

        if (!Person.isValidPhone(phoneTextField.getText())) {
            showError("Enter a valid phone number.");
            return;
        }

        if (!Person.isValidSalary(salaryTextField.getText())) {
            showError("Invalid salary format");
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

        try {
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
            showConfirmation("Finished successfully!", "The account has been created.");
            ssnTextField.setText("");
            passwordTextField.setText("");
            nameTextField.setText("");
            emailTextField.setText("");
            salaryTextField.setText("");
            phoneTextField.setText("");
            addressTextField.setText("");
            locationChoiceBox.setValue(null);
            roleChoiceBox.setValue(null);
        } catch (IllegalArgumentException e) {
            showError(e.getMessage());
        }catch (HibernateException e){
            showError("Account already exists");
        }
    }
}
