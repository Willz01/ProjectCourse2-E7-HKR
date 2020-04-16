package se.hkr.e7;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SQL {


    private static final SessionFactory ourSessionFactory;

    static {

        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {

            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();


    }


    public static void Reset() {
        //this method reset the remote database to the default if the hibernate.cfg file set to create  not to update .

        Admin firstAdmin = new Admin(111, "Wills", "199701010000", "073656656", "Home", "m@yahoo.com", "123456", "2020-01-01", null);


        Staff firstStaff = new Staff(2, "Marcos", "1993249503", "Home@hao.com", 200.16, "073656656", "Street lamp 432", Staff.Location.Kalmar, "13412", firstAdmin);
        Staff secondStaff = new Staff(3, "Nilson", "198005087778", "Kristan Street", 200.16, "056356556", "Homes", Staff.Location.Skane, "63465345", firstAdmin);


        Analyser firstAnalyser = new Analyser(45, "Nilson", "198005087778", "Kristan Street", 200.16, "056356556", "Homes", "98745794", "2020-05-23", "2020-20-12", firstAdmin);
        Analyser secondAnalyser = new Analyser(44, "Nilson", "198076687778", "Kristan Street", 200.16, "056356556", "Homes", "98745794", "2020-05-23", "2020-20-12", firstAdmin);

        Patient firstPatient = new Patient("Mohammed", "196154054565", "062563454", "onehomet 32", "myt@yahoo.com");
        Patient secondPatient = new Patient("Jone", "19880108994", "07332233", "oneStreet 32", "mymail@yahoo.com");


        Result firstResult = new Result(44454, "2020-01-01", Result.Status.Pending, firstPatient);
        Result secondResult = new Result(45435, "2020-01-01", Result.Status.Positive, firstPatient);


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


