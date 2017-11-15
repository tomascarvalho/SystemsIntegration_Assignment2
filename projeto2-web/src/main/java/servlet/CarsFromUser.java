package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Car;
import ejb.CarsEJBRemote;

/**
 * Servlet implementation class CarsFromUser
 */

@WebServlet("/CarsFromUser")
public class CarsFromUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    CarsEJBRemote ejbremote;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarsFromUser() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            List<Car> lc = ejbremote.carsFromUser(id);
            out.println("<h1> Cars </h1>");
            for (Car c : lc)
                out.println(c.toString() + "<br/>");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}