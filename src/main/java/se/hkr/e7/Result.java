package se.hkr.e7;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "testID", unique = true)
    private int id;
    @Column(name = "date", nullable = false)
    private String date = getDate();
    @Column(name = "status", nullable = false)
    private Status status = getStatus();
    @ManyToOne(cascade = CascadeType.ALL)
    Patient patient;
    @Column(name = "patientSSN", nullable = false)
    private String ssn = getSsn();

    public Result(int id, String date, Status status, String patientSsn, Patient patient) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.ssn = patientSsn;
        this.patient = patient;
    }


    enum Status {
        Positive, Negative, Pending;

    }
    public Result() {


    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", status=" + status +
                ", patient=" + patient +
                ", ssn='" + ssn + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


    public static void addResult(Result result) {

        try (Session session = SQL.getSession()) {
            session.beginTransaction();
            session.save(result);

            session.flush();
        }
    }
    public static void getResultFromDataBase(int testID) {


        try (Session session = SQL.getSession()) {
            session.beginTransaction();

            Result result = (Result) session.get(Result.class, testID);
            System.out.println(result.getStatus());
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();

        }


    }

}
