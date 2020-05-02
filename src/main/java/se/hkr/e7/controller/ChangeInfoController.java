package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Singleton;

import java.sql.SQLOutput;

public class ChangeInfoController extends Controller {


    public Button Save;
    public TextField name;
    public TextField email;
    public TextField phone;
    public TextField address;
    public Label nameLabel;
    public Label emailLabel;
    public Label phoneLabel;
    public Label addressLabel;
    public Label passwordLebel;
    public TextField password;


    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/ChangeInfo.fxml");


    }

    public void Save(ActionEvent event) {


        if (name.getText().isEmpty()) {
            nameLabel.setText(" can't be empty");
        }


        if (address.getText().isEmpty()) {
            addressLabel.setText(" can't be empty");
        }
        if (email.getText().isEmpty()) {
            emailLabel.setText("cant be empty");
        }

        if (password.getText().isEmpty()) {
            passwordLebel.setText(" can't be empty");
        }


        if (Singleton.getInstance().getCurrentUser() == Singleton.getInstance().getEmployee())
            try {
                Singleton.getInstance().getEmployee().setName(name.getText());
                Singleton.getInstance().getEmployee().setAddress(address.getText());
                Singleton.getInstance().getEmployee().setEmail(email.getText());
                Singleton.getInstance().getEmployee().setPhone(phone.getText());
                Singleton.getInstance().getEmployee().updatePassword(password.getText());

                new Employee(Singleton.getInstance().getEmployee().getSsn(),
                        Singleton.getInstance().getEmployee().getPassword(),
                        Singleton.getInstance().getEmployee().getName(),
                        Singleton.getInstance().getEmployee().getEmail(),
                        Singleton.getInstance().getEmployee().getPhone(),
                        Singleton.getInstance().getEmployee().getSsn(),
                        Singleton.getInstance().getEmployee().getLocation(),
                        Singleton.getInstance().getEmployee().getRole(),
                        Singleton.getInstance().getEmployee().getSalary());
                showConfirmation("Complete", "you edit your info successfully");
            } catch (Exception e) {
                showError("something went wrong");
                e.printStackTrace();
            }

        if (Singleton.getInstance().getCurrentUser() == Singleton.getInstance().getPatient()) {
            try {
                Singleton.getInstance().getPatient().setName(name.getText());
                Singleton.getInstance().getPatient().setAddress(address.getText());
                Singleton.getInstance().getPatient().setEmail(email.getText());
                Singleton.getInstance().getPatient().setPhone(phone.getText());
                Singleton.getInstance().getPatient().updatePassword(password.getText());

                Patient patient = new Patient(Singleton.getInstance().getPatient().getSsn(),
                        Singleton.getInstance().getPatient().getPassword(),
                        Singleton.getInstance().getPatient().getName(),
                        Singleton.getInstance().getPatient().getEmail(),
                        Singleton.getInstance().getPatient().getPhone(),
                        Singleton.getInstance().getPatient().getAddress());
                patient.setTestResults(Singleton.getInstance().getPatient().getTestResults());

                showConfirmation("Complete", "you edit your info successfully");
            } catch (Exception e) {
                showError("something went wrong");
                e.printStackTrace();

            }
        }
    }
}