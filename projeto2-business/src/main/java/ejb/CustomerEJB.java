package ejb;

import data.Customer;
import security.BCrypt;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    public Customer readCustomer(String email, String password)
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

    // get costumer with email and password hash
    public Customer readCustomerById(long id)
    {
        try{
            Query newQuery = em.createQuery(" FROM Customer cost where cost.id=?1");
            newQuery.setParameter(1, id);
            Customer customer = (Customer) newQuery.getSingleResult();

                return customer;


        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;

    }

    public String hashPassword(String password)
    {
        String hashed =  BCrypt.hashpw(password,BCrypt.gensalt());
        return hashed;
    }

}
