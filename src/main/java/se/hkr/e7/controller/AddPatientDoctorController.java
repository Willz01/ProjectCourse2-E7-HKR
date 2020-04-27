package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Patient;

public class AddPatientDoctorController extends Controller {
    public Button backButton;
    public Button exitButton;
    public Button addButton;
    public TextField ssn;
    public TextField name;
    public TextField password;
    public TextField address;
    public TextField email;
    public TextField phone;


    @FXML
    public void initialize() {
        exitButton.setOnAction(this::exit);
        backButton.setOnAction(actionEvent -> loadScene("view/DoctorDashboard.fxml", actionEvent));
        addButton.setOnAction(this::addPatient);
    }

    private void addPatient(ActionEvent actionEvent) {



        if (!ssn.getText().matches("^([0-9]{2})([0-9]{2})([0-9]{2})([a-zA-Z0-9][0-9]{3})$")) {
            showError("input in YYMMDDXXXX form");
        }
        if (name.getText().equals("")) {
            showError("name can't be empty");
        }
        if (address.getText().equals("")) {
            showError("address can't be empty");
        }
        if (password.getText().equals("")) {
            showError("password can't be empty");
        }
        if (email.getText().equals("")) {
            showError("email can't be empty");
        }
        if (phone.getText().equals("")) {
            showError("phone can't be empty");


            try {

                DatabaseHandler.save(new Patient(ssn.getText(), password.getText(), name.getText(), email.getText(),
                        phone.getText(), address.getText()));

                showDone("saved","now the patient is in the System");
            } catch (Exception exception) {
                showError("did't save","please check if the patient is already in the System");

            }
        }
    }

}
