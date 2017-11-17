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
import ejb.CustomerEJB;
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
            customerDTO = customerToCustomerDTO(adverter);
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
                    carDTO = carToCarDTO(car);
                    ArrayList<CarDTO> relatedCarsDTO = new ArrayList<>();
                    for (Car relatedCar : relatedCars) {
                        relatedCarsDTO.add(carToCarDTO(relatedCar));
                    }
                    carDTO.setRelatedCars(relatedCarsDTO);
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
                allCarsDTO.add(carToCarDTO(car));
            }
            logger.debug("Debug: returning all cars...");
            return allCarsDTO;

        } catch (NoResultException nre) {
            logger.debug("Debug: No cars found...");
            logger.error("Exception: ", nre);

            return null;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return null;

        }
    }
    public CustomerDTO carDelete(long carID, long customerID)
    {
        try{
            Car car = em.find(Car.class,carID);
            long userID = car.getCustomer().getId();
            if (userID != customerID) {
                return null;
            }
            em.remove(car);
            CustomerDTO customerDTO = new CustomerDTO();
            Customer customer = em.find(Customer.class, userID);
            customer.getCars().remove(car);
            customerDTO = customerToCustomerDTO(customer);

            return customerDTO;
        }catch(Exception e)
        {
            e.printStackTrace();
            return  null;
        }

    }

    public String updateCarAccount(String brand, String model, String mileage, String month, String year, String price, long customerId, long carId)
    {

        String response = "";

        Car toUpdate = em.find(Car.class, carId);
        if (toUpdate == null) {
            response = "Something went wrong!";
            return response;
        }

        //check if car belongs to customer
        if(toUpdate.getCustomer().getId()==customerId){

            System.out.println(brand);
            System.out.printf(model);
            System.out.println(mileage);
            System.out.println(month);
            System.out.println(year);
            System.out.println(price);

            if(brand.length()>1 ){
                if(!brand.equals(toUpdate.getBrand())){
                toUpdate.setBrand(brand);
                response = "Success";
                }
                else {
                    response = "Invalid brand name";
                }
            }


            if(model.length()>1 ){
                if( !model.equals(toUpdate.getModel())){
                    toUpdate.setModel(model);
                    response = "Success";
                }
                else{
                response = "Invalid model name";
                }
            }

            if(mileage.length()>1){
                int intMileage = Integer.parseInt(mileage);
                if(intMileage!=toUpdate.getMileage() && intMileage>=0){
                    toUpdate.setMileage(intMileage);
                    response = "Success";
                }else{
                    response = "Invalid mileage";
                }
            }

            if(month.length()>1){
                if(!month.equals(toUpdate.getMonth())){
                toUpdate.setMonth(month);
                response = "Success";
                }
                else{
                 response = "Invalid month";
                }
            }

            if(year.length()>1) {
                int intYear = Integer.parseInt(year);

                toUpdate.setYear(intYear);
                response = "Success";
            }else{
                response="Invalid year";
            }

            if(price.length()>1){

                int intPrice = Integer.parseInt(price);
                    if(intPrice!=toUpdate.getPrice() && intPrice>0){
                    toUpdate.setPrice(intPrice);
                    response = "Success";
                }else{
                    response ="Invalid price";
                }
            }

            return response;

        }else{
            return response = "Access denied!";
        }
    }


    public CarDTO carToCarDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setBrand(car.getBrand());
        carDTO.setModel(car.getModel());
        carDTO.setMileage(car.getMileage());
        carDTO.setPrice(car.getPrice());
        carDTO.setOwnerId(car.getCustomer().getId());
        carDTO.setImageUrl(car.getImageUrl());
        carDTO.setYear(car.getYear());
        carDTO.setMonth(car.getMonth());

        return carDTO;
    }

    public CustomerDTO customerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        ArrayList cars = new ArrayList();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        for (Car car : customer.getCars()) {
            cars.add(carToCarDTO(car));
        }
        customerDTO.setCars(cars);

        return customerDTO;
    }

    public String followCar(long carID, long userID) {
        try {
            Car car = em.find(Car.class, carID);
            Customer customer = em.find(Customer.class, userID);
            if (car.getFollowers().contains(customer)) {
                return "You already follow this car!";
            }
            car.getFollowers().add(customer);
            return "Success";

        } catch (Exception e) {
            return "Error following this car";
        }


    }


}
