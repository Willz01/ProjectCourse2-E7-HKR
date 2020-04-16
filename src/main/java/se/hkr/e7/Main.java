package se.hkr.e7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;

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

//        get the object from the database

//        Admin.getAdminFromDataBase(12);
//        Analyser.getAnalyserFromDataBase(4);
//        Staff.getStaffFromDataBase(1);
//        Result.getResultFromDataBase(1212121);

    }

}



