package se.hkr.e7.controller;

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
        assert resource != null;
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Exit() {
        System.exit(0);
    }

    public void addAdmin(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("AddAdmin.fxml");
        assert resource != null;
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addPatient(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("AddPatient.fxml");
        assert resource != null;
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    }

    public void addStaff(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("AddStaff.fxml");
        assert resource != null;
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void viewResults(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("ViewResults.fxml");
        assert resource != null;
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void viewPatient(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("ViewPatients.fxml");
        assert resource != null;
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void viewStaff(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("ViewStaff.fxml");
        assert resource != null;
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void search(ActionEvent actionEvent) throws IOException {
        System.out.println("not implemented yet");
    }

    public void removeStaff(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("RemoveStaff.fxml");
        assert resource != null;
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void removeAdmin(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("RemoveAdmin.fxml");
        assert resource != null;
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
