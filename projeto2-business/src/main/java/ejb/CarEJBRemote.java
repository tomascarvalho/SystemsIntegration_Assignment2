package ejb;

import dto.CarDTO;
import dto.CustomerDTO;

import java.util.ArrayList;
import javax.ejb.Remote;

/**
 * Created by jorgearaujo on 15/11/2017.
 */
@Remote
public interface CarEJBRemote {
    CustomerDTO createCar(String brand, String model, int mileage, String month, int year, int price, long customerId, String imageURL);
    CarDTO readCar(long carID);
    ArrayList<CarDTO> getAllCars();
    CustomerDTO carDelete(long carID, long customerID);
    String updateCarAccount(String brand, String model, String mileage, String month, String year, String price, long customerId, long carId);
    String followCar(long carID, long customerID);
}