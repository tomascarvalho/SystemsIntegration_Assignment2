/**
 * Created by jorgearaujo on 15/11/2017.
 */
package ejb;

import data.Customer;
import dto.CustomerDTO;

import javax.ejb.Remote;


@Remote
public interface CustomerEJBRemote {
    boolean createCustomerAccount(String email, String Password);
    CustomerDTO readCustomer(String email, String password);
    String updateCustomerAccount(String email, String password, String newPassword, String confirmPassword,
                                         String firstName, String lastName, long uid);
    Customer readCustomerById(long id);


}
