package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Singleton;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class ViewPatientsController extends Controller {

    public TableView<Patient> patientTableView;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/ViewPatients.fxml");
        List<Patient> patients = DatabaseHandler.loadAll(Patient.class);

        for (Map.Entry<String, String> entry : Map.ofEntries(
                new AbstractMap.SimpleEntry<>("name", "name"),
                new AbstractMap.SimpleEntry<>("ssn", "ssn"),
                new AbstractMap.SimpleEntry<>("email", "email"),
                new AbstractMap.SimpleEntry<>("address", "address")
        ).entrySet()) {
            TableColumn<Patient, String> tableColumn = new TableColumn<>(entry.getValue());
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(entry.getKey()));
            patientTableView.getColumns().add(tableColumn);
        }

        patientTableView.getItems().addAll(patients);
    }
}
