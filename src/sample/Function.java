package sample;

import org.hibernate.Session;

public class Function {


    public static void addPatientToDataBase(Patient patient) {


        // Save the customer object

        try (Session session = SQL.getSession()) {
            session.beginTransaction();
            session.save(patient);

            session.flush();
        }
    }


    public static void addResult(Result result) {

        try (Session session = SQL.getSession()) {
            session.beginTransaction();
            session.save(result);

            session.flush();
        }
    }


    public static void addStaffToDataBase(Staff staff) {

        try (Session session = SQL.getSession()) {
            session.beginTransaction();
            session.save(staff);

            session.flush();
        }
    }


    public static void addAdminToDataBase(Admin admin) {


        try (Session session = SQL.getSession()) {
            session.beginTransaction();

            session.save(admin);

            session.flush();
        }


    }


}
