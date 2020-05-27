package se.hkr.e7;

import se.hkr.e7.model.Person;
import se.hkr.e7.model.Result;

import java.util.LinkedList;

public class Singleton {
    private static Singleton instance = null;

    private final LinkedList<String> sceneHistory;
    private Person currentUser;
    private Person person;
    private Result result;

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
        sceneHistory.clear();
    }

    public void addSceneHistory(String path) {
        sceneHistory.addFirst(path);
    }

    /**
     * This method will remove the current scene from the history.
     * It will then remove and return the previous scene. This
     * scene will be re-added when the previous scene is loaded.
     *
     * @return The location of the previous scene's FXML file
     */
    public String getPreviousScene() {
        sceneHistory.removeFirst();
        return sceneHistory.removeFirst();
    }

    /**
     * This method returns the current scene without changing the history.
     *
     * @return The location of the current scene's FXML file
     */
    public String getCurrentScene() {
        return sceneHistory.getFirst();
    }

    public Person getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Person currentUser) {
        this.currentUser = currentUser;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
