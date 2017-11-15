package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.Car;
import data.Customer;

/**
 * Session Bean implementation class CarsEJB
 */
@Stateless
public class CarsEJB implements CarsEJBRemote {
    @PersistenceContext(name="Cars")
    EntityManager em;

    /**
     * Default constructor.
     */
    public CarsEJB() {
        // TODO Auto-generated constructor stub
    }

    public void addCarSV(Car car) {
        Query q = em.createQuery("from Customer where c.firstName = :n and c.lastName = :l");
        q.setParameter("n", "Stand");
        q.setParameter("l", "Virtual");
        Customer standvirtual = (Customer) q.getResultList().get(0);
        if (standvirtual == null) { // first time creating a SV user
            standvirtual = new Customer("Stand", "Virtual");
            em.persist(standvirtual);
        }
        car.setCustomer(standvirtual);
        em.persist(car);
    }


    public List<Car> carsFromUser(int id) {
        System.out.println("In the EJB. ID = " + id);
        Query q = em.createQuery("from Car p where p.customer.id = :i");
        q.setParameter("i", id);
        @SuppressWarnings("unchecked")
        List<Car> result = q.getResultList();
        return result;
    }
}