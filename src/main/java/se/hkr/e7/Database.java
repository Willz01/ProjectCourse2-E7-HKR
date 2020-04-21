package se.hkr.e7;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;

public abstract class Database implements Serializable {

    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    private Session session;

    public Database() {
        this.session = sessionFactory.openSession();
    }

    public static <T extends Database> T load(String key, final Class<T> tClass) {
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
        new Employee("199701010000", "123456", "Wills", "wills@example.com",
                "073656656", "Home", Location.STOCKHOLM, Employee.Role.ADMIN, 123.12).save();

        new Employee("1993249503", "13412", "Marcos", "marcos@example.com",
                "073656656", "Street lamp 432", Location.KALMAR, Employee.Role.ANALYSER, 111.12).save();

        new Employee("198005087778", "wegpijewg", "Nilson", "nilson@example.com",
                "056356556", "Kristan Street", Location.DALARNA, Employee.Role.ANALYSER, 111.12).save();

        Employee employee = new Employee("198002249876", "98745794", "Petson",
                "petson@example.com", "056356556", "Kristan Street", Location.SKÅNE, Employee.Role.DOCTOR,
                98.1);
        employee.save();

        new Patient("19880108994", "password1", "Jone", "mymail@yahoo.com",
                "07332233", "oneStreet 32").save();
        Patient patient = new Patient("196154054565", "password1", "Mohammed",
                "mohammed@example.com", "062563454", "onehomet 32");
        patient.save();

        new Result(patient, employee, "2020-01-01", Result.Status.PENDING);
        new Result(patient, employee, "2020-01-01", Result.Status.POSITIVE);
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


