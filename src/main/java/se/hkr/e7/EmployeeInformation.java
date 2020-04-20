package se.hkr.e7;

public class EmployeeInformation {

    private double salary;
    private Location location;

    public EmployeeInformation(double salary, Location location) {
        this.salary = salary;
        this.location = location;
    }

    public EmployeeInformation() {}
}

enum Location {
    Skane, Norrbottens, Vasterbottens, Jamtlands,
    Dalarnas, Vastmanlands, Uppsala, Stockholms, Kalmar
}