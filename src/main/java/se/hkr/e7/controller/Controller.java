package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import se.hkr.e7.model.Singleton;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;


public abstract class Controller {


    public final static ActionEvent actionEvent = new ActionEvent();

    public void loadScene(String name, ActionEvent actionEvent) {
        try {
            Node node = (Node) actionEvent.getSource();
            Scene currScene = node.getScene();
            Stage stage = (Stage) currScene.getWindow();
            URL resource = getClass().getClassLoader().getResource(name);
            Parent root = FXMLLoader.load(resource);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            if (!name.equals("view/Welcome.fxml")) {
                System.out.println("not");
                loadScene("view/Welcome.fxml", actionEvent);
            } else {
                new Alert(Alert.AlertType.ERROR, "There was an error loading the default scene.", ButtonType.CLOSE)
                        .showAndWait();
                System.exit(1);
            }
        }
    }

    public void exit(ActionEvent actionEvent) {
        Singleton.getInstance().setSsn(null);
        System.exit(0);
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

    void showDone(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.OK);
        alert.setTitle(title);
        alert.getDialogPane().setGraphic(new ImageView("alert_confirmation.png"));
        alert.showAndWait();
    }

    boolean showOptions(String info, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(message);
        alert.setContentText(info);
        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {

            return true;
        } else if (result.get() == buttonTypeCancel) {
            return false;
        }
        return false;
    }
}
