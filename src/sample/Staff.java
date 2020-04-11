package sample;

import org.hibernate.Session;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Staff extends Person implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "staffID", unique = true)
    private String staffID;
    @Column(name = "name", nullable = false)
    String name = getName();
    @Column(name = "SSN", nullable = false)
    String ssn = getSSN();
    @Column(name = "adminID", nullable = true)
    private String adminId = getAdminId();
    @Column(name = "email", nullable = false)
    String email = getEmail();
    @Column(name = "salary", nullable = false)
    private long salary = getSalary();
    @Column(name = "phone", nullable = true)
    String phone = getPhone();
    @Column(name = "address", nullable = true)
    String address = getAddress();
    @Column(name = "location", nullable = false)
    private Location location;
    @Column(name = "password", nullable = false)
    private String password = getPassword();


    public Staff(String name, String ssn, String phone, String address, String email, String staffID,
                 String password, long salary, Location location, String adminId) {
        this.staffID = staffID;
        this.name = name;
        this.ssn = ssn;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.salary = salary;
        this.location = location;
        this.adminId = adminId;

    }


    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
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


    @Override
    public String toString() {
        return "Staff{" +
                "Password='" + password + '\'' +
                ", staffID=" + staffID +
                ", salary=" + salary +
                ", location=" + location +
                ", admin=" + adminId +
                ", name='" + name + '\'' +
                ", ssn='" + ssn + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


    public static void addStaffToDataBase(Staff staff) {

        try (Session session = SQL.getSession()) {
            session.beginTransaction();
            session.save(staff);

            session.flush();
        }
    }


}
