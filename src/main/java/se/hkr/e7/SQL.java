package se.hkr.e7;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SQL {

    private static final SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    private Session session;

    public SQL() {
        this.session = sessionFactory.openSession();
    }

    static <T extends SQL> T load(String key, final Class<T> tClass) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        T t = session.get(tClass, key);
        session.getTransaction().commit();
        t.setSession(session);
        return t;
    }

    /**
     * Insert some default data into the system.
     */
    public static void Reset() {
        new User("199701010000", "123456", "Wills", "wills@example.com",
                "073656656", "Home", Role.ADMIN, new EmployeeInformation(123.45, Location.Stockholms)).save();

        new User("1993249503", "13412", "Marcos", "Home@hao.com", "073656656",
                "Street lamp 432", Role.ANALYSER, new EmployeeInformation(200.16, Location.Kalmar)).save();
        new User("198005087778", "", "Nilson", "info@example.com", "056356556",
                "Kristan Street", Role.ANALYSER, new EmployeeInformation(200.16, Location.Dalarnas)).save();

        new User("198005087778", "98745794", "Nilson", "nilson@example.com",
                "056356556", "Homes", Role.DOCTOR, new EmployeeInformation(200.16, Location.Skane)).save();

        new User("19880108994", "password1", "Jone", "mymail@yahoo.com",
                "07332233", "oneStreet 32", Role.PATIENT, null).save();
        User secondPatient = new User("196154054565", "password1", "Mohammed", "myt@yahoo.com",
                "062563454", "onehomet 32", Role.PATIENT, null);
        secondPatient.save();

        new Result(secondPatient, "2020-01-01", Result.Status.PENDING);
        new Result(secondPatient, "2020-01-01", Result.Status.POSITIVE);
    }

    void setSession(Session session) {
        this.session = session;
    }

    void save() {
        session.beginTransaction();
        session.saveOrUpdate(this);
        session.getTransaction().commit();
    }
}


