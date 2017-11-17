package ejb;

import data.Car;
import data.Customer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import dto.CarDTO;
import dto.CustomerDTO;
import org.jboss.logging.Logger;

/**
 * Created by jorgearaujo on 15/11/2017.
 */
@Stateless
public class CarEJB implements CarEJBRemote{
    final static Logger logger = Logger.getLogger(CarEJB.class);
    @PersistenceContext(name="Cars")
    EntityManager em;
    @EJB
    CustomerEJBRemote customerRemote;

    public CustomerDTO createCar(String brand, String model, int mileage, String month, int year, int price, long customerId, String imageURL)
    {
        try{
            Customer adverter = em.find(Customer.class, customerId);
            if (adverter == null) {
                return null;
            }
            try {
                Car newCar = new Car(brand, model, mileage, month, year, price, adverter, imageURL);
                adverter.getCars().add(newCar);
                em.persist(newCar);

                logger.debug("Debug: new car created " + newCar.toString());

            } catch (Exception e) {
                //logs an exception thrown from somewhere
                logger.error("Exception: ", e);
                return null;
            }
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId(adverter.getId());
            customerDTO.setFirstName(adverter.getFirstName());
            customerDTO.setLastName(adverter.getLastName());
            customerDTO.setEmail(adverter.getEmail());
            customerDTO.setCars(adverter.getCars());
            return customerDTO;

        }catch(Exception e)
        {
            e.printStackTrace();
            //logs an exception thrown from somewhere
            logger.error("Exception: ", e);
            return null;
        }
    }

    public CarDTO readCar(long carID) {
        try {
            Car car = em.find(Car.class, carID);
            if (car == null) {
                return null;
            } else {
                try {
                    Query newQuery = em.createQuery(" FROM Car c where c.brand=?1 AND c.id != ?2");
                    newQuery.setParameter(1, car.getBrand());
                    newQuery.setParameter(2, car.getId());
                    newQuery.setMaxResults(4);
                    List <Car> relatedCars = newQuery.getResultList();

                    CarDTO carDTO = new CarDTO();
                    carDTO.setId(car.getId());
                    carDTO.setBrand(car.getBrand());
                    carDTO.setModel(car.getModel());
                    carDTO.setMileage(car.getMileage());
                    carDTO.setPrice(car.getPrice());
                    carDTO.setOwner(car.getCustomer());
                    carDTO.setImageUrl(car.getImageUrl());
                    carDTO.setRelatedCars(relatedCars);
                    carDTO.setYear(car.getYear());
                    carDTO.setMonth(car.getMonth());
                    //logs a debug message
                    logger.debug("Debug: getting car" + car.toString());
                    return carDTO;
                } catch(Exception e) {
                    //logs an exception thrown from somewhere
                    logger.error("Exception: ", e);
                    return null;
                }
            }
        } catch (Exception e) {
            //logs an exception thrown from somewhere
            logger.error("Exception: ", e);
            return null;
        }
    }

    public ArrayList<CarDTO> getAllCars() {
        try {
            Query newQuery = em.createQuery(" FROM Car");
            List<Car> allCars = newQuery.getResultList();
            ArrayList<CarDTO> allCarsDTO = new ArrayList<>();
            for (Car car : allCars) {
                CarDTO carDTO = new CarDTO();
                carDTO.setId(car.getId());
                carDTO.setBrand(car.getBrand());
                carDTO.setModel(car.getModel());
                carDTO.setMileage(car.getMileage());
                carDTO.setPrice(car.getPrice());
                carDTO.setOwner(car.getCustomer());
                carDTO.setImageUrl(car.getImageUrl());
                carDTO.setYear(car.getYear());
                carDTO.setMonth(car.getMonth());
                allCarsDTO.add(carDTO);
            }
            logger.debug("Debug: returning all cars...");
            return allCarsDTO;

        } catch(NoResultException nre) {
            logger.debug("Debug: No cars found...");
            logger.error("Exception: ", nre);

            return null;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return null;
        }

    }
}
