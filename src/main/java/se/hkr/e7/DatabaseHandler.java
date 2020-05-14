package se.hkr.e7;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import se.hkr.e7.model.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class DatabaseHandler {

    private static final SessionFactory sessionFactory;
    private static final Session session;

    static {
        ServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("./hibernate.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Patient.class)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Result.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();
        sessionFactory = metadata.buildSessionFactory();
        session = sessionFactory.openSession();
    }

    private DatabaseHandler() {
    }

    public static <T> T load(final Class<T> tClass, Serializable key) {
        session.beginTransaction();
        T t = session.get(tClass, key);
        session.getTransaction().commit();
        return t;
    }

    /**
     * This method will get all object in table <the command to run it is
     * List<Employee> users = DatabaseHandler.loadAll(Employee.class);
     *
     * @param tClass A Hibernate annotated class type
     * @return A list containing objects of type T
     */
    public static <T> List<T> loadAll(Class<T> tClass) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(tClass);
        criteria.from(tClass);
        return session.createQuery(criteria).getResultList();
    }

    public static void save(Object object) {
        session.beginTransaction();
        session.saveOrUpdate(object);
        session.getTransaction().commit();
    }

    public static void delete(Object object) {
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
    }

    public static void createDefaultAdmin() {
        List<Employee> employees = loadAll(Employee.class);
        if (employees.size() == 0) {
            save(new Employee("0101010000", "H8hLqlTz5QfJmh", "", "", "",
                    "", Location.STOCKHOLM, Employee.Role.ADMIN, 0));
        }
    }

    /**
     * Insert some default data into the system.
     */
    public static void reset() {
        save(new Employee("9701010000", "oE0mxbdxhCpqvI", "Wills", "wills@example.com",
                "073656656", "Home", Location.STOCKHOLM, Employee.Role.ADMIN, 123.12));

        save(new Employee("9304140000", "fsSBHFqQRPWBkI", "Marcos", "marcos@example.com",
                "073656656", "Street lamp 432", Location.KALMAR, Employee.Role.DOCTOR, 111.12));

        save(new Employee("8005087778", "t4mI7zEiPPN8nf", "Nilson", "nilson@example.com",
                "056356556", "Kristan Street", Location.DALARNA, Employee.Role.ANALYSER, 111.12));

        save(new Patient("8801089940", "QNnHfBY0FcYd6O", "Jone", "mymail@yahoo.com",
                "07332233", "oneStreet 32"));

        Employee employee = new Employee("8002249876", "kU2T7uBoGAj1EV", "Petson",
                "petson@example.com", "056356556", "Kristan Street", Location.SKANE, Employee.Role.DOCTOR,
                98.1);
        save(employee);

        Patient patient = new Patient("6101054565", "9CgPOgCtpA190R", "Mohammed",
                "mohammed@example.com", "062563454", "onehomet 32");
        save(patient);

        new Result(patient, employee, LocalDateTime.parse("2020-01-01T10:15:00"), Result.Status.PENDING);
        Result result = new Result(patient, employee, LocalDateTime.parse("2020-01-01T14:39:23"), Result.Status.POSITIVE);
        result.setNote("Test note");
        DatabaseHandler.save(result);
    }
}