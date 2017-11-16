package data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by jorgearaujo on 14/11/2017.
 */
@Entity
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy="uuid")
    private String id;
    @Column(unique=true, nullable=false)
    private String email;
    @OneToMany(mappedBy="customer")
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public String toString() {
        return firstName + " " + lastName + " email: " + email  + " id: " + id;
    }
}
