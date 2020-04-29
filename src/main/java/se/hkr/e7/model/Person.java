package se.hkr.e7.model;

import org.mindrot.jbcrypt.BCrypt;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person implements Serializable {
    private String ssn;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;

    public Person() {
    }

    public Person(String ssn, String password, String name, String email, String phone, String address) {
        this.ssn = ssn;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        updatePassword(password);
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

        if (Integer.parseInt(ssn.substring(4, 6)) > 31) {
            return false;
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

    public void updatePassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password) {
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
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


}
