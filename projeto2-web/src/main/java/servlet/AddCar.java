package servlet;

import data.Customer;
import ejb.CarEJB;
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
 * Created by jorgearaujo on 15/11/2017.
 */
@WebServlet("/addCar")
public class AddCar extends HttpServlet {

    @EJB
    private CustomerEJBRemote authEJB;
    @EJB
    private CarEJBRemote carRemote;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        int mileage = Integer.parseInt(request.getParameter("mileage"));
        String month = request.getParameter("month");
        int year =  Integer.parseInt(request.getParameter("year"));
        int price = Integer.parseInt(request.getParameter("price"));
        long adverterId = (long) session.getAttribute("userId");
        if(carRemote.createCar(brand,model,mileage,month,year,price,adverterId))
        {
            session.setAttribute("notification", "Car Adverted Successfully");
            response.sendRedirect(request.getContextPath() +"/addcar.jsp");

        }
        else
            {
                session.setAttribute("notification", "Advert not inserted successfully");
                response.sendRedirect(request.getContextPath()+"/addcar.jsp");

            }
    }
}
