package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.hkr.e7.Singleton;

import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;

public class ButtonsController extends Controller {
    public Button backButton;
    public Button helpButton;

    @FXML
    public void initialize() {
        backButton.setOnAction(actionEvent -> {
            try {
                loadScene(Singleton.getInstance().getPreviousScene());
            } catch (NoSuchElementException e) {
                loadScene("view/Welcome.fxml");
            }
        });

        helpButton.setOnMouseClicked(mouseEvent -> {
            Stage stage = new Stage();
            URL resource = getClass().getClassLoader().getResource("view/HelpMenu.fxml");
            try {
                Parent root = FXMLLoader.load(resource);
                Scene scene = new Scene(root);
                stage.setTitle("Help Menu");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
