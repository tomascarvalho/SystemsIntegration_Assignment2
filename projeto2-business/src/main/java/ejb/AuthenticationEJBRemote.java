package ejb;

import javax.ejb.Remote;

/**
 * Created by jorgearaujo on 14/11/2017.
 */
@Remote
public interface AuthenticationEJBRemote {
    public boolean createUserAccount(String email, String Password);

}
