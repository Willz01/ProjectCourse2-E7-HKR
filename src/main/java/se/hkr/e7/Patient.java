package se.hkr.e7;

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

    public void addTestResult(Result result) {
        this.testResults.add(result);
    }

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "patient",
            orphanRemoval = true
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
