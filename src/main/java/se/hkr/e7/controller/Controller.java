package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public abstract class Controller {

    private static Stage stage;

    public static void setStage(Stage stage) {
        Controller.stage = stage;
    }

    public void loadScene(String path) {
        try {
            URL resource = getClass().getClassLoader().getResource(path);
            Parent root = FXMLLoader.load(resource);
            stage.setResizable(false);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            if (!path.equals("view/Welcome.fxml")) {
                loadScene("view/Welcome.fxml");
            } else {
                new Alert(Alert.AlertType.ERROR, "There was an error loading the default scene.", ButtonType.CLOSE)
                        .showAndWait();
                System.exit(1);
            }
        }
    }

    void showError(String message) {
        showError("Input error - please retry.", message);
    }

    void showError(String title, String message) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle(title);
        error.setContentText(message);
        error.showAndWait();
    }

    void showConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.OK);
        alert.setTitle(title);
        alert.getDialogPane().setGraphic(new ImageView("view/images/alert_confirmation.png"));
        alert.showAndWait();
    }

    boolean showChoice(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
