package data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

/**
 * Created by jorgearaujo on 14/11/2017.
 */
@Entity
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue
    long id;
    @Column(unique=true, nullable=false)
    private String email;
    @OneToMany(mappedBy="customer",fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Car> cars;
    private String firstName;
    private String lastName;
    private String passwordHash;

    public Customer(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public Customer(String email, String firstName, String lastName, String passwordHash) {

        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwordHash = passwordHash;
    }


    public Customer() {
    }

    public Customer(String username, String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Transactional
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " email: " + email  + " id: " + id;
    }
}
