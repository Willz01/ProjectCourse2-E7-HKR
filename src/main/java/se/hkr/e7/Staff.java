package se.hkr.e7;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Staff extends User {

    @Column(nullable = false)
    private double salary;
    private Location location;

    public Staff(String name, String ssn, String email, double salary, String phone, String address, Location location, String password) {
        super(ssn, password, name, email, phone, address);
        this.salary = salary;
        this.location = location;
    }

    public Staff() {
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
    public String toString() {
        return "Staff{" +
                "salary=" + salary +
                ", location=" + location +
                "} " + super.toString();
    }

    enum Location {
        Skane, Norrbottens, Vasterbottens, Jamtlands,
        Dalarnas, Vastmanlands, Uppsala, Stockholms, Kalmar
    }
}
