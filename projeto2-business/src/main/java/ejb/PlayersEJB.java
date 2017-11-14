package ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.Car;

/**
 * Session Bean implementation class PlayersEJB
 */
@Stateless
public class PlayersEJB implements PlayersEJBRemote {
    @PersistenceContext(name="Players")
    EntityManager em;

    /**
     * Default constructor.
     */
    public PlayersEJB() {
        // TODO Auto-generated constructor stub
    }

    public static Date getDate(int day, int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);

        Date d = cal.getTime();
        return d;
    }


    public void populate() {
        Team [] teams = { new Team("Sporting", "Alvalade", "Carvalho"), new Team("Academica", "Coimbra", "Simões"), new Team("Porto", "Antas", "Costa"), new Team("Benfica", "Luz", "Vieira") };
        Car[] cars = {
                new Car("Albino", getDate(23,4,1987), 1.87f, teams[0]),
                new Car("Bernardo", getDate(11,4,1987), 1.81f, teams[0]),
                new Car("Cesar", getDate(12,5,1983), 1.74f, teams[0]),
                new Car("Dionisio", getDate(3,12,1992), 1.67f, teams[0]),
                new Car("Eduardo", getDate(31,8,1985), 1.89f, teams[0]),
                new Car("Franco", getDate(6,1,1989), 1.95f, teams[1]),
                new Car("Gil", getDate(7,12,1986), 1.8f, teams[1]),
                new Car("Helder", getDate(14,5,1987), 1.81f, teams[1]),
                new Car("Ilidio", getDate(13,6,1991), 1.82f, teams[1]),
                new Car("Jacare", getDate(4,2,1993), 1.83f, teams[1]),
                new Car("Leandro", getDate(4,10,1984), 1.81f, teams[2]),
                new Car("Mauricio", getDate(3,6,1984), 1.8f, teams[2]),
                new Car("Nilton", getDate(11,3,1985), 1.88f, teams[2]),
                new Car("Oseias", getDate(23,11,1990), 1.74f, teams[2]),
                new Car("Paulino", getDate(14,9,1986), 1.75f, teams[2]),
                new Car("Quevedo", getDate(10,10,1987), 1.77f, teams[2]),
                new Car("Renato", getDate(7,7,1991), 1.71f, teams[3]),
                new Car("Saul", getDate(13,7,1992), 1.86f, teams[3]),
                new Car("Telmo", getDate(4,1,1981), 1.88f, teams[3]),
                new Car("Ulisses", getDate(29,8,1988), 1.84f, teams[3]),
                new Car("Vasco", getDate(16,5,1988), 1.83f, teams[3]),
                new Car("X", getDate(8,12,1990), 1.82f, teams[3]),
                new Car("Ze", getDate(13,5,1987), 1.93f, teams[3]),
        };

        for (Team t : teams)
            em.persist(t);

        for (Car p : cars)
            em.persist(p);
    }


    public List<Car> playersTallerThan(float threshold) {
        System.out.println("In the EJB. Height = " + threshold);
        Query q = em.createQuery("from Car p where p.height >= :t");
        q.setParameter("t", threshold);
        @SuppressWarnings("unchecked")
        List<Car> result = q.getResultList();
        return result;
    }
}