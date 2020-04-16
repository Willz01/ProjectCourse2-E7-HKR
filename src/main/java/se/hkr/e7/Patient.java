package se.hkr.e7;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Patient extends Person implements Serializable {
    private static final long serialVersionUID = 1L;


    @Column(name = "address", nullable = false)
    String address = getAddress();
    @Column(name = "name", nullable = false)
    String name = getName();
    @Id
    @Column(name = "SSN", nullable = false, unique = true)
    String ssn = getSSN();
    @Column(name = "phone", nullable = true)
    String phone = getPhone();

    @Column(name = "email", nullable = false)
    String email = getEmail();


    public Patient(String name, String ssn, String phone, String address, String email) {
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

    public Patient() {


    }

    @Override
    public String toString() {
        return "Patient{" +
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

    public static void getPatientFromDataBase(String ssn) {


        try (Session session = SQL.getSession()) {
            session.beginTransaction();

            Patient patient = session.get(Patient.class, ssn);
            System.out.println(patient.getAddress());

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();

        }


    }
}
