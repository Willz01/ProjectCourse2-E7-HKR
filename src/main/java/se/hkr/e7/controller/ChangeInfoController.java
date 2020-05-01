package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import se.hkr.e7.model.Employee;
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
            name.setText(" can't be empty");
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
        }
    }
}