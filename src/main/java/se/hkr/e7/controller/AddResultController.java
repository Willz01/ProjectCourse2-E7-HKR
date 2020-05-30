package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Mail;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Person;
import se.hkr.e7.model.Result;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddResultController extends Controller {

    public TextField ssnTextField;
    public DatePicker datePicker;
    public Button saveButton;
    public ToggleGroup resultToggleGroup;
    public TextArea resultNote;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AddResult.fxml");
        saveButton.setOnAction(this::addResult);
        datePicker.setValue(LocalDate.now());
        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) > 0);
            }
        });
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
                    loadScene("view/AddPatient.fxml");
                }
                return;
            }
            LocalDateTime localDateTime;
            if (date.equals(LocalDate.now())) {
                localDateTime = LocalDateTime.now();
            } else {
                localDateTime = date.atStartOfDay();
            }

            Result result = new Result(patient, currentUser, localDateTime, status);
            result.setNote(resultNote.getText());
            DatabaseHandler.save(result);
            if (result.getStatus() != Result.Status.PENDING) {
                try {
                    String body = String.format("Hello %s .<br>Your results are now available. <br>Best Regards.", patient.getName());
                    Mail.send("Result available", body, patient);
                    showConfirmation("Saved", "Email has been sent to the patient. \nThank you.");

                } catch (UnsupportedEncodingException | MessagingException e) {
                    showConfirmation("Result added", "Result was added but email notification couldn't be sent.");
                }

            } else {
                showConfirmation("Saved", "Result has been added");
            }
            ssnTextField.setText("");
            datePicker.setValue(LocalDate.now());
            resultRadioButton.setSelected(false);
            resultNote.setText("");
        } catch (Exception e) {
            showError("Something went wrong");
        }
    }
}