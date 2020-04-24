package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public abstract class Controller {

    public void loadScene(String url, ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource(String.valueOf(url));
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}