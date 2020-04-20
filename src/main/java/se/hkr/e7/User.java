package se.hkr.e7;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

enum Role {
    ADMIN, ANALYSER, DOCTOR, PATIENT
}

@Entity
public class User extends Database implements Serializable {
    private String ssn;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Role role;
    private EmployeeInformation employeeInformation;
    private List<Result> results;

    public User() {
    }

    public User(String ssn, String password, String name, String email, String phone, String address,
                Role role, EmployeeInformation employeeInformation) {
        super();
        this.ssn = ssn;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.employeeInformation = employeeInformation;
        this.results = new ArrayList<>();
    }

    public void addResult(Result result) {
        results.add(result);
        result.setPatient(this);
    }

    @Id
    @Column(unique = true)
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Column(nullable = false)
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

    @Column(nullable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    public EmployeeInformation getEmployeeInformation() {
        return employeeInformation;
    }

    public void setEmployeeInformation(EmployeeInformation employeeInformation) {
        this.employeeInformation = employeeInformation;
    }

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "User{" +
                "ssn='" + ssn + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", role=" + role +
                ", employeeInformation=" + employeeInformation +
                ", results=" + results +
                '}';
    }
}