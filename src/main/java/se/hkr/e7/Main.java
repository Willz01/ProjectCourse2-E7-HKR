package se.hkr.e7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.hkr.e7.controller.Controller;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Mail;
import se.hkr.e7.model.Person;


import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        URL resource = getClass().getClassLoader().getResource("view/Welcome.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


//        Person person = DatabaseHandler.load(Person.class,"199701010000");
//
//        try {
//            Mail.send("reset","password",person,person);
//            Controller.showConfirmation("","email has ben Sent ");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }



    }
}
