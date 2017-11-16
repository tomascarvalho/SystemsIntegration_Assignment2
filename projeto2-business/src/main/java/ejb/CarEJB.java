package ejb;

import data.Car;
import data.Customer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by jorgearaujo on 15/11/2017.
 */
@Stateless
public class CarEJB implements CarEJBRemote{
    @PersistenceContext(name="Cars")
    EntityManager em;
    @EJB
    CustomerEJBRemote customerRemote;

    public boolean createCar(String brand, String model, int mileage, String month, int year, int price, long customerId)
    {
        try{
            Customer adverter = customerRemote.readCustomerById(customerId);
            Car newCar = new Car(brand,model,mileage,month,year,price,adverter);
            adverter.getCars().add(newCar);
            em.persist(newCar);
            return true;
        }catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
