package se.hkr.e7.controller;

import com.sun.istack.NotNull;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientDashboardController implements Initializable {


    public Text resultText;
    public Button Back;
    public Button Cancel;


    public void Back(@NotNull ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Scene currScene = node.getScene();
        Stage stage = (Stage) currScene.getWindow();
        URL resource = getClass().getClassLoader().getResource("patientLogin.fxml");
        assert resource != null;
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Cancel(ActionEvent actionEvent) {
        System.exit(0);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


//        this not working need some work..
//
//        try (Session session = Database.getSession()) {
//            session.beginTransaction();
//
//            Result result = session.get(Result.class, ssn);
//            resultText.setText(result.getStatus() + "     " + result.getDate());
//            session.getTransaction().commit();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//
//        }

    }


}

