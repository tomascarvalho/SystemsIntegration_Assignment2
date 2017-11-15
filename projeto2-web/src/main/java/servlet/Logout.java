package servlet;

import ejb.AuthenticationEJBRemote;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import data.*;


/**
 * Created by jorgearaujo on 14/11/2017.
 */
@WebServlet("/logout")
public class Logout extends HttpServlet{

    @EJB
    private AuthenticationEJBRemote authEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        if(session!=null && session.getAttribute("userId")!=null)
        {
            session.removeAttribute("userId");
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }
    }


}
