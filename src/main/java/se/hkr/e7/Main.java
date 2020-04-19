package se.hkr.e7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

        SQL.Reset();
        Admin admin = Admin.load("199701010000", Admin.class);
        Analyser analyser = Analyser.load("198005087778", Analyser.class);
        Staff staff = Staff.load("1993249503", Staff.class);
        Patient patient = Patient.load("196154054565", Patient.class);
        for (Person person : List.of(admin, analyser, staff, patient)) {
            if (person != null) {
                System.out.println(person.toString());
            } else {
                System.out.println("No " + person.getClass());
            }
        }

//        get the object from the database

//        Admin.getAdminFromDataBase(12);
//        Analyser.getAnalyserFromDataBase(4);
//        Staff.getStaffFromDataBase(1);



     /*   EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(Admin);
        entityManager.persist(Patient);
        entityManager.persist(Analyser);
        entityManager.persist(Person);
        entityManager.persist(Result);
        entityManager.persist(Singleton);
        entityManager.persist(SQL);
        entityManager.persist(Staff);




        entityManager.getTransaction().commit();
        entityManagerFactory.close();

*/
//        Result.getResultFromDataBase(1212121);

    }

}



