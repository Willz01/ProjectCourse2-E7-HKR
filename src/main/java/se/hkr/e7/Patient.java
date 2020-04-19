package se.hkr.e7;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Patient extends Person {

    public Patient(String ssn, String password, String name, String email, String phone, String address) {
        super(ssn, password, name, email, phone, address);
    }

    public Patient() {
    }

    @Override
    public String toString() {
        return "Patient{} " + super.toString();
    }
}
