package servlet;

import ejb.CarEJBRemote;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * Created by jorgearaujo on 14/11/2017.
 */
@WebServlet("/edit_car")
public class EditCar extends HttpServlet{

    @EJB
    private  CarEJBRemote carEJBRemote;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String mileage = request.getParameter("mileage");
        String month = request.getParameter("month");
        String year =  request.getParameter("year");
        String price = request.getParameter("price");
        long adverterId = (long) session.getAttribute("userId");
        long carId = Long.parseLong(request.getParameter("carID"));


        String result = carEJBRemote.updateCar(brand, model, mileage, month, year, price, adverterId, carId);
        System.out.println(result);
        if (result.equals("Success")) {
            session.setAttribute("success", result);
            response.sendRedirect(request.getContextPath() + "/home.jsp");
        }

        else{
            System.out.println("Error editing car"); // This has to be logged
            session.setAttribute("error", result);
            response.sendRedirect(request.getContextPath() + "/home.jsp");
        }
    }


}