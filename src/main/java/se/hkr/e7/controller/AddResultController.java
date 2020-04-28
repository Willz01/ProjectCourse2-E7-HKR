package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Result;
import se.hkr.e7.model.Singleton;

import java.time.LocalDate;

public class AddResultController extends Controller {

    public TextField ssnTextField;
    public CheckBox negativeCheckBox;
    public CheckBox positiveCheckBox;
    public DatePicker datePicker;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AddResult.fxml");
        while (negativeCheckBox.isSelected()) positiveCheckBox.setSelected(true);
        while (positiveCheckBox.isSelected()) negativeCheckBox.setSelected(true);
    }

    public void Save(ActionEvent event) {
        LocalDate today = LocalDate.now();
        LocalDate bdate = datePicker.getValue();
        String date = String.valueOf(bdate);
        if (bdate != null && bdate.isAfter(today)) {

            showError("you can not chose date after today ");
            System.out.println(date);

        } else if (negativeCheckBox.isSelected() && positiveCheckBox.isSelected() || !negativeCheckBox.isSelected() && !positiveCheckBox.isSelected()) {
            showError("please put valid test result");
        } else if (!ssnTextField.getText().matches("^([0-9]{2})([0-9]{2})([0-9]{2})([a-zA-Z0-9][0-9]{3})$")) {
            showError("ssn must be valid 10 digits as YYMMDDXXXX");
        } else {

            try {

                Patient patient = DatabaseHandler.load(Patient.class, ssnTextField.getText());
                Singleton.getInstance().getEmployee();
                if (negativeCheckBox.isSelected()) {
                    Result Result = new Result(patient, Singleton.getInstance().getEmployee(), date, se.hkr.e7.model.Result.Status.NEGATIVE);
                    showConfirmation("Saved", "thank you ");

                }
                if (positiveCheckBox.isSelected()) {
                    Result Result = new Result(patient, Singleton.getInstance().getEmployee(), date, se.hkr.e7.model.Result.Status.POSITIVE);
                    showConfirmation("Saved", "thank you ");
                }

            } catch (Exception e) {
                showError("can not find the patient");
            }

        }


    }
}