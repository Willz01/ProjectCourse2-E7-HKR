package se.hkr.e7.model;

import java.util.LinkedList;

public class Singleton {

    private static Singleton instance = null;
    private Person currentUser;
    private Employee employee;
    private LinkedList<String> sceneHistory;
    private Patient patient;
    private Result result;
    private String ssn;

    private Singleton() {
        sceneHistory = new LinkedList<>();
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void addSceneHistory(String path) {
        sceneHistory.addFirst(path);
    }

    public String getPreviousScene() {
        sceneHistory.removeFirst();
        return sceneHistory.removeFirst();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Person getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Person currentUser) {
        this.currentUser = currentUser;
    }
}

