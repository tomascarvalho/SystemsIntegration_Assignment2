package ejb;

import data.Customer;
import security.BCrypt;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jorgearaujo on 15/11/2017.
 */
@Stateless
public class CustomerEJB implements CustomerEJBRemote{
    @PersistenceContext(name="Cars")
    EntityManager em;

    public boolean createCustomerAccount(String email, String Password)
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



    // get costumer with email and password hash
    public long readCustomer(String email, String password)
    {
        try{
            Query newQuery = em.createQuery(" FROM Customer cost where cost.email=?1");
            newQuery.setParameter(1, email);
            System.out.println(email);
            Customer costumerToAuth = (Customer) newQuery.getSingleResult();
            if(BCrypt.checkpw(password,costumerToAuth.getPasswordHash()) == true)
            {
                return costumerToAuth.getId();
            }


        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return -1;

    }

    public String hashPassword(String password)
    {
        String hashed =  BCrypt.hashpw(password,BCrypt.gensalt());
        return hashed;
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public String updateCustomerAccount(String email, String password, String newPassword, String confirmPassword,
                                         String firstName, String lastName, long uid) {

        System.out.println("updateCustomerAccount");

        String response = "";

        Customer toUpdate = (Customer) em.find(Customer.class, uid);
        if (toUpdate == null) {
            response = "Something went wrong!";
            return response;
        }
        System.out.println("Found Customer: " + toUpdate.toString());

        if (email != null && !email.equals(toUpdate.getEmail())) {
            if (validate(email)) {
                toUpdate.setEmail(email);
                response = "Success";
            } else {
                return (response = "Invalid Email");
            }
        }
        if(password != null && newPassword != null && confirmPassword != null && BCrypt.checkpw(password,toUpdate.getPasswordHash()) == true) {
            if (newPassword.equals(confirmPassword) && newPassword.length()< 6) {
                toUpdate.setPasswordHash(hashPassword(newPassword));
                response = "Success";
            } else {
                return (response = "Invalid password");
            }
        }

        if (firstName != null && !firstName.equals(toUpdate.getFirstName())) {
            if (firstName.length() >= 2) {
                toUpdate.setFirstName(firstName);
                response = "Success";
            } else {
                return (response = "Invalid First Name");
            }
        }

        if (lastName != null && !lastName.equals(toUpdate.getLastName())) {
            if (lastName.length() >= 2) {
                toUpdate.setLastName(lastName);
                response = "Success";
            } else {
                return (response = "Invalid Last Name");
            }
        }
        return response;

    }

}
