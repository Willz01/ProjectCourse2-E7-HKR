package se.hkr.e7;

import javax.persistence.Entity;

@Entity
public class Patient extends User {

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
