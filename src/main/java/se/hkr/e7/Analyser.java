package se.hkr.e7;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Analyser extends Person {

    @Column(nullable = false)
    private double salary = getSalary();
    @Column(nullable = false)
    private String startingDate = getStartingDate();
    private String lastDayInContract = getLastDayInContract();

    public Analyser(String name, String ssn, String email, double salary, String phone, String address, String password, String startingDate, String lastDayInContract) {
        super(ssn, password, name, email, phone, address);
        this.salary = salary;
        this.startingDate = startingDate;
        this.lastDayInContract = lastDayInContract;
    }

    public Analyser() {
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
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

    @Override
    public String toString() {
        return "Analyser{" +
                "salary=" + salary +
                ", startingDate='" + startingDate + '\'' +
                ", lastDayInContract='" + lastDayInContract + '\'' +
                "} " + super.toString();
    }
}
