package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import se.hkr.e7.model.*;

import java.time.LocalDate;

public class AddResultController extends Controller {

    public Button exitButton;
    public Button backButton;
    public TextField snn;
    public CheckBox Negative;
    public CheckBox POSITIVE;
    public DatePicker datePicker;


    @FXML
    public void initialize() {
        exitButton.setOnAction(this::exit);
        backButton.setOnAction(actionEvent -> loadScene("view/DoctorDashboard.fxml", actionEvent));

        while (Negative.isSelected()) POSITIVE.setSelected(true);
        while (POSITIVE.isSelected()) Negative.setSelected(true);


    }

    public void Save(ActionEvent event) {
        LocalDate today = LocalDate.now();
        LocalDate bdate = datePicker.getValue();
        String date = String.valueOf(bdate);
        if (bdate != null && bdate.isAfter(today)) {

            showError("you can not chose date after today ");
            System.out.println(date);

        }else
        if (Negative.isSelected()&&POSITIVE.isSelected()||!Negative.isSelected()&&!POSITIVE.isSelected()){
            showError("please put valid test result");
        }else

        if (!snn.getText().matches("^([0-9]{2})([0-9]{2})([0-9]{2})([a-zA-Z0-9][0-9]{3})$")) {
            showError("ssn must be valid 10 digits as YYMMDDXXXX");
        } else {

            try {

                Patient patient = DatabaseHandler.load(Patient.class, snn.getText());
                Singleton.getInstance().getEmployee();
                if (Negative.isSelected()) {
                    Result Result = new Result(patient, Singleton.getInstance().getEmployee(), date, se.hkr.e7.model.Result.Status.NEGATIVE);
                    showConfirmation("Saved", "thank you ");

                }
                if (POSITIVE.isSelected()) {
                    Result Result = new Result(patient, Singleton.getInstance().getEmployee(), date, se.hkr.e7.model.Result.Status.POSITIVE);
                    showConfirmation("Saved", "thank you ");
                }

            } catch (Exception e) {
                showError("can not find the patient");
            }

        }


    }
}