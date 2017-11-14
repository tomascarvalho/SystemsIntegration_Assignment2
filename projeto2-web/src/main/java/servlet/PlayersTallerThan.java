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
import ejb.PlayersEJBRemote;

/**
 * Servlet implementation class PlayersTallerThan
 */
// http://localhost:8080/ServerPlayers-Web/PlayersTallerThan?fill=1
// url = http://localhost:8080/ServerPlayers-Web/PlayersTallerThan?height=1.80
@WebServlet("/PlayersTallerThan")
public class PlayersTallerThan extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    PlayersEJBRemote ejbremote;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayersTallerThan() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        if (request.getParameter("fill") != null) {
            ejbremote.populate();
            out.println("<h1>Populate: OK!</h1>");
        }
        else {
            float tall = Float.parseFloat(request.getParameter("height"));
            List<Car> lp = ejbremote.playersTallerThan(tall);

            out.println("<h1> Players </h1>");
            for (Car p : lp)
                out.println(p + "<br/>");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}