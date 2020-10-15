package com.example.salonna;

public class BabyAndKids_Cream_Products {
    private String Name;
    private String Brand;
    private String ID;
    private Double Price;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    private String ImageId;


    public BabyAndKids_Cream_Products() {
    }

    public BabyAndKids_Cream_Products(String name, String brand, double price, String imageId) {
        Name = name;
        Brand = brand;
        Price = price;
        ImageId = imageId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

}
