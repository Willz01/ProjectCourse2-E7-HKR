package se.hkr.e7.model;

import java.util.LinkedList;

public class Singleton {
    private static Singleton instance = null;

    private final LinkedList<String> sceneHistory;
    private Person currentUser;
    private Employee employee;
    private Patient patient;

    private Singleton() {
        sceneHistory = new LinkedList<>();
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void clear() {
        this.currentUser = null;
        this.employee = null;
        this.patient = null;
    }

    public void addSceneHistory(String path) {
        sceneHistory.addFirst(path);
    }

    public String getPreviousScene() {
        sceneHistory.removeFirst();
        return sceneHistory.removeFirst();
    }

    public Person getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Person currentUser) {
        this.currentUser = currentUser;
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
}

