package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Employee;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import static se.hkr.e7.model.DatabaseHandler.loadAllData;

public class ViewStaffController {
    public TextArea showStaff;
    public void Back(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("view/AdminDashboard.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() throws IOException {
        List<Employee> employees = loadAllData(Employee.class, DatabaseHandler.getSession());
        showStaff.setText(String.valueOf(employees));

    }

    public void Exit() {
        System.exit(0);
    }
}
