package se.hkr.e7;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person extends Database {
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

//        if (ssn.matches("(20|19)+(01|02|03|04|05|06|07|08|09|10|11|12{2})+(01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31{2})+[0-9]{4}"))
            this.ssn = ssn;
//        else {
//            System.out.println("wrong regex format ");
//        }
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
