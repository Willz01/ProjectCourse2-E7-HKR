package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import se.hkr.e7.model.Employee;
import se.hkr.e7.model.Patient;
import se.hkr.e7.model.Singleton;

public class HelpController {
    public Label label;

    @FXML
    public void initialize() {
        if (Singleton.getInstance().getCurrentUser() == null) {
            label.setText("General help menu");
        } else if (Singleton.getInstance().getCurrentUser().getClass().getSimpleName().equalsIgnoreCase("Patient")) {
            label.setText("Patient help menu");
        } else if (Singleton.getInstance().getCurrentUser().getClass().getSimpleName().equalsIgnoreCase("Employee")) {
            switch (Singleton.getInstance().getEmployee().getRole()) {
                case ADMIN:
                    label.setText("Admin help menu");
                    break;
                case ANALYSER:
                    label.setText("Analyser help menu");
                    break;
                case DOCTOR:
                    label.setText("Doctor help menu");
                    break;
            }

        }
    }
}
