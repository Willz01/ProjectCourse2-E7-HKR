package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Location;

public class AddAdminController extends Controller {
    @FXML
    public ChoiceBox<Location> choiceBox;
    public TextField ssn;
    public TextField name;
    public TextField address;
    public TextField password;
    public TextField email;
    public TextField phone;
    public TextField salary;
    public Label ssnLabel;
    public Label nameLabel;
    public Label passwordLabel;
    public Label addressLabel;
    public Label emailLabel;
    public Label phoneLabel;
    public Label SalaryLabel;
    public Label locationLabel;
    public Label saveLabel;
    public Button addButton;
    public Button backButton;
    public Button exitButton;

    @FXML
    public void initialize() {
        choiceBox.getItems().setAll(Location.values());

        addButton.setOnAction(actionEvent -> addAdmin());
        backButton.setOnAction(actionEvent -> loadScene("view/AdminDashboard.fxml", actionEvent));
        exitButton.setOnAction(status -> System.exit(0));
    }

    private void addAdmin() {
        nameLabel.setText("");
        ssnLabel.setText("");
        passwordLabel.setText("");
        emailLabel.setText("");
        SalaryLabel.setText("");
        phoneLabel.setText("");
        addressLabel.setText("");
        saveLabel.setText("");

        if (ssn.getText().matches("^([0-9]{2})([0-9]{2})([0-9]{2})([a-zA-Z0-9][0-9]{3})$")) {
            ssnLabel.setText("");
        }

        if (!ssn.getText().matches("^([0-9]{2})([0-9]{2})([0-9]{2})([a-zA-Z0-9][0-9]{3})$")) {
            ssnLabel.setText("input in YYMMDDXXXX form");
        }

        if (name.getText().equals("")) {
            nameLabel.setText("can't be empty");
        }

        if (address.getText().equals("")) {
            addressLabel.setText(" can't be empty");
        }

        if (password.getText().equals("")) {
            passwordLabel.setText(" can't be empty");
        }

        if (email.getText().equals("")) {
            emailLabel.setText(" can't be empty");
        }

        if (phone.getText().equals("")) {
            passwordLabel.setText(" can't be empty");
            if (!(salary.getText().matches("^[0-9]+\\.?[0-9]*$"))) {
                salary.setText(" salary must be number");
            }
        }

        try {
            DatabaseHandler.save(new Employee(ssn.getText(), password.getText(), name.getText(), email.getText(),
                    phone.getText(), address.getText(), choiceBox.getValue(), Employee.Role.ADMIN,
                    Double.parseDouble(salary.getText())));
            saveLabel.setText("saved");
        } catch (Exception exception) {
            saveLabel.setText("did't save ");
        }
    }
}


