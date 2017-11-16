package servlet;

import ejb.CarEJBRemote;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import dto.CarDTO;
/**
 * Created by jorgearaujo on 15/11/2017.
 */
@WebServlet("/car")
public class ViewCarDetails extends HttpServlet {

    @EJB
    private CarEJBRemote carRemote;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        long carID = Long.parseLong(request.getParameter("id"));
        CarDTO carDTO = carRemote.readCar(carID);
        if(carDTO != null)
        {
            session.setAttribute("car", carDTO);
            response.sendRedirect(request.getContextPath() +"/car.jsp");
        }
        else
        {
            response.sendRedirect(request.getContextPath()+"/404.jsp");

        }
    }
}
