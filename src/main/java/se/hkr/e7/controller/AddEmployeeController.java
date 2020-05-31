package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.hibernate.HibernateException;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Mail;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Location;
import se.hkr.e7.model.Person;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public class AddEmployeeController extends Controller {

    public ChoiceBox<Location> locationChoiceBox;
    public ChoiceBox<Employee.Role> roleChoiceBox;
    public TextField ssnTextField;
    public TextField nameTextField;
    public TextField addressTextField;

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
            Employee employee = Employee.load(ssnTextField.getText());

            if (employee != null && employee.isEnabled()) {
                showError("The account already exists.");
                return;
            }

            String password = Mail.generatePassword(10);

            if (employee == null) {
                employee = new Employee(
                        ssnTextField.getText(),
                        password,
                        nameTextField.getText(),
                        emailTextField.getText(),
                        phoneTextField.getText(),
                        addressTextField.getText(),
                        locationChoiceBox.getValue(),
                        roleChoiceBox.getValue(),
                        Double.parseDouble(salaryTextField.getText())
                );
                DatabaseHandler.save(employee);
            } else {
                employee.setName(nameTextField.getText());
                employee.updatePassword(password);
                employee.setEmail(emailTextField.getText());
                employee.setPhone(phoneTextField.getText());
                employee.setAddress(addressTextField.getText());
                employee.setRole(roleChoiceBox.getValue());
                employee.setLocation(locationChoiceBox.getValue());
                employee.setSalary(Double.parseDouble(salaryTextField.getText()));
                employee.setEnabled(true);
                DatabaseHandler.save(employee);
            }

            try {

                Mail.send("New account information",
                        String.format("Dear %s,<br>Your account has been created and your password is: <br> %s <br> Best regards.",
                                employee.getName(), password), employee);
                showConfirmation("Success", "The employee was added, password was sent to the employee email.");
            } catch (UnsupportedEncodingException | MessagingException e) {
                showError("Employee was added, Email could not be sent and the password is: " + password);
            }

            ssnTextField.setText("");

            nameTextField.setText("");
            emailTextField.setText("");
            salaryTextField.setText("");
            phoneTextField.setText("");
            addressTextField.setText("");
            locationChoiceBox.setValue(null);
            roleChoiceBox.setValue(null);
        } catch (IllegalArgumentException e) {
            showError(e.getMessage());
        } catch (HibernateException e) {
            showError("There was an error saving the account.");
        }
    }
}
