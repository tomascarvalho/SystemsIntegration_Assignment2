package ejb;

import data.Car;
import data.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by jorgearaujo on 15/11/2017.
 */
@Stateless
public class CarEJB {
    @PersistenceContext(name="Cars")
    EntityManager em;

    public boolean createCar(String brand, String model, int mileage, String month, int year, int price, Customer customer)
    {
        try{
            Car newCar = new Car(brand,model,mileage,month,year,price,customer);
            em.persist(newCar);
            return true;
        }catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
