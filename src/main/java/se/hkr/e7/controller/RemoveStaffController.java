package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import se.hkr.e7.model.*;

public class RemoveStaffController extends Controller {

    public TextField ssnField;
    public Label saveLabel;
    public Button removeButton;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/RemoveStaff.fxml");
    }

    public void Remove(ActionEvent actionEvent) {
        if (Person.isValidSsn(ssnField.getText())) {
            saveLabel.setText("");
        } else {
            saveLabel.setText("input in YYMMDDXXXX form");
        }

        Employee employee = DatabaseHandler.load(Employee.class, ssnField.getText());

        try {
            DatabaseHandler.delete(employee);
            showConfirmation("Deleted", "Done");
        } catch (Exception exception) {
            showError("Did not delete, make sure you have right ssn");

        }
    }
}
