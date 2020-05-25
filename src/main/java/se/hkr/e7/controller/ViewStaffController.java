package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Person;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ViewStaffController extends Controller {


    public TableView<Employee> staffTableView;
    public TextField filterTextField;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/ViewStaff.fxml");
        List<Employee> employees = DatabaseHandler.loadAll(Employee.class).stream()
                .filter(Person::isEnabled).collect(Collectors.toList());
        for (Map.Entry<String, String> entry : Map.ofEntries(
                new AbstractMap.SimpleEntry<>("ssn", "SSN"),
                new AbstractMap.SimpleEntry<>("name", "Name"),
                new AbstractMap.SimpleEntry<>("email", "Email"),
                new AbstractMap.SimpleEntry<>("role", "Role"),
                new AbstractMap.SimpleEntry<>("location", "Location"),
                new AbstractMap.SimpleEntry<>("salary", "Salary")
        ).entrySet()) {
            TableColumn<Employee, String> tableColumn = new TableColumn<>(entry.getValue());
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(entry.getKey()));
            staffTableView.getColumns().add(tableColumn);
        }

        staffTableView.getItems().addAll(employees);
        filterTextField.textProperty().addListener((observableValue, s, t1) -> staffTableView.getItems().setAll(
                employees.stream().filter(e -> {
                    for (String string : new String[]{e.getSsn(), e.getName(), e.getEmail(), e.getRole().toString(),
                            e.getLocation().toString(), String.valueOf(e.getSalary())
                    }) {
                        if (string.toLowerCase().contains(t1.toLowerCase())) {
                            return true;
                        }
                    }
                    return false;
                }).collect(Collectors.toList())));
    }
}
