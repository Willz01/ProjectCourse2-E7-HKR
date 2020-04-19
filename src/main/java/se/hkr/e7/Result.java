package se.hkr.e7;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    public Result(int id, String date, Status status, Patient patient) {
        this.id = id;
        this.date = date;
        this.status = status;
    }

    public Result() {}

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public static void addResult(Result result) {

        try (Session session = SQL.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(result);

            session.flush();
        }
    }

    enum Status {
        Positive, Negative, Pending

    }

}
