package se.hkr.e7;

import org.hibernate.Session;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Analyser extends Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "analyserId", unique = true)
    int analyserId = getAnalyserId();
    @Column(name = "name", nullable = false)
    String name = getName();
    @Column(name = "SSN", nullable = false, unique = true)
    String ssn = getSSN();
    @Column(name = "email", nullable = false)
    String email = getEmail();
    @Column(name = "phone", nullable = true)
    String phone = getPhone();
    @Column(name = "address", nullable = false)
    String address = getAddress();
    @ManyToOne(cascade = CascadeType.ALL)
    Admin admin;
    @Column(name = "salary", nullable = false)
    private double salary = getSalary();
    @Column(name = "password", nullable = false)
    private String password = getPassword();
    @Column(name = "startingDate", nullable = false)
    private String startingDate = getStartingDate();
    @Column(name = "lastDayInContract", nullable = true)
    private String lastDayInContract = getLastDayInContract();

    public Analyser(int analyserId, String name, String ssn, String email, double salary, String phone, String address, String password, String startingDate, String lastDayInContract, Admin admin) {
        this.analyserId = analyserId;
        this.name = name;
        this.ssn = ssn;
        this.email = email;
        this.salary = salary;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.startingDate = startingDate;
        this.lastDayInContract = lastDayInContract;
        this.admin = admin;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void addAnalyserToDataBase(Analyser analyser) {

        try (Session session = SQL.getSession()) {
            session.beginTransaction();
            session.save(analyser);

            session.flush();
        }
    }

    public int getAnalyserId() {
        return analyserId;
    }

    public void setAnalyserId(int analyserId) {
        this.analyserId = analyserId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getLastDayInContract() {
        return lastDayInContract;
    }

    public void setLastDayInContract(String lastDayInContract) {
        this.lastDayInContract = lastDayInContract;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }


}
