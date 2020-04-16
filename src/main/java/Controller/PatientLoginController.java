package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.query.Query;
import se.hkr.e7.Patient;
import se.hkr.e7.SQL;

import java.io.IOException;
import java.net.URL;

public class PatientLoginController {


    public TextField ssnText;


    public void Back(ActionEvent actionEvent) throws IOException {

        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("login.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void Cancel(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void patientLogin(ActionEvent actionEvent) throws IOException {


        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("patientDashboard.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
}

