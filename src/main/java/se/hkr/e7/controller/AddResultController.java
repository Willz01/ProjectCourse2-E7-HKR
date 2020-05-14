package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Person;
import se.hkr.e7.model.Result;

import java.time.LocalDate;

public class AddResultController extends Controller {

    public TextField ssnTextField;
    public CheckBox negativeCheckBox;
    public CheckBox positiveCheckBox;
    public DatePicker datePicker;
    public CheckBox pendingCheckBox;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AddResult.fxml");
    }

    public void Save(ActionEvent event) {
        LocalDate today = LocalDate.now();
        LocalDate bdate = datePicker.getValue();
        String date = String.valueOf(bdate);
        if (bdate != null && bdate.isAfter(today)) {
            showError("You can not chose a date after today");
        } else if (!negativeCheckBox.isSelected() && !positiveCheckBox.isSelected() && !pendingCheckBox.isSelected()) {
            showError("Please enter a valid test result");
        } else if (!Person.isValidSsn(ssnTextField.getText())) {
            showError("SSN must be valid 10 digits as YYMMDDXXXX");
        } else {

            try {

                Patient patient = DatabaseHandler.load(Patient.class, ssnTextField.getText());
                if (negativeCheckBox.isSelected()) {
                    Result Result = new Result(patient, Singleton.getInstance().getEmployee(), date, se.hkr.e7.model.Result.Status.NEGATIVE);
                    showConfirmation("Saved", "Thank you ");
                }

                if (positiveCheckBox.isSelected()) {
                    Result Result = new Result(patient, Singleton.getInstance().getEmployee(), date, se.hkr.e7.model.Result.Status.POSITIVE);
                    showConfirmation("Saved", "Thank you ");
                }

                if (pendingCheckBox.isSelected()) {
                    Result Result = new Result(patient, Singleton.getInstance().getEmployee(), date, se.hkr.e7.model.Result.Status.PENDING);
                    showConfirmation("Saved", "Thank you ");
                }
            } catch (Exception e) {
                if (showChoice("Couldn't find patient", "Do you want to add new patient")) {
                    loadScene("view/AddPatientDoctor.fxml", event);
                }
            }

        }
    }

    public void negative(ActionEvent event) {
        positiveCheckBox.setSelected(false);
        pendingCheckBox.setSelected(false);
    }

    public void positive(ActionEvent event) {
        negativeCheckBox.setSelected(false);
        pendingCheckBox.setSelected(false);
    }

    public void pending(ActionEvent event) {
        positiveCheckBox.setSelected(false);
        negativeCheckBox.setSelected(false);
    }
}