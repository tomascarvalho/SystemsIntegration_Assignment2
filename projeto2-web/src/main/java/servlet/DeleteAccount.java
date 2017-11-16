package servlet;

import ejb.CustomerEJBRemote;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by jorgearaujo on 16/11/2017.
 */
@WebServlet("/delete_account")
public class DeleteAccount extends HttpServlet {

    @EJB
    private CustomerEJBRemote customerEJB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        long uid = (long) session.getAttribute("userId");

        boolean result = customerEJB.deleteCustomer(uid);

        if (result) {
            session.removeAttribute("userId");
            session.setAttribute("success", result);
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }

        else{
            System.out.println("Error deleting user"); // This has to be logged
            session.setAttribute("error", result);
            response.sendRedirect(request.getContextPath() + "/profile.jsp");
        }
    }


}