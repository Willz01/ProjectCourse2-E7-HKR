package se.hkr.e7.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient extends Person {

    private List<Result> testResults;

    public Patient() {
    }

    public Patient(String ssn, String password, String name, String email, String phone, String address) {
        super(ssn, password, name, email, phone, address);
        this.testResults = new ArrayList<>();
    }

    public static Patient load(String ssn) {
        return load(Patient.class, ssn);
    }

    public void addTestResult(Result result) {
        this.testResults.add(result);
    }

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.LAZY,
            mappedBy = "patient"
    )
    public List<Result> getTestResults() {
        return testResults;
    }

    private void setTestResults(List<Result> testResults) {
        this.testResults = testResults;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "testResults=" + testResults +
                "} " + super.toString();
    }
}
