package com.example.rezeq.nusret.models;

/**
 * Created by Rezeq on 12/25/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class CartItem {

    private int id;
    private String name;
    private double price;
    private String image;
    private int amount;
    private double discount, totalPrice;

    public CartItem() {
    }

    public CartItem(int id, String name, double price, String image, int amount, double discount, double totalPrice) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.amount = amount;
        this.discount = discount;
        this.totalPrice = totalPrice;
    }

    public CartItem(Product product, int amount, double discount, double totalPrice) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.image = product.getImage();
        this.amount = amount;
        this.discount = discount;
        this.totalPrice = totalPrice;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void saveOrUpdate(){
        //TODO save to database
    }

    public void delete(){
        //TODO delete from database
    }
}
