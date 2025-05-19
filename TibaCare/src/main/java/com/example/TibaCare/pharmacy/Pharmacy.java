package com.example.TibaCare.pharmacy;

import jakarta.persistence.*;

import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "pharmacy")
@Table(name = "pharmacy")
public class Pharmacy {
    @Id
    @SequenceGenerator(
            name = "pharmacy_sequence",
            sequenceName = "pharmacy_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "pharmacy_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private int id;
    @Column(
            name = "medicinename",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String medicinename;
    private List<String> Category ;
    @Column(
            name = "price",
            nullable = false
    )
    private double price;
    @Column(
            name = "quantity",
            nullable = false
    )
    private int quantity;
    @Column(
            name = "manufacturer",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String manufacturer;

    @Column(
            name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;
    @Column(
            name = "expiredate",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String expiredate;

    public Pharmacy() {
    }
    public Pharmacy(int id, String medicinename,
                    double price, int quantity, String manufacturer,
                    String description, String expiredate) {
        this.id = id;
        this.medicinename = medicinename;
        this.price = price;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
        this.description = description;
        this.expiredate = expiredate;
    }
    public Pharmacy(String medicinename, double price, int quantity,
                    String manufacturer, String description, String expiredate) {
        this.medicinename = medicinename;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicinename() {
        return medicinename;
    }

    public void setMedicinename(String medicinename) {
        this.medicinename = medicinename;
    }

    public List<String> getCategory() {
        return Category;
    }

    public void setCategory(List<String> category) {
        Category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(String expiredate) {
        this.expiredate = expiredate;
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "id=" + id +
                ", medicinename='" + medicinename + '\'' +
                ", Category=" + Category +
                ", price=" + price +
                ", quantity=" + quantity +
                ", manufacturer='" + manufacturer + '\'' +
                ", description='" + description + '\'' +
                ", expiredate='" + expiredate + '\'' +
                '}';
    }
}
