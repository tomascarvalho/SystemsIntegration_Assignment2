package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import data.Customer;

/**
 * Created by jorgearaujo on 14/11/2017.
 */
@Stateless
public class AuthenticationEJB implements AuthenticationEJBRemote{
    @PersistenceContext(name="Adverter")
    EntityManager em;

    public AuthenticationEJB() {
    }

    public boolean createUserAccount(String email, String Password)
    {
        try{
        Customer newCustomer = null;
        byte [] hashedPass = hashPassword(Password);
        newCustomer = new Customer(email,hashedPass);
        em.persist(newCustomer);
        return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Error creating user.");
            return false;
        }

    }


    // hash passwords funtion
    private byte[] hashPassword(String Password)
    {
        try {

            MessageDigest messageDig = MessageDigest.getInstance("SHA-256");
            byte[] hashedPass = messageDig.digest(Password.getBytes(StandardCharsets.UTF_8));
            return hashedPass;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;

        }
    }
}
