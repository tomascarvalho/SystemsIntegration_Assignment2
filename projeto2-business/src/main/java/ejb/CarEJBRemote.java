package ejb;

import dto.CarDTO;
import dto.CustomerDTO;

import javax.ejb.Remote;

/**
 * Created by jorgearaujo on 15/11/2017.
 */
@Remote
public interface CarEJBRemote {
    CustomerDTO createCar(String brand, String model, int mileage, String month, int year, int price, long customerId, String imageURL);
    CarDTO readCar(long carID);
}