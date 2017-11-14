package ejb;

import java.util.List;

import javax.ejb.Remote;

import data.Car;

@Remote
public interface PlayersEJBRemote {
    public void populate();
    public List<Car> playersTallerThan(float threshold);
}