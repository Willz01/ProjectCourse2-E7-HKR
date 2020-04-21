package se.hkr.e7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        URL resource = getClass().getClassLoader().getResource("Welcome.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

//        Database.Reset();
        Employee admin = Employee.load("199701010000", Employee.class);
        Employee analyser = Employee.load("198005087778", Employee.class);
        Employee staff = Employee.load("1993249503", Employee.class);
        Patient patient = Patient.load("196154054565", Patient.class);
        for (Person person : List.of(admin, analyser, staff, patient)) {
            if (person != null) {
                System.out.println(person.toString());
            } else {
                System.out.println("No " + person.getClass());
            }
        }
    }
}



