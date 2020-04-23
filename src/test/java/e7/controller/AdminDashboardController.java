package e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminDashboardController {
    public Button Back;
    public Button Exit;
    public Button addAdmin;
    public Button addPatient;
    public Button addStaff;
    public Button viewResults;
    public Button search;
    public Button viewStaff;
    public Button viewPatient;
    public Button removeAdmin;
    public Button removeStaff;
    public TextField searchText;

    public void Back(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("staffLogin.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void addAdmin(ActionEvent actionEvent) throws IOException {
        System.out.println("run");
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("AddAdmin.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addPatient(ActionEvent actionEvent) {
        System.out.println("run");
    }

    public void addStaff(ActionEvent actionEvent) {
        System.out.println("run");

    }

    public void viewResults(ActionEvent actionEvent) {
        System.out.println("run");

    }

    public void viewPatient(ActionEvent actionEvent) {
        System.out.println("run");

    }

    public void viewStaff(ActionEvent actionEvent) {
        System.out.println("run");

    }

    public void search(ActionEvent actionEvent) {
        System.out.println("run");

    }

    public void removeStaff(ActionEvent actionEvent) {
        System.out.println("run");

    }

    public void removeAdmin(ActionEvent actionEvent) {
        System.out.println("run");

    }
}
