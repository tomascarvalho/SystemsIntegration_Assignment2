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
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");


        if(authEJB.createCustomerAccount(email,password)==true){

        response.sendRedirect(request.getContextPath()+"/login.jsp");
            System.out.println("USER CREATED");

        }
        else{
            System.out.println("Error creating user");
        }
    }


}
