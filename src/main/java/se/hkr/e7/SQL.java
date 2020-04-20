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

        new Admin("2020-01-01", null, "199701010000", "123456",
                "Wills", "m@yahoo.com", "073656656", "Home").save();


        new User("1993249503", "13412","Marcos", "Home@hao.com", "073656656",
                "Street lamp 432", new EmployeeInformation(200.16, Location.Kalmar)).save();
        new User("198005087778", "", "Nilson", "info@example.com", "056356556",
                "Kristan Street", new EmployeeInformation(200.16, Location.Dalarnas)).save();

        new User("198005087778", "98745794", "Nilson", "nilson@example.com",
                "056356556", "Homes", new EmployeeInformation(200.16, Location.Skane)).save();

        User firstPatient = new User("196154054565", "password1", "Mohammed", "myt@yahoo.com", "062563454", "onehomet 32", null);
        User secondPatient = new User("19880108994", "password1", "Jone", "mymail@yahoo.com", "07332233", "oneStreet 32", null);
        firstPatient.save();
        secondPatient.save();

        Result firstResult = new Result(44454, "2020-01-01", Result.Status.Pending, firstPatient);
        Result secondResult = new Result(45435, "2020-01-01", Result.Status.Positive, firstPatient);

        Result.addResult(firstResult);
        Result.addResult(secondResult);
    }

}


