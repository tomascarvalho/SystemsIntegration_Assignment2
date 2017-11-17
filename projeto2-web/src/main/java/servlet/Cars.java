package servlet;

import ejb.CarEJBRemote;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import dto.CarDTO;
/**
 * Created by jorgearaujo on 15/11/2017.
 */
@WebServlet("/cars")
public class Cars extends HttpServlet {

    @EJB
    private CarEJBRemote carRemote;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (request.getParameter("brand") != null && request.getParameter("model") != null) {
            //TODO SEARCH BY BRAND AND MODEL
        } else if (request.getParameter("brand") != null) {
            //TODO SEARCH BY BRAND

        } else if (request.getParameter("price_from") != null) {
            //TODO SEARCH PRICE FROM ...
            if (request.getParameter("price_to") != null) {
                //TODO SEARCH PRICE BETWEEN PRICE_FROM AND PRICE_TO (MAKE SURE FROM < TO)
            }

        } else if (request.getParameter("price_to") != null) {
            //TODO SEARCH PRICE UP TO SOME VALUE
        } else if (request.getParameter("km_from") != null) {
            //TODO SEARCH KM FROM ...
            if (request.getParameter("km_to") != null) {
                //TODO SEARCH PRICE BETWEEN KM_FROM AND KM_TO (MAKE SURE FROM < TO)
            }

        } else if (request.getParameter("km_to") != null) {
            //TODO SEARCH KM UP TO SOME VALUE
        } else if (request.getParameter("year_to") != null) {
            //TODO SEARCH BY AGE
        } else {
            //Retrieve all cars
            List<CarDTO> allCars = carRemote.getAllCars();
            session.setAttribute("cars", allCars);
            response.sendRedirect(request.getContextPath() +"/cars.jsp");
        }


    }
}
