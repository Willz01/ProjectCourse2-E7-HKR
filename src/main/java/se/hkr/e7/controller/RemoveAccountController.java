package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Person;

import java.util.Objects;

public class RemoveAccountController extends Controller {

    public TextField ssnField;
    public Button removeButton;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/RemoveAccount.fxml");
        removeButton.setOnAction(this::removeAccount);
    }

    public void removeAccount(ActionEvent actionEvent) {
        try {
            if (!Person.isValidSsn(ssnField.getText())) {
                showError("Enter a valid SSN");
                return;
            }

            Employee employee = DatabaseHandler.load(Employee.class, ssnField.getText());
            Patient patient = DatabaseHandler.load(Patient.class, ssnField.getText());
            if (employee == null && patient == null) {
                showError("Entered SSN doesn't exist in the system.");
                return;
            }

            Employee currentUser = (Employee) Singleton.getInstance().getCurrentUser();
            if (employee != null && currentUser.getSsn().equals(employee.getSsn())) {
                showError("You can't delete your own account.");
                return;
            }

            try {
                DatabaseHandler.delete(Objects.requireNonNullElse(employee, patient));
                showConfirmation("Account Deleted", "Done");
            } catch (Exception exception) {
                if (employee != null) {
                    employee.clear();
                    DatabaseHandler.save(employee);
                }
                if (patient != null) {
                    patient.clear();
                    DatabaseHandler.save(patient);
                }
                showConfirmation("Account Deleted", "Done");
            }
        } catch (Exception exception) {
            showError("The account could not be deleted.");
        }
    }
}
