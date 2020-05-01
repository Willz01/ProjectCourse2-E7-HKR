package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Singleton;

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


    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/ChangeInfo.fxml");
    }

    public void Save(ActionEvent event) {

        if (name.getText().equals("")) {
            name.setText(" can't be empty");
        }
        if (address.getText().equals("")) {
            addressLabel.setText(" can't be empty");
        }

        if (email.getText().equals("")) {
            emailLabel.setText(" can't be empty");
        }
        if (phone.getText().equals("")) {
            phoneLabel.setText(" can't be empty");
        }

        if (Singleton.getInstance().getCurrentUser() == Singleton.getInstance().getEmployee()) {

            Employee employee = DatabaseHandler.load(Employee.class, Singleton.getInstance().getCurrentUser());

            employee.setName(name.getText());
            employee.setAddress(address.getText());
            employee.setEmail(email.getText());
            employee.setPhone(phone.getText());
            DatabaseHandler.save(employee);
            showConfirmation("Complete", "you edit your info successfully");

        }
        if ((Singleton.getInstance().getCurrentUser() == Singleton.getInstance().getPatient())) {
            Patient patient = DatabaseHandler.load(Patient.class, Singleton.getInstance().getCurrentUser());

            patient.setName(name.getText());
            patient.setAddress(address.getText());
            patient.setEmail(email.getText());
            patient.setPhone(phone.getText());
            DatabaseHandler.save(patient);
            showConfirmation("Complete", "you edit your info successfully");
        }
        showError("something went wrong");
    }
}