package servlet;

import ejb.AuthenticationEJBRemote;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * Created by jorgearaujo on 14/11/2017.
 */
@WebServlet("/edit")
public class EditProfile extends HttpServlet{

    @EJB
    private AuthenticationEJBRemote authEJB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        String newPassword = (String) request.getParameter("newPassword");
        String confirmPassword = (String) request.getParameter("confirmPassword");
        String firstName = (String) request.getParameter("firstName");
        String lastName = (String) request.getParameter("lastName");

        if (authEJB.updateCostumerAccount(email, password, newPassword, confirmPassword, firstName, lastName)) {

            session.setAttribute("success", "Dados alterados com sucesso!");
            response.sendRedirect(request.getContextPath() + "/profile.jsp");
        }
        else{
            System.out.println("Error creating user"); // This has to be logged
            session.setAttribute("error", "Erro ao alterar dados");
        }
    }


}
