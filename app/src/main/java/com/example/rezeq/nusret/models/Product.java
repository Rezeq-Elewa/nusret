package com.example.rezeq.nusret.models;

/**
 * Created by Rezeq on 12/25/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class Product {
    private int id;
    private String name;
    private double price;
    private String image;

    public Product() {
    }

    public Product(int id, String name, double price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
