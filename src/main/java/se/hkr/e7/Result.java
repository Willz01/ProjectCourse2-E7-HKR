package se.hkr.e7;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Result implements Serializable {

    private Long id;
    private Employee examiner;
    private Patient patient;
    private String date;
    private Status status;
    private String note;

    public Result() {
    }

    public Result(Patient patient, Employee examiner, String date, Status status) {
        this.examiner = examiner;
        this.patient = patient;
        this.date = date;
        this.status = status;

        this.patient.addTestResult(this);
        this.patient.save();

        this.examiner.addPatientResult(this);
        this.examiner.save();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(optional = false)
    public Patient getPatient() {
        return patient;
    }

    private void setPatient(Patient patient) {
        this.patient = patient;
    }

    @ManyToOne(optional = false)
    public Employee getExaminer() {
        return examiner;
    }

    private void setExaminer(Employee examiner) {
        this.examiner = examiner;
    }

    @Column(nullable = false)
    public String getDate() {
        return date;
    }

    private void setDate(String date) {
        this.date = date;
    }

    @Column(nullable = false)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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
        return
                '\n'+
                ", date=  '" + date + '\n' +
                ", status=  " + status +'\n'+'\n' +'\n' ;
    }

    enum Status {
        POSITIVE, NEGATIVE, PENDING
    }
}
