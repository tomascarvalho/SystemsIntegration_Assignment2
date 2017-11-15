package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sun.org.apache.bcel.internal.util.BCELifier;
import data.Customer;
import security.BCrypt;


/**
 * Created by jorgearaujo on 14/11/2017.
 */
@Stateless
public class AuthenticationEJB implements AuthenticationEJBRemote{
    @PersistenceContext(name="Cars")
    EntityManager em;

    public AuthenticationEJB() {
    }

    public boolean createCostumerAccount(String email, String Password)
    {
        try{
        Customer newCustomer = null;
        String hashedPass = hashPassword(Password);
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


    // hash passwords function
    private String hashPassword(String password)
    {
            String hashed =  BCrypt.hashpw(password,BCrypt.gensalt());
            return hashed;
    }

    // get costumer with email and password hash
    public Customer getCostumer(String email, String password)
    {
        try{
            Query newQuery = em.createQuery(" FROM Customer cost where cost.email=?1");
            newQuery.setParameter(1, email);
            System.out.println(email);
            Customer costumerToAuth = (Customer) newQuery.getSingleResult();
            if(BCrypt.checkpw(password,costumerToAuth.getPasswordHash()) == true)
            {
                return costumerToAuth;
            }


        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;

    }
}
