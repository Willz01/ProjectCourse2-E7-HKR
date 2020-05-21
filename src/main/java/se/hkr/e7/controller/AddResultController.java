package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Person;
import se.hkr.e7.model.Result;

import java.time.LocalDate;

public class AddResultController extends Controller {

    public TextField ssnTextField;
    public DatePicker datePicker;
    public Button saveButton;
    public ToggleGroup resultToggleGroup;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AddResult.fxml");
        saveButton.setOnAction(this::addResult);
    }

    public void addResult(ActionEvent event) {
        LocalDate date = datePicker.getValue();
        RadioButton resultRadioButton = (RadioButton) resultToggleGroup.getSelectedToggle();
        Result.Status status;

        if (!Person.isValidSsn(ssnTextField.getText())) {
            showError("Please enter a valid SSN");
            return;
        }

        if (date == null) {
            showError("Please choose a valid date");
            return;
        }

        if (date.isAfter(LocalDate.now())) {
            showError("You cannot chose a date after today");
            return;
        }

        if (!Person.isValidSsn(ssnTextField.getText())) {
            showError("SSN must be valid 10 digits as YYMMDDXXXX");
            return;
        }

        if (resultRadioButton == null) {
            showError("Please choose a test result status");
            return;
        }

        switch (resultRadioButton.getText()) {
            case "Positive":
                status = Result.Status.POSITIVE;
                break;
            case "Negative":
                status = Result.Status.NEGATIVE;
                break;
            default:
                status = Result.Status.PENDING;
                break;
        }

        try {
            Employee currentUser = (Employee) Singleton.getInstance().getCurrentUser();
            Patient patient = DatabaseHandler.load(Patient.class, ssnTextField.getText());

            if (patient == null) {
                if (showChoice("Couldn't find patient", "Do you want to add new patient")) {
                    loadScene("view/AddPatientDoctor.fxml", event);
                } else {
                    return;
                }
            }

            Result result = new Result(patient, currentUser, date.atStartOfDay(), status);
            DatabaseHandler.save(result);
            showConfirmation("Saved", "Thank you");
            ssnTextField.setText("");
            datePicker.setValue(null);
            resultRadioButton.setSelected(false);
        } catch (Exception e) {
            showError("Something went wrong");
        }
    }
}