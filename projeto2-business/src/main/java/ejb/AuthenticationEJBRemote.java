package ejb;

import data.Customer;

import javax.ejb.Remote;

/**
 * Created by jorgearaujo on 14/11/2017.
 */
@Remote
public interface AuthenticationEJBRemote {
     boolean createCostumerAccount(String email, String Password);
    Customer getCostumer(String email, String password);

}
