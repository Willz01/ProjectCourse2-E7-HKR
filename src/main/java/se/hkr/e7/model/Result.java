package se.hkr.e7.model;

import se.hkr.e7.DatabaseHandler;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Entity
public class Result implements Serializable {

    private Long id;
    private Employee examiner;
    private Patient patient;
    private LocalDateTime dateTime;
    private Status status;
    private String note;

    public Result() {
    }

    public Result(Patient patient, Employee examiner, LocalDateTime dateTime, Status status) {
        this.examiner = examiner;
        this.patient = patient;
        this.dateTime = dateTime;
        this.status = status;

        this.patient.addTestResult(this);
        DatabaseHandler.save(this.patient);

        this.examiner.addPatientResult(this);
        DatabaseHandler.save(this.examiner);
    }

    @Transient
    public String getPatientName() {
        return patient.getName();
    }

    @Transient
    public String getExaminerName() {
        return examiner.getName();
    }

    @Transient
    public String getDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        return simpleDateFormat.format(Timestamp.valueOf(dateTime));
    }

    public void updateStatus(Status status) {
        this.status = status;
        this.dateTime = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(
            cascade = CascadeType.ALL,
            optional = false
    )
    public Patient getPatient() {
        return patient;
    }

    private void setPatient(Patient patient) {
        this.patient = patient;
    }

    @ManyToOne(
            cascade = CascadeType.ALL,
            optional = false
    )
    public Employee getExaminer() {
        return examiner;
    }

    private void setExaminer(Employee examiner) {
        this.examiner = examiner;
    }

    @Column(nullable = false)
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    private void setDateTime(LocalDateTime date) {
        this.dateTime = date;
    }

    @Column(nullable = false)
    public Status getStatus() {
        return status;
    }

    private void setStatus(Status status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", date='" + dateTime + '\'' +
                ", status=" + status +
                ", note='" + note + '\'' +
                '}';
    }

    public enum Status {
        POSITIVE, NEGATIVE, PENDING
    }
}
