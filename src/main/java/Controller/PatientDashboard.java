package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class PatientDashboard {


    public Text resultText;


    public void Back(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("patientLogin.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void Cancel(ActionEvent actionEvent) {
        System.exit(0);
    }
}
