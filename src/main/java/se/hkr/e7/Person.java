package se.hkr.e7;

import org.hibernate.Session;

public class Person {


    private String name;
    private String SSN;
    private String phone;
    private String address;
    private String email;

    static <T extends Person> T get(int id, final Class<T> tClass) {
        Session session = SQL.getSession();
        session.beginTransaction();
        T person = session.get(tClass, id);
        session.getTransaction().commit();
        return person;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "SSN='" + SSN + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
