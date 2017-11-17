package servlet;

import ejb.CarEJBRemote;
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
@WebServlet("/delete_car")
public class DeleteCar extends HttpServlet {

    @EJB
    private CarEJBRemote carEJBRemote;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        long carId = (long) session.getAttribute("id");

        boolean result = carEJBRemote.carDelete(carId);

        if (result) {
            session.removeAttribute("id");
            session.setAttribute("success", result);
            response.sendRedirect(request.getContextPath());
        }

        else{
            System.out.println("Error deleting car"); // This has to be logged
            session.setAttribute("error", result);
            response.sendRedirect(request.getContextPath());
        }
    }


}