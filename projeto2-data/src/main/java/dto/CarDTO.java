package dto;

import java.io.Serializable;
import java.util.List;

import data.Car;
import data.Customer;


public class CarDTO implements Serializable {
    public long id;
    public String brand;
    public String model;
    public String month;
    public String imageUrl;
    public int year;
    public int price;
    public int mileage;
    public Customer owner;
    public List<Car> relatedCars;


    public CarDTO() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMonth() {
        return month;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public List<Car> getRelatedCars() {
        return relatedCars;
    }

    public void setRelatedCars(List<Car> relatedCars) {
        this.relatedCars = relatedCars;
    }
}
