package se.hkr.e7;

import org.hibernate.Session;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class User implements Serializable {
    @Id
    @Column(unique = true)
    private String ssn;
    @Column(nullable = false)
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;

    public User() {
    }

    public User(String ssn, String password, String name, String email, String phone, String address) {
        this.ssn = ssn;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    static <T extends User> T load(String ssn, final Class<T> tClass) {
        Session session = SQL.getSession();
        session.beginTransaction();
        T person = session.get(tClass, ssn);
        session.getTransaction().commit();
        return person;
    }

    void save() {
        Session session = SQL.getSession();
        session.beginTransaction();
        session.saveOrUpdate(this);
        session.getTransaction().commit();
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "Person{" +
                "ssn='" + ssn + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
