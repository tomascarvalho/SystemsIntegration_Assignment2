package servlet;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import ejb.CustomerEJBRemote;


/**
 * Created by jorgearaujo on 14/11/2017.
 */
@WebServlet("/login")
public class Login extends HttpServlet{

    @EJB
    private CustomerEJBRemote authEJB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        long customerToAuthenticate = 0;
        HttpSession session = request.getSession();
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");

        customerToAuthenticate = authEJB.readCustomer(email,password);

        if(customerToAuthenticate > 0){
            session.setAttribute("userId",customerToAuthenticate );
            response.sendRedirect(request.getContextPath()+"/home.jsp");

        }else{
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            session.setAttribute("error", "email or password incorrect");
        }
    }


}
