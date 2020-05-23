package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Person;
import se.hkr.e7.model.Result;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;


public class ViewResultsController extends Controller {

    public TableView<Result> resultsTableView;

    @FXML
    public void initialize() {
        Singleton singleton = Singleton.getInstance();
        singleton.addSceneHistory("view/ViewResults.fxml");
        Person currentUser = singleton.getCurrentUser();
        List<Result> results;

        if (currentUser instanceof Patient) {
            results = ((Patient) currentUser).getTestResults();
        } else if (currentUser instanceof Employee) {
            if (((Employee) currentUser).getRole() == Employee.Role.ADMIN) {
                results = DatabaseHandler.loadAll(Result.class);
            } else {
                results = ((Employee) currentUser).getPatientResults();
            }
        } else {
            return;
        }

        for (Map.Entry<String, String> entry : Map.ofEntries(
                new AbstractMap.SimpleEntry<>("dateFormat", "Date"),
                new AbstractMap.SimpleEntry<>("status", "Status"),
                new AbstractMap.SimpleEntry<>("patientName", "Patient"),
                new AbstractMap.SimpleEntry<>("examinerName", "Examiner"),
                new AbstractMap.SimpleEntry<>("note", "Note")
        ).entrySet()) {
            TableColumn<Result, String> tableColumn = new TableColumn<>(entry.getValue());
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(entry.getKey()));
            resultsTableView.getColumns().add(tableColumn);
        }

        resultsTableView.getItems().addAll(results);
        resultsTableView.getSelectionModel().selectedItemProperty().addListener(((observableValue, result, t1) -> {
            if (t1 != null) {
                singleton.setResult(t1);
                loadScene("view/ViewSingleResult.fxml");
            }
        }));
    }
}
