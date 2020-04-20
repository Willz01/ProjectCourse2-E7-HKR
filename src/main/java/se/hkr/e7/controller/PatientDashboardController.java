package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import se.hkr.e7.Result;
import se.hkr.e7.SQL;
import se.hkr.e7.Singleton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientDashboardController implements Initializable {


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


    @Override
    public void initialize(URL location, ResourceBundle resources) {


//        this not working need some work..
//
//        try (Session session = SQL.getSession()) {
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
