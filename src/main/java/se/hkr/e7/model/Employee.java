package se.hkr.e7.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee extends Person {

    private Location location;
    private Role role;
    private double salary;
    private List<Result> patientResults;

    public Employee() {
    }

    public Employee(String ssn, String password, String name, String email, String phone, String address,
                    Location location, Role role, double salary) {
        super(ssn, password, name, email, phone, address);
        this.location = location;
        this.role = role;
        this.salary = salary;
        this.patientResults = new ArrayList<>();
    }

    public void clear() {
        super.clear();
        setLocation(null);
        setRole(null);
        setSalary(0);
    }

    public void addPatientResult(Result result) {
        this.patientResults.add(result);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.LAZY,
            mappedBy = "examiner"
    )
    public List<Result> getPatientResults() {
        return patientResults;
    }

    private void setPatientResults(List<Result> patientResults) {
        this.patientResults = patientResults;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "location=" + location +
                ", role=" + role +
                ", salary=" + salary +
                ", patientResults=" + patientResults +
                "} " + super.toString();
    }

    public enum Role {
        ADMIN, ANALYSER, DOCTOR
    }
}