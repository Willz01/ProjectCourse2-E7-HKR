package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


        Admin firstAdmin = new Admin(12, "Wills", "199701010000", "073656656", "Home", "m@yahoo.com", "123456", "2020-01-01", null, null, null);


        Staff firstStaff = new Staff(1, "Marcos", "1993249503", "Home@hao.com", 200.16, "073656656", "Street lamp 432", Staff.Location.Kalmar, "13412", firstAdmin);
        Staff secondStaff = new Staff(18, "Nilson", "198005087778", "Kristan Street", 200.16, "056356556", "Homes", Staff.Location.Skane, "63465345", firstAdmin);


        Analyser firstAnalyser = new Analyser(18, "Nilson", "198005087778", "Kristan Street", 200.16, "056356556", "Homes", "98745794", "2020-05-23", "2020-20-12", firstAdmin);
        Analyser secondAnalyser = new Analyser(18, "Nilson", "198005087778", "Kristan Street", 200.16, "056356556", "Homes", "98745794", "2020-05-23", "2020-20-12", firstAdmin);

        Patient firstPatient = new Patient(1, "Mohammed", "884888994", "07332233", "oneStreet 32", "mymail@yahoo.com");
        Patient secondPatient = new Patient(3, "Jone", "884888994", "07332233", "oneStreet 32", "mymail@yahoo.com");


        Result firstResult = new Result("89TAMGAJ", "2020-01-01", Result.Status.Pending, "199902030943-342", firstPatient);
        Result secondResult = new Result("8912MGAJ", "2020-01-01", Result.Status.Positive, "199902030943-342", firstPatient);


//        Patient firstPatient = new Patient(1, "Mohammed", "884888994", "07332233", "oneStreet 32", "mymail@yahoo.com" );
//        Patient secondPatient = new Patient(3, "Jone", "884888994", "07332233", "oneStreet 32", "mymail@yahoo.com");

//        List<Patient> patients = new ArrayList<>();
//        patients.add(firstPatient);
//        patients.add(secondPatient);


        Result.addResult(firstResult);
        Result.addResult(secondResult);

        Analyser.addAnalyserToDataBase(firstAnalyser);
        Analyser.addAnalyserToDataBase(secondAnalyser);

        Admin.addAdminToDataBase(firstAdmin);
        Staff.addStaffToDataBase(firstStaff);
        Patient.addPatientToDataBase(firstPatient);
        Staff.addStaffToDataBase(secondStaff);
        Patient.addPatientToDataBase(secondPatient);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
