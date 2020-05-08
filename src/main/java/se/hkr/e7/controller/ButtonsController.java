package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.hkr.e7.model.Singleton;

import java.io.IOException;
import java.net.URL;

public class ButtonsController extends Controller {
    public Button backButton;
    public Button helpButton;

    @FXML
    public void initialize() {
        backButton.setOnAction(actionEvent -> loadScene(Singleton.getInstance().getPreviousScene(), actionEvent));
        helpButton.setOnMouseClicked(mouseEvent -> {
            Stage stage = new Stage();
            URL resource = getClass().getClassLoader().getResource("view/HelpMenu.fxml");
            Parent root = null;
            try {
                root = FXMLLoader.load(resource);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            stage.setTitle("Help Menu");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        });
    }
}
