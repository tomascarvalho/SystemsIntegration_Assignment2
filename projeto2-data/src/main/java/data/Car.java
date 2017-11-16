package data;

import javax.persistence.*;
import java.io.Serializable;
/**
 * Entity implementation class for Entity: Car
 *
 */
@Entity
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String brand;
    private String model;
    private int mileage;
    private String month;
    private int year;
    private int price;

    private String imageUrl;
    @ManyToOne
    private Customer customer;

    public Car() {
        super();
    }

    public Car(String brand, String model, int mileage, String month, int year, int price, Customer customer) {
        super();
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.month = month;
        this.year = year;
        this.price = price;
        this.customer = customer;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getMonth() {
        return month;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Brand: " + this.brand + " Model: " + this.model + " Mileage: " +this.mileage + " Year: " + this.year + " Month: " + this.month + " Price: " + this.price;
    }

}