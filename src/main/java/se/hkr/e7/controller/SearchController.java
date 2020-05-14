package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Patient;

public class SearchController extends Controller {
    public TextArea text;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/Search.fxml");
        StringBuilder stringBuilder = new StringBuilder();
        if (Singleton.getInstance().getPatient() != null) {
            Patient patient = Singleton.getInstance().getPatient();
            stringBuilder.append(" - ")
                    .append("----Is Patient----  ")
                    .append(" \nName  : ")
                    .append(patient.getName()).append(System.lineSeparator())
                    .append(" SSN : ")
                    .append(patient.getSsn()).append(System.lineSeparator())
                    .append(" Phone : ")
                    .append(patient.getPhone()).append(System.lineSeparator())
                    .append(" Email : ")
                    .append(patient.getEmail()).append(System.lineSeparator())
                    .append(" Address : ")
                    .append(patient.getAddress()).append(System.lineSeparator())
                    .append(patient.getTestResults() != null ? "     || Note : " + patient.getTestResults() : "")
                    .append(System.lineSeparator()).append(System.lineSeparator()).append(System.lineSeparator());
        }
        try {
            if (Singleton.getInstance().getEmployee() != null) {
                Employee employee = Singleton.getInstance().getEmployee();
                stringBuilder.append(" - ")
                        .append("-----Is Employ ---- ")
                        .append(System.lineSeparator())
                        .append(" Name : ")
                        .append(employee.getName()).append(System.lineSeparator())
                        .append(" Staff SSN : ")
                        .append(employee.getSsn()).append(System.lineSeparator())
                        .append(" Location : ")
                        .append(employee.getLocation()).append(System.lineSeparator())
                        .append(" Role : ")
                        .append(employee.getRole()).append(System.lineSeparator())
                        .append(" Address : ")
                        .append(employee.getAddress()).append(System.lineSeparator())
                        .append(" Email : ")
                        .append(employee.getEmail()).append(System.lineSeparator())
                        .append(" Phone : ")
                        .append(employee.getPhone()).append(System.lineSeparator())
                        .append(" Salary : ")
                        .append(employee.getSalary()).append(System.lineSeparator())
                        .append(System.lineSeparator()).append(System.lineSeparator()).append(System.lineSeparator());
            }
        } finally {
            text.setText(String.valueOf(stringBuilder));
        }
    }
}