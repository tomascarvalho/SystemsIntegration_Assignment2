package ejb;

import dto.CarDTO;
import dto.CustomerDTO;

import java.util.ArrayList;
import java.util.List;
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
    CustomerDTO updateCar(String brand, String model, String mileage, String month, String year, String price, long customerId, long carId);
    String followCar(long carID, long customerID);
    String unfollowCar(long carID, long userID);
    List<CarDTO> getCarsByBrandModel(String brand, String model);
    List<CarDTO> getCarsByBrand(String brand);
    List<CarDTO> getCarsByPrice(int price_from, int price_to);
    List<CarDTO> getCarsByKm(int km_from, int km_to);
    List<CarDTO> getCarsNewerThan(int year);
}