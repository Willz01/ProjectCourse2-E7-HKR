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


