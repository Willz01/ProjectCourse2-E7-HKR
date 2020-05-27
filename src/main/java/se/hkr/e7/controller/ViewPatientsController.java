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

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewPatientsController extends Controller {

    public TableView<Patient> patientTableView;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/ViewPatients.fxml");
        Person currentUser = Singleton.getInstance().getCurrentUser();
        List<Patient> patients;

        if (currentUser instanceof Employee) {
            if (((Employee) currentUser).getRole() == Employee.Role.ADMIN) {
                patients = DatabaseHandler.loadAll(Patient.class);
            } else {
                patients = ((Employee) currentUser).getPatients();
            }
        } else {
            return;
        }

        for (Map.Entry<String, String> entry : Map.ofEntries(
                new AbstractMap.SimpleEntry<>("name", "Name"),
                new AbstractMap.SimpleEntry<>("ssn", "SSN"),
                new AbstractMap.SimpleEntry<>("email", "Email"),
                new AbstractMap.SimpleEntry<>("address", "Address")
        ).entrySet()) {
            TableColumn<Patient, String> tableColumn = new TableColumn<>(entry.getValue());
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(entry.getKey()));
            patientTableView.getColumns().add(tableColumn);
        }

        patientTableView.getItems().addAll(patients);
    }
}
