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
        URL resource = getClass().getClassLoader().getResource("sample.fxml");
        System.out.println(resource);
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();




            Admin firstAdmin = new Admin(12, "Wills", "199701010000", "073656656", "Home", "m@yahoo.com", "123456", "2020-01-01", null);


            Staff firstStaff = new Staff(1, "Marcos", "1993249503", "Home@hao.com", 200.16, "073656656", "Street lamp 432", Staff.Location.Kalmar, "13412", firstAdmin);
            Staff secondStaff = new Staff(2, "Nilson", "198005087778", "Kristan Street", 200.16, "056356556", "Homes", Staff.Location.Skane, "63465345", firstAdmin);


            Analyser firstAnalyser = new Analyser(3, "Nilson", "198005087778", "Kristan Street", 200.16, "056356556", "Homes", "98745794", "2020-05-23", "2020-20-12", firstAdmin);
            Analyser secondAnalyser = new Analyser(4, "Nilson", "198076687778", "Kristan Street", 200.16, "056356556", "Homes", "98745794", "2020-05-23", "2020-20-12", firstAdmin);

            Patient firstPatient = new Patient(5, "Mohammed", "65454565", "563454", "onehomet 32", "myt@yahoo.com");
            Patient secondPatient = new Patient(6, "Jone", "884888994", "07332233", "oneStreet 32", "mymail@yahoo.com");


            Result firstResult = new Result("89TAMGAJ", "2020-01-01", Result.Status.Pending, "199902030943-342", firstPatient);
            Result secondResult = new Result("432234", "2020-01-01", Result.Status.Positive, "199902030943-342", firstPatient);


            Admin.addAdminToDataBase(firstAdmin);



            Patient.addPatientToDataBase(firstPatient);
            Patient.addPatientToDataBase(secondPatient);

            Result.addResult(firstResult);
            Result.addResult(secondResult);



            Analyser.addAnalyserToDataBase(firstAnalyser);
            Analyser.addAnalyserToDataBase(secondAnalyser);


            Staff.addStaffToDataBase(firstStaff);
            Staff.addStaffToDataBase(secondStaff);



        }

}



