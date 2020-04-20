package se.hkr.e7;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Result implements Serializable {

    private Long id;
    private User patient;
    private String date;
    private Status status;

    public Result(User patient, String date, Status status) {
        this.patient = patient;
        this.date = date;
        this.status = status;
        patient.addResult(this);
        patient.save();
    }

    public Result() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    @Column(nullable = false)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(nullable = false)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", status=" + status +
                '}';
    }

    enum Status {
        POSITIVE, NEGATIVE, PENDING
    }
}
