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
        if (phone.getText().isEmpty()) {
            phoneLabel.setText("can't be empty ");
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


        if (Singleton.getInstance().getEmployee()!=null)
            try {

                Employee employee = DatabaseHandler.load(Employee.class,Singleton.getInstance().getCurrentUser().getSsn());

                      employee.updatePassword(password.getText());
                        employee.setName(name.getText());
                        employee.setEmail(email.getText());
                        employee.setPhone(phone.getText());
                        employee.setAddress(address.getText());

                DatabaseHandler.save(employee);

                showConfirmation("Complete", "you edit your info successfully");
            } catch (Exception e) {
                showError("something went wrong");
                e.printStackTrace();
            }

        if (Singleton.getInstance().getPatient() != null) {
            try {


                Patient patient = DatabaseHandler.load(Patient.class,Singleton.getInstance().getCurrentUser().getSsn());

                patient.updatePassword(password.getText());
                patient.setName(name.getText());
                patient.setEmail(email.getText());
                patient.setPhone(phone.getText());
                patient.setAddress(address.getText());

                DatabaseHandler.save(patient);

                showConfirmation("Complete", "you edit your info successfully");
            } catch (Exception e) {
                showError("something went wrong..");
                e.printStackTrace();

            }
        }
    }
}