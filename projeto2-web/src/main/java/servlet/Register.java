package servlet;

import data.Customer;
import ejb.CustomerEJBRemote;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * Created by jorgearaujo on 14/11/2017.
 */
@WebServlet("/register")
public class Register extends HttpServlet{

    @EJB
    private CustomerEJBRemote authEJB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        String firstName = (String) request.getParameter("firstName");
        String lastName = (String) request.getParameter("lastName");
        System.out.println("CALLING CREATE USER WITH: EMAIL: " + email + " password: " + password + " firstName: " + firstName + " lastName: "+ lastName);
        String result = authEJB.createCustomerAccount(email, password, firstName, lastName);

        if(result.equals("Success")){
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            session.setAttribute("error", result);
        }
        else{
            response.sendRedirect(request.getContextPath()+"/register.jsp");
            session.setAttribute("error", result);
            System.out.println("Error creating user");
        }
    }


}
