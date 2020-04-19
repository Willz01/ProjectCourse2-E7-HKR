package se.hkr.e7;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Admin extends Person {

    @Column(nullable = false)
    private String startingDate;
    private String lastDayInContract;

    public Admin(String startingDate, String lastDayInContract, String ssn, String password, String name, String email, String phone, String address) {
        super(ssn, password, name, email, phone, address);
        this.startingDate = startingDate;
        this.lastDayInContract = lastDayInContract;
    }

    public Admin() {
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
        return "Admin{" +
                "startingDate='" + startingDate + '\'' +
                ", lastDayInContract='" + lastDayInContract + '\'' +
                "} " + super.toString();
    }
}