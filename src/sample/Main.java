package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();



SQL.getSession();


//        Admin firstAdmin = new Admin("Wills", "199701010000", "073656656", "Home", "m@yahoo.com", "1", "123456", "2020-01-01");
//        Staff fs = new Staff("Tom", "199701010000", "073656656", "Home", "m@yahoo.com", "1", "1233213", 1312, Staff.Location.Kalmar, "1");
//
//        Patient fp = new Patient("1", "Adam", "199001018545", "076465645", "Home", "kdj@kkdj.com");
//        Patient tp = new Patient("1", "fda", "199001018545", "076465645", "Home", "kdj@kkdj.com");
//        Result fr = new Result("1", "2020-01-01", Result.Status.Pending, fp.getPatientID());
//        Function.addResult(fr);
//        Function.addAdminToDataBase(firstAdmin);
//        Function.addPatientToDataBase(tp);
//
//        Function.addStaffToDataBase(fs);
































    }


    public static void main(String[] args) {
        launch(args);
    }
}
