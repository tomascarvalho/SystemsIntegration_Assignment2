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
        String brand = request.getParameter("brand");
        String model = request.getParameter("mode");
        String price_from = request.getParameter("price_from");
        String price_to = request.getParameter("price_to");
        String km_from = request.getParameter("mileage_from");
        String km_to = request.getParameter("mileage_to");
        String year_since = request.getParameter("year_since");
        String str_option = (request.getParameter("option"));
        int option = 0;
        if (str_option != null) {
            option = Integer.parseInt(str_option);
        }

        if (brand!= null && model != null && !brand.isEmpty() && !model.isEmpty() && option==2) {
            List <CarDTO> getAllCars = carRemote.getCarsByBrandModel(brand, model);
            session.setAttribute("cars", getAllCars);
            response.sendRedirect(request.getContextPath() +"/cars.jsp");

        } else if (brand != null && !brand.isEmpty() && option==1) {
            List <CarDTO> getAllCars = carRemote.getCarsByBrand(brand);
            session.setAttribute("cars", getAllCars);
            response.sendRedirect(request.getContextPath() +"/cars.jsp");

        } else if (price_from != null && !price_from.isEmpty()&& option==3) {
            if (!price_to.isEmpty()) {
                List <CarDTO> getAllCars = carRemote.getCarsByPrice(Integer.parseInt(price_from), Integer.parseInt(price_to));
                session.setAttribute("cars", getAllCars);
                response.sendRedirect(request.getContextPath() +"/cars.jsp");
            }

        } else if (km_from != null && !km_from.isEmpty() && option==4) {
            if (!price_to.isEmpty()) {
                List <CarDTO> getAllCars = carRemote.getCarsByKm(Integer.parseInt(km_from), Integer.parseInt(km_to));
                session.setAttribute("cars", getAllCars);
                response.sendRedirect(request.getContextPath() +"/cars.jsp");
            }

        } else if (year_since != null && !year_since.isEmpty() && option==5) {
            List <CarDTO> getAllCars = carRemote.getCarsNewerThan(Integer.parseInt(year_since));
            session.setAttribute("cars", getAllCars);
            response.sendRedirect(request.getContextPath() +"/cars.jsp");
        } else {
            //Retrieve all cars
            List<CarDTO> allCars = carRemote.getAllCars();
            session.setAttribute("cars", allCars);
            response.sendRedirect(request.getContextPath() +"/cars.jsp");
        }


    }
}
