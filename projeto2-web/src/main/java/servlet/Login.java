package servlet;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import ejb.CustomerEJBRemote;
import dto.CustomerDTO;


/**
 * Created by jorgearaujo on 14/11/2017.
 */
@WebServlet("/login")
public class Login extends HttpServlet{

    @EJB
    private CustomerEJBRemote authEJB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");

        CustomerDTO customerToAuthenticate = authEJB.readCustomer(email,password);

        if(customerToAuthenticate != null){
            session.setAttribute("userId",customerToAuthenticate.getId());
            session.setAttribute("user", customerToAuthenticate);
            response.sendRedirect(request.getContextPath()+"/home.jsp");

        }else{
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            session.setAttribute("error", "Email or password incorrect");
        }
    }


}
