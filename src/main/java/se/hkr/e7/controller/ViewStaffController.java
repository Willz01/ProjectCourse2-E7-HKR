package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Employee;

import java.io.IOException;
import java.util.List;


public class ViewStaffController extends Controller {

    public Button exitButton;
    public Button backButton;
    public TextArea showStaffTextArea;
    public ChoiceBox<String> choiceBox;

    @FXML
    public void initialize() {
        exitButton.setOnAction(this::exit);
        backButton.setOnAction(actionEvent -> loadScene("view/AdminDashboard.fxml", actionEvent));
    }


    @FXML
    public void initialize() {
        List<Employee> users = DatabaseHandler.loadAllData(Employee.class);
        int count = 0;
        for (Employee employee : users) {
            count++;
            if (employee.getRole() == Employee.Role.ADMIN)
                choiceBox.getItems().add(count + "- {  " + employee.getRole() + "  }" + "   " + employee.getName() + " ----  " + " Location: " + employee.getLocation() + "  ---- " + " SSN: " + employee.getSsn());
        }
        for (Employee employee : users) {
            count++;
            if (employee.getRole() == Employee.Role.DOCTOR)
                choiceBox.getItems().add(count + "- {  " + employee.getRole() + "  }" + "   " + employee.getName() + " ----  " + " Location: " + employee.getLocation() + "  ---- " + " SSN: " + employee.getSsn());

        }
        for (Employee employee : users) {
            count++;
            if (employee.getRole() == Employee.Role.ANALYSER)
                choiceBox.getItems().add(count + "- {  " + employee.getRole() + "  }" + "   " + employee.getName() + " ----  " + " Location: " + employee.getLocation() + "  ---- " + " SSN: " + employee.getSsn());
        }
        choiceBox.getItems().add("View Staff");

        choiceBox.setValue("View Staff");
    }

}
