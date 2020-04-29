package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Singleton;

import java.util.Objects;

public class RemoveAccountController extends Controller {

    public TextField ssnField;
    public Label saveLabel;
    public Button removeButton;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/RemoveAccount.fxml");
        removeButton.setOnAction(this::remove);
    }

    public void remove(ActionEvent actionEvent) {
        try {
            if (!ssnField.getText().matches("^([0-9]{2})([0-9]{2})([0-9]{2})([a-zA-Z0-9][0-9]{3})$")) {
                showError("Please input a valid SSN.");
                return;
            }

            Employee employee = DatabaseHandler.load(Employee.class, ssnField.getText());
            Patient patient = DatabaseHandler.load(Patient.class, ssnField.getText());

            if (employee == null && patient == null) {
                showError("No one with that SSN exists.");
                return;
            }

            DatabaseHandler.delete(Objects.requireNonNullElse(employee, patient));
            showConfirmation("Deleted", "Done");
        } catch (Exception exception) {
            showError("The account could not be deleted.");
        }
    }
}
