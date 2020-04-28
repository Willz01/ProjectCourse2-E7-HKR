package se.hkr.e7.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Result;
import se.hkr.e7.model.Singleton;
import java.time.LocalDate;
public class AddResultController extends Controller {
    public Button exitButton;
    public Button backButton;
    public TextField ssnTextField;
    public CheckBox negativeCheckBox;
    public CheckBox positiveCheckBox;
    public DatePicker datePicker;
    public CheckBox pending;
    @FXML
    public void initialize() {
        exitButton.setOnAction(this::exit);
        backButton.setOnAction(actionEvent -> loadScene("view/DoctorDashboard.fxml", actionEvent));
    }
    public void Save(ActionEvent event) {
        LocalDate today = LocalDate.now();
        LocalDate bdate = datePicker.getValue();
        String date = String.valueOf(bdate);
        if (bdate != null && bdate.isAfter(today)) {
            showError("you can not chose date after today ");
            System.out.println(date);
        } else if (!negativeCheckBox.isSelected() && !positiveCheckBox.isSelected() && !pending.isSelected()) {
            showError("please put valid test result");
        } else if (!ssnTextField.getText().matches("^([0-9]{2})([0-9]{2})([0-9]{2})([a-zA-Z0-9][0-9]{3})$")) {
            showError("ssn must be valid 10 digits as YYMMDDXXXX");
        } else {
            try {
                Patient patient = DatabaseHandler.load(Patient.class, ssnTextField.getText());
                Singleton.getInstance().getEmployee();
                if (negativeCheckBox.isSelected()) {
                    new Result(patient, Singleton.getInstance().getEmployee(), date, se.hkr.e7.model.Result.Status.NEGATIVE);
                    showDone("Saved", "thank you ");
                }
                if (positiveCheckBox.isSelected()) {
                    new Result(patient, Singleton.getInstance().getEmployee(), date, se.hkr.e7.model.Result.Status.POSITIVE);
                    showDone("Saved", "thank you ");
                }
                if (pending.isSelected()) {
                    new Result(patient, Singleton.getInstance().getEmployee(), date, Result.Status.PENDING);
                    showDone("Saved", "thank you ");
                }
            } catch (Exception e) {
                if (showOptions("Do you want to make new patient ?", "Can't find Patient")) {
                    Singleton.getInstance().setSsn(ssnTextField.getText());
                    loadScene("view/AddPatientDoctor.fxml", event);
                }
            }
        }
    }
    public void pressNegative(ActionEvent event) {
        positiveCheckBox.setSelected(false);
        pending.setSelected(false);
    }
    public void pressPositive(ActionEvent event) {
        pending.setSelected(false);
        negativeCheckBox.setSelected(false);
    }
    public void pressPending(ActionEvent event) {
        positiveCheckBox.setSelected(false);
        negativeCheckBox.setSelected(false);
    }
}