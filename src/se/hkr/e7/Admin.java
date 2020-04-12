package se.hkr.e7;

import org.hibernate.Session;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Admin extends Person implements Serializable {
    private static final long serialVersionUID = 1L;




    @Id
    @Column(name = "adminID", unique = true)
    private int adminID = getAdminID();
    @Column(name = "name", nullable = true)
    private String name = getName();
    @Column(name = "SSN", nullable = false)
    private String ssn = getSsn();
    @Column(name = "phone", nullable = true)
    private String phone = getPhone();
    @Column(name = "address", nullable = true)
    private String address = getAddress();
    @Column(name = "email", nullable = false)
    private String email = getEmail();
    @Column(name = "password", nullable = false)
    private String Password = getPassword();
    @Column(name = "startingDate", nullable = false)
    private String startingDate = getStartingDate();
    @Column(name = "lastDayInContract", nullable = true)
    private String lastDayInContract = getLastDayInContract();

    public Admin(int adminID, String name, String ssn, String phone, String address, String email, String password, String startingDate, String lastDayInContract) {
        this.adminID = adminID;
        this.name = name;
        this.ssn = ssn;
        this.phone = phone;
        this.address = address;
        this.email = email;
        Password = password;
        this.startingDate = startingDate;
        this.lastDayInContract = lastDayInContract;

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getAdminID() {
        return adminID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
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


    public static void addAdminToDataBase(Admin admin) {


        try (Session session = SQL.getSession()) {
            session.beginTransaction();

            session.save(admin);

            session.flush();
        }


    }


}