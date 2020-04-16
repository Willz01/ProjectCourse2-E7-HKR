package se.hkr.e7;

public class Singleton {

    private static Singleton instance=null;

    private Singleton() {

    }


    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    private String ssn;


    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}

