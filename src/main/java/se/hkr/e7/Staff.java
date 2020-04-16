package se.hkr.e7;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Staff extends Person implements Serializable {
    private static final long serialVersionUID = 1L;


    @Column(name = "address", nullable = false)
    String address = getAddress();
    @Column(name = "name", nullable = false)
    String name = getName();
    @Column(name = "SSN", nullable = false, unique = true)
    String ssn = getSSN();
    @Column(name = "email", nullable = false)
    String email = getEmail();
    @ManyToOne(cascade = CascadeType.ALL)
    Admin admin;
    @Column(name = "phone", nullable = true)
    String phone = getPhone();
    @Id
    @Column(name = "staffID", unique = true)
    private int staffID;
    @Column(name = "salary", nullable = false)
    private double salary = getSalary();
    @Column(name = "password", nullable = false)
    private String password = getPassword();
    @Column(name = "location", nullable = true)
    private Location location;


    public Staff(int staffID, String name, String ssn, String email, double salary, String phone, String address, Location location, String password, Admin admin) {
        this.staffID = staffID;
        this.name = name;
        this.ssn = ssn;
        this.email = email;
        this.salary = salary;
        this.phone = phone;
        this.address = address;
        this.location = location;
        this.password = password;
        this.admin = admin;

    }
    public Staff() {


    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    @Override
    public String getName() {
        return name;
    }

    enum Location {
        Skane, Norrbottens, Vasterbottens, Jamtlands,
        Dalarnas, Vastmanlands, Uppsala, Stockholms, Kalmar
    }

    @Override
    public String toString() {
        return "Staff{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", ssn='" + ssn + '\'' +
                ", email='" + email + '\'' +
                ", admin=" + admin +
                ", phone='" + phone + '\'' +
                ", staffID=" + staffID +
                ", salary=" + salary +
                ", password='" + password + '\'' +
                ", location=" + location +
                '}';
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


    public static void addStaffToDataBase(Staff staff) {

        try (Session session = SQL.getSession()) {
            session.beginTransaction();
            session.save(staff);

            session.flush();
        }
    }
    public static void getStaffFromDataBase(int testID) {


        try (Session session = SQL.getSession()) {
            session.beginTransaction();

            Staff staff = (Staff) session.get(Staff.class, testID);
            System.out.println(staff.getAddress());
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();

        }

    }}
