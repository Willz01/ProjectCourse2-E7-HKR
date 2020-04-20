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
        URL resource = getClass().getClassLoader().getResource("login.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Database.Reset();
        User admin = User.load("199701010000", User.class);
        User analyser = User.load("198005087778", User.class);
        User staff = User.load("1993249503", User.class);
        User patient = User.load("196154054565", User.class);
        for (User user : List.of(admin, analyser, staff, patient)) {
            if (user != null) {
                System.out.println(user.toString());
            } else {
                System.out.println("No " + user.getClass());
            }
        }

        System.out.println(admin.getPassword());
        User adminCopy = User.load("199701010000", User.class);
        adminCopy.setEmail("test@example.com");
        adminCopy.save();
        System.out.println(adminCopy.getPassword());
        System.out.println("Password does not match: " + adminCopy.checkPassword("no match"));
        System.out.println("Password does match: " + adminCopy.checkPassword("123456"));
    }
}



