package servlet;

import dto.CustomerDTO;
import ejb.CarEJBRemote;


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
@WebServlet("/follow_car")
public class FollowCar extends HttpServlet {

    @EJB
    private CarEJBRemote carEJBRemote;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        session.removeAttribute("error");
        session.removeAttribute("success");

        long carId = Long.parseLong(request.getParameter("carID"));
        CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("user");
        String result  = carEJBRemote.followCar(carId, customerDTO.getId());

        if (result == "Success") {
            session.setAttribute("success", result + ", following car!");
            response.sendRedirect(request.getContextPath()+"/car?id="+carId);
        }

        else{
            System.out.println("Error following car"); // This has to be logged
            session.setAttribute("error", result);
            response.sendRedirect(request.getContextPath()+"/car?id="+carId);
        }
    }


}