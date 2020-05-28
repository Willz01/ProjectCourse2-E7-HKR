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
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
     *
     * @param resultAmount The amount of results that should be automatically generated
     */
    public static void reset(int resultAmount) {
        createDefaultAdmin();
        save(new Employee("9701010000", "oE0mxbdxhCpqvI", "Wills", "wills@example.com",
                "073656656", "Home", Location.STOCKHOLM, Employee.Role.ADMIN, 123.12));

        save(new Employee("8005087778", "oE0mxbdxhCpqvI", "Nilson", "nilson@example.com",
                "056356556", "Kristan Street", Location.DALARNA, Employee.Role.ANALYSER, 111.12));

        Employee employee1 = new Employee("8002249876", "t4mI7zEiPPN8nf", "Petson",
                "petson@example.com", "056356556", "Kristan Street", Location.SKANE, Employee.Role.DOCTOR,
                98.1);
        save(employee1);

        Employee employee2 = new Employee("9304140000", "fsSBHFqQRPWBkI", "Marcos", "marcos@example.com",
                "073656656", "Street lamp 432", Location.KALMAR, Employee.Role.DOCTOR, 111.12);
        save(employee2);

        Patient patient1 = new Patient("6101054565", "9CgPOgCtpA190R", "Mohammed",
                "mohammed@example.com", "062563454", "onehomet 32");
        save(patient1);

        Patient patient2 = new Patient("8801089940", "QNnHfBY0FcYd6O", "Jone", "mymail@yahoo.com",
                "07332233", "oneStreet 32");
        save(patient2);

        generateResults(resultAmount, new Employee[]{employee1, employee2}, new Patient[]{patient1, patient2});
    }

    /**
     * This method generates Results with random status, a default note, and a randomly selected employee and patient.
     *
     * @param amount    The amount of results to generate
     * @param employees An array of employees from which one will be selected per result
     * @param patients  An array of patients from which one will be selected per result
     */
    public static void generateResults(int amount, Employee[] employees, Patient[] patients) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        LocalDateTime now = LocalDateTime.now();
        long latest = now.toEpochSecond(ZoneOffset.UTC);
        long earliest = now.minusDays(30).toEpochSecond(ZoneOffset.UTC);

        for (int i = 0; i < amount; i++) {
            LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(
                    random.nextLong(earliest, latest), 0, ZoneOffset.UTC);
            Result.Status status;
            switch (random.nextInt(3)) {
                case 0:
                    status = Result.Status.POSITIVE;
                    break;
                case 1:
                    status = Result.Status.NEGATIVE;
                    break;
                default:
                    status = Result.Status.PENDING;
                    break;
            }

            Result result = new Result(
                    patients[random.nextInt(0, patients.length)],
                    employees[random.nextInt(0, employees.length)],
                    localDateTime,
                    status
            );
            result.setNote("Lorem ipsum");
            DatabaseHandler.save(result);
        }
    }

    public static List<Object[]> query(String query) {
        return session.createQuery(query, Object[].class).getResultList();
    }

    public static List sqlQuery(String query) {
        return session.createSQLQuery(query).getResultList();

    }

    public static List<Patient> getPatients(Employee employee) {
        return session.createQuery("SELECT T FROM Patient T JOIN Person P ON T LIKE P JOIN Result R ON T " +
                "LIKE R.patient JOIN Employee E ON E LIKE R.examiner WHERE E.ssn LIKE :ssn GROUP BY T", Patient.class)
                .setParameter("ssn", employee.getSsn())
                .getResultList();
    }

}
