package ejb;

import java.util.List;

import javax.ejb.Remote;

import data.Car;

@Remote
public interface CarsEJBRemote {
    public List<Car> carsFromUser(int id);
    public void addCarSV(Car car);
    public boolean updateCustomerAccount(String email, String password, String newPassword, String confirmPassword,
                                         String firstName, String lastName);
}