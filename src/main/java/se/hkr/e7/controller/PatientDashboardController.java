package se.hkr.e7.controller;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import se.hkr.e7.Patient;
import se.hkr.e7.Person;
import se.hkr.e7.Singleton;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PatientDashboardController implements Initializable {


    public TextArea resultText;
    public Button Back;
    public Button Exit;


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

    public void Exit(ActionEvent actionEvent) {
        System.exit(0);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


      try {
          Patient patient = Patient.load(Singleton.getInstance().getSsn(), Patient.class);


            resultText.setText(String.valueOf(patient.getTestResults()));
      }catch (Exception exception){
          resultText.setText("could not find result please press back and try again ");
      }

    }


}

