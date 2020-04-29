package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Location;
import se.hkr.e7.model.Singleton;

import javax.xml.crypto.Data;

public class RemoveStaffController extends Controller {

    public TextField ssnField;
    public Label saveLabel;
    public Button removeButton;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/RemoveStaff.fxml");
    }

    public void Remove(ActionEvent actionEvent) {
        if (ssnField.getText().matches("^([0-9]{2})([0-9]{2})([0-9]{2})([a-zA-Z0-9][0-9]{3})$")) {
            saveLabel.setText("");
        }
        if (!ssnField.getText().matches("^([0-9]{2})([0-9]{2})([0-9]{2})([a-zA-Z0-9][0-9]{3})$")) {
            saveLabel.setText("input in YYMMDDXXXX form");
        }
        Employee employee = DatabaseHandler.load(Employee.class, ssnField.getText());

        try {
            DatabaseHandler.delete(employee);
        } catch (Exception exception) {
            employee.clear();
            DatabaseHandler.save(employee);
        } finally {
            showConfirmation("Deleted", "Done");
        }
    }
}
