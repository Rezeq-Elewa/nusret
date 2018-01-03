package com.example.rezeq.nusret.models;

/**
 * Created by Rezeq on 12/25/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class CartItem {

    private String id;
    private String product_id;
    private String name;
    private String img;
    private String price;
    private String amount;
    private String total;
    private String coupone;
    private String coupon_discount;
    private String coupone_price;
    private String coupone_total;

    public CartItem() {
    }

    public CartItem(String id, String product_id, String name, String img, String price, String amount, String total, String coupone, String coupon_discount, String coupone_price, String coupone_total) {
        this.id = id;
        this.product_id = product_id;
        this.name = name;
        this.img = img;
        this.price = price;
        this.amount = amount;
        this.total = total;
        this.coupone = coupone;
        this.coupon_discount = coupon_discount;
        this.coupone_price = coupone_price;
        this.coupone_total = coupone_total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
        this.total = String.valueOf(Integer.parseInt(amount) * Integer.parseInt(price));
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCoupone() {
        return coupone;
    }

    public void setCoupone(String coupone) {
        this.coupone = coupone;
    }

    public String getCoupon_discount() {
        return coupon_discount;
    }

    public void setCoupon_discount(String coupon_discount) {
        this.coupon_discount = coupon_discount;
    }

    public String getCoupone_price() {
        return coupone_price;
    }

    public void setCoupone_price(String coupone_price) {
        this.coupone_price = coupone_price;
    }

    public String getCoupone_total() {
        return coupone_total;
    }

    public void setCoupone_total(String coupone_total) {
        this.coupone_total = coupone_total;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
