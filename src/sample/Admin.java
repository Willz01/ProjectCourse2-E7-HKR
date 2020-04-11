package sample;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Admin extends Person implements Serializable {
    private static final long serialVersionUID = 1L;


//    @ManyToMany(cascade = CascadeType.ALL)
//    List<Staff> staff = new ArrayList<>();
    @Id
    @Column(name = "adminID", unique = true)
    private String adminID = getAdminID();
    @Column(name = "password", nullable = false)
    private String Password = getPassword();
    @Column(name = "name", nullable = true)
    String name = getName();
    @Column(name = "SSN", nullable = true)
    String ssn = getSsn();
    @Column(name = "phone", nullable = true)
    String phone = getPhone();
    @Column(name = "address", nullable = true)
    String address = getAddress();
    @Column(name = "email", nullable = false)
    String email = getEmail();
    @Column(name = "startDate", nullable = true)
    private String startDate = getStartDate();

    public Admin(String name, String ssn, String phone, String address, String email, String adminID, String password, String startDate) {
        this.name = name;
        this.ssn = ssn;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.adminID = adminID;
        password = password;
        this.startDate = startDate;
    }


    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
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

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", ssn='" + ssn + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", adminID=" + adminID +
                ", Password='" + Password + '\'' +
                ", startDate='" + startDate + '\'' +
                '}';
    }
}
