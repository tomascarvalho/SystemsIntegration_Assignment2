package servlet;

import ejb.CustomerEJBRemote;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * Created by jorgearaujo on 14/11/2017.
 */
@WebServlet("/edit_profile")
public class EditProfile extends HttpServlet{

    @EJB
    private CustomerEJBRemote customerEJB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        String newPassword = (String) request.getParameter("newPassword");
        String confirmPassword = (String) request.getParameter("confirmPassword");
        String firstName = (String) request.getParameter("firstName");
        String lastName = (String) request.getParameter("lastName");
        long uid = (long) session.getAttribute("userId");

        String result = customerEJB.updateCustomerAccount(email, password, newPassword, confirmPassword, firstName, lastName, uid);

        if (result.equals("Success")) {
            session.setAttribute("success", result);
            response.sendRedirect(request.getContextPath() + "/profile.jsp");
        }

        else{
            System.out.println("Error creating user"); // This has to be logged
            session.setAttribute("error", result);
        }
    }


}