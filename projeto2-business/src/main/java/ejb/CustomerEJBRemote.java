/**
 * Created by jorgearaujo on 15/11/2017.
 */
package ejb;

import data.Customer;

import javax.ejb.Remote;


@Remote
public interface CustomerEJBRemote {
    boolean createCustomerAccount(String email, String Password);
    Customer readCustomer(String email, String password);

}