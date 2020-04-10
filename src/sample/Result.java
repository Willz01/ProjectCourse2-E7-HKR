package sample;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "patientSSN", nullable = true)
    private String ssn = getSsn();


    @Id
    @Column(name = "testID", unique = true)
    private String id;
    @Column(name = "date", nullable = true)
    private String date = getDate();
    @Column(name = "status", nullable = true)
    private Status status = getStatus();

    public Result(String id, String date, Status status, String patientssn) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.ssn = patientssn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Result{" +
                "ssn='" + ssn + '\'' +
                ", id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", status=" + status +
                '}';
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


    enum Status {
        Positive, Negative, Pending;

    }
}
