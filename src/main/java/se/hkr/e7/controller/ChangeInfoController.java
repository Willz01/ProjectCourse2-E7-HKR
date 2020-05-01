package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import se.hkr.e7.model.*;

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
    public Circle phoneg;
    public Circle emailg;
    public Circle nameg;
    public Circle addressg;



    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/ChangeInfo.fxml");
        phoneg.setVisible(false);
        emailg.setVisible(false);
        nameg.setVisible(false);
        address.setVisible(false);

        while (name.getText().isEmpty())
            nameg.setFill(Paint.valueOf("#d1231f"));
        while (Person.isValidPhone(phone.getText()))
            phoneg.setFill(Paint.valueOf("#d1231f"));
        while (address.getText().isEmpty())
            addressg.setFill(Paint.valueOf("#d1231f"));
        while (Person.isValidEmail(email.getText()))
            emailg.setFill(Paint.valueOf("#d1231f"));
    }

    public void Save(ActionEvent event) {

        if (name.getText().isEmpty()) {
            name.setText(" can't be empty");
        }

        if (address.getText().isEmpty()) {
            addressLabel.setText(" can't be empty");
        }

        if (email.getText().isEmpty()) {
            emailLabel.setText(" can't be empty");
        }if (Person.isValidEmail("please enter valid email"))

        if (phone.getText().isEmpty()) {
            phoneLabel.setText(" can't be empty");
        }if (Person.isValidPhone(phone.getText())){
            showError("pleas enter valid phone number");
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