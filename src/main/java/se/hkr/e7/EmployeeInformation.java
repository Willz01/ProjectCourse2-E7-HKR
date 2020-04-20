package se.hkr.e7;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmployeeInformation {

    private Long id;
    private double salary;
    private Location location;

    public EmployeeInformation(double salary, Location location) {
        this.salary = salary;
        this.location = location;
    }

    public EmployeeInformation() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "EmployeeInformation{" +
                "id=" + id +
                ", salary=" + salary +
                ", location=" + location +
                '}';
    }
}

enum Location {
    Skane, Norrbottens, Vasterbottens, Jamtlands,
    Dalarnas, Vastmanlands, Uppsala, Stockholms, Kalmar
}