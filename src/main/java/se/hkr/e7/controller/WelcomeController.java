package se.hkr.e7.controller;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;

public class WelcomeController extends Controller {

    public void StaffLogin(ActionEvent actionEvent) throws IOException {
        loadScene("view/StaffLogin.fxml",actionEvent);
    }

    public void PatientLogin(ActionEvent actionEvent) throws IOException {
       loadScene("view/patientLogin.fxml",actionEvent);
    }

    public void Exit() {
        System.exit(0);
    }
}
