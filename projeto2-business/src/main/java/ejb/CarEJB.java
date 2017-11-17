package ejb;

import data.Car;
import data.Customer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import dto.CarDTO;
import dto.CustomerDTO;

/**
 * Created by jorgearaujo on 15/11/2017.
 */
@Stateless
public class CarEJB implements CarEJBRemote{
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
            } catch (Exception e) {
                e.printStackTrace();
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
                    return carDTO;
                } catch(Exception e) {
                    System.err.println(e);
                    return null;
                }
            }
        } catch (Exception e) {
            System.err.println(e);
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
            customerDTO.setId(customer.getId());
            customerDTO.setFirstName(customer.getFirstName());
            customerDTO.setLastName(customer.getLastName());
            customerDTO.setEmail(customer.getEmail());
            customerDTO.setCars(customer.getCars());

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

}
