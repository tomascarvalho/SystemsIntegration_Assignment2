package servlet;

import data.Customer;
import dto.CustomerDTO;
import ejb.CarEJB;
import ejb.CarEJBRemote;
import ejb.CustomerEJBRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by jorgearaujo on 15/11/2017.
 */
@WebServlet("/addCar")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize=1024*1024*50)
public class AddCar extends HttpServlet {

    @EJB
    private CustomerEJBRemote authEJB;
    @EJB
    private CarEJBRemote carRemote;
    private static final String SAVE_DIR="images";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        int mileage = Integer.parseInt(request.getParameter("mileage"));
        String month = request.getParameter("month");
        int year =  Integer.parseInt(request.getParameter("year"));
        int price = Integer.parseInt(request.getParameter("price"));
        long adverterId = (long) session.getAttribute("userId");

        //dumb path
        String savePath = "/Users/tomas/SystemsIntegration_Assignment2/images"; //specify your path here
        File fileSaveDir=new File(savePath);

        //check dir existence
        if(!fileSaveDir.exists()){
            fileSaveDir.mkdir();
        }

        //save photo
        UUID photoUuid = UUID.randomUUID();
        Part part=request.getPart("photo");
        String filename = getFileName(part).replaceAll(" ","");
        String newFilename =  File.separator+photoUuid+filename;
        part.write(savePath + newFilename);
        String imageURL = "/images"+newFilename;


        CustomerDTO customerDTO = carRemote.createCar(brand,model,mileage,month,year,price,adverterId,imageURL);
        if(customerDTO != null)
        {

            session.setAttribute("notification", "Car Adverted Successfully");
            session.setAttribute("user", customerDTO);
            response.sendRedirect(request.getContextPath() +"/addcar.jsp");

        }
        else
        {
            session.setAttribute("notification", "Advert not inserted successfully");
            response.sendRedirect(request.getContextPath()+"/addcar.jsp");

        }
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
