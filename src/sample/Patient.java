package sample;

import org.hibernate.Session;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient extends Person implements Serializable {
    private static final long serialVersionUID = 1L;


    @Column(name = "address", nullable = false)
    String address = getAddress();
    @Column(name = "name", nullable = false)
    String name = getName();

    @Column(name = "SSN", nullable = false, unique = true)
    String ssn = getSSN();
    @Column(name = "phone", nullable = true)
    String phone = getPhone();
    @Id
    @Column(name = "patientID", unique = true)
    private int patientID = getPatientID();
    @Column(name = "email", nullable = false)
    String email = getEmail();


    public Patient(int patientID, String name, String ssn, String phone, String address, String email) {
        this.patientID = patientID;
        this.name = name;
        this.ssn = ssn;
        this.phone = phone;
        this.address = address;
        this.email = email;

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }


    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Patient{" +
                "patientID='" + patientID + '\'' +
                ", name='" + name + '\'' +
                ", ssn='" + ssn + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


    public static void addPatientToDataBase(Patient patient) {


        // Save the customer object

        try (Session session = SQL.getSession()) {
            session.beginTransaction();
            session.save(patient);

            session.flush();
        }
    }


}
