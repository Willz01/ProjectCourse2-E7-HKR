package se.hkr.e7.controller;

import javafx.event.ActionEvent;
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
    public Button addButton;
    public Button backButton;
    public Button exitButton;

    @FXML
    public void initialize() {
        choiceBox.getItems().setAll(Location.values());

        addButton.setOnAction(this::addAdmin);
        backButton.setOnAction(actionEvent -> loadScene("view/AdminDashboard.fxml", actionEvent));
        exitButton.setOnAction(this::exit);
    }

    private void addAdmin(ActionEvent actionEvent) {

        if (!ssn.getText().matches("^([0-9]{2})([0-9]{2})([0-9]{2})([a-zA-Z0-9][0-9]{3})$")) {
            showError("ssn must be valid 10 digits as YYMMDDXXXX");
        }
        if (!email.getText().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {
            showError("please enter valid email Address");
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

        if (!phone.getText().matches("^[0-9\\-\\+]{9,15}$"))
            showError("please enter valid phone number");

        if (!(salary.getText().matches("^[0-9]+\\.?[0-9]*$"))) {
            showError(" salary must be number");
        }

        if (choiceBox.getValue() == null) {
            showError("please select Location ");
        }

        if (ssn.getText().matches("^([0-9]{2})([0-9]{2})([0-9]{2})([a-zA-Z0-9][0-9]{3})$")
                && email.getText().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
                && phone.getText().matches("^[0-9\\-\\+]{9,15}$")
                && choiceBox.getValue() != null) {
            DatabaseHandler.save(new Employee(ssn.getText(), password.getText(), name.getText(), email.getText(),
                    phone.getText(), address.getText(), choiceBox.getValue(), Employee.Role.ADMIN,
                    Double.parseDouble(salary.getText())));
            showDone("Finished successfully!", "The admin account was created.");
            loadScene("view/AdminDashboard.fxml", actionEvent);
        }
    }
}
