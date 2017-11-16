package ejb;

import data.Car;
import data.Customer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    public CustomerDTO createCar(String brand, String model, int mileage, String month, int year, int price, long customerId)
    {
        try{
            Customer adverter = em.find(Customer.class, customerId);
            try {
                Car newCar = new Car(brand, model, mileage, month, year, price, adverter);
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
}
