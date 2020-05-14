package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Result;
import se.hkr.e7.model.Singleton;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class ViewStaffController extends Controller {


    public TableView<Employee> staffTableView;
    @FXML
    public void initialize(){
        Singleton.getInstance().addSceneHistory("view/ViewStaff.fxml");
        List<Employee> employees = DatabaseHandler.loadAll(Employee.class);
        for (Map.Entry<String, String> entry : Map.ofEntries(
                new AbstractMap.SimpleEntry<>("location", "Location"),
                new AbstractMap.SimpleEntry<>("role", "Role"),
                new AbstractMap.SimpleEntry<>("salary", "Salary"),
                new AbstractMap.SimpleEntry<>("ssn", "SSN")
        ).entrySet()) {
            TableColumn<Employee, String> tableColumn = new TableColumn<>(entry.getValue());
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(entry.getKey()));
            staffTableView.getColumns().add(tableColumn);
        }

        staffTableView.getItems().addAll(employees);

    }
}
