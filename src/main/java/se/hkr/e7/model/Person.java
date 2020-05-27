package se.hkr.e7.model;

import org.mindrot.jbcrypt.BCrypt;
import se.hkr.e7.DatabaseHandler;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person implements Serializable {
    private String ssn;
    private String password;
    private boolean enabled;
    private String name;
    private String email;
    private String phone;
    private String address;

    public Person() {
    }

    public Person(String ssn, String password, String name, String email, String phone, String address) {
        if (!isValidSsn(ssn)) {
            throw new IllegalArgumentException("The SSN is not valid");
        } else {
            if (ssn.length() == 10) {
                setSsn(ssn);
            } else {
                setSsn(ssn.substring(2));
            }
        }

        updatePassword(password);
        this.enabled = true;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public static <T extends Person> T load(Class<T> tClass, String ssn) {
        if (ssn.length() == 12) {
            ssn = ssn.substring(2);
        }
        return DatabaseHandler.load(tClass, ssn);
    }

    public static boolean isValidSsn(String ssn) {
        // TODO: 29.04.20 Implement checksum calculation https://en.wikipedia.org/wiki/Personal_identity_number_(Sweden)#Checksum
        if (!ssn.matches("^(\\d{2})?\\d{10}$")) {
            return false;
        }

        if (ssn.length() == 12) {
            ssn = ssn.substring(2);
        }

        if (Integer.parseInt(ssn.substring(2, 4)) > 12) {
            return false;
        }

        return Integer.parseInt(ssn.substring(4, 6)) <= 31;
    }

    /**
     * The password must be at least 8 characters. If it is fewer
     * than 14 characters in length, it must contain at least one
     * lowercase and one uppercase letter and at least one number.
     * If it is at least 14 characters in length, any character
     * combination is allowed.
     *
     * @param password The password that should be checked against
     *                 the minimum complexity requirements
     * @return True if the password is complex enough and false if it isn't
     * <p>
     * (?=.*[0-9]) a digit must occur at least once
     * (?=.*[a-z]) a lower case letter must occur at least once
     * (?=.*[A-Z]) an upper case letter must occur at least once
     * (?=\\S+$) no whitespace allowed in the entire string
     */
    public static boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        if (password.length() < 14) {
            return password.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).*");
        }

        return true;
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
    }

    public static boolean isValidPhone(String phoneNumber) {
        return phoneNumber.matches("^[0-9\\-\\+]{9,15}$");
    }

    public static boolean isValidSalary(String salary) {
        if (!salary.matches("^[0-9]+\\.?[0-9]*$")) {
            return false;
        }

        return isValidSalary(Double.parseDouble(salary));
    }

    public static boolean isValidSalary(double salary) {
        return salary >= 0;
    }

    public void clear() {
        setPassword("");
        setEnabled(false);
        setName(null);
        setEmail(null);
        setPhone(null);
        setAddress(null);
    }

    /**
     * Hashes the password and updates the Person object.
     *
     * @param password The new password of the user
     * @throws IllegalArgumentException is thrown when the password does not meet the minimum requirements
     */
    public void updatePassword(String password) {
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("The password does not meet the minimum requirements");
        }

        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password) {
        if (password.length() == 0 || this.password.length() == 0) {
            return false;
        }
        return BCrypt.checkpw(password, this.password);
    }

    @Id
    @Column(unique = true)
    public String getSsn() {
        return ssn;
    }

    private void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "ssn='" + ssn + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
