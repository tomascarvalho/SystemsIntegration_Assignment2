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
@WebServlet("/login")
public class Login extends HttpServlet{

    @EJB
    private AuthenticationEJBRemote authEJB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Customer costumerToAuthenticate = null;
        HttpSession session = request.getSession();
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");

        costumerToAuthenticate = authEJB.getCostumer(email,password);
        if(costumerToAuthenticate!=null){
        session.setAttribute("userId", costumerToAuthenticate.getId());
        response.sendRedirect(request.getContextPath()+"/home.jsp");

        }else{
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            session.setAttribute("error", "email or password incorrect");
        }
    }


}
