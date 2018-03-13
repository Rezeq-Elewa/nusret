package com.example.rezeq.nusret.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rezeq on 12/25/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class Order implements Parcelable{

    private String id;
    private String total;
    private String discount_total;
    private String items;
    private String delivery_way;
    private String pay_way;
    private String status;
    private String created_at;

    public Order() {
    }

    public Order(String id, String total, String discount_total, String items, String delivery_way, String pay_way, String status, String created_at) {
        this.id = id;
        this.total = total;
        this.discount_total = discount_total;
        this.items = items;
        this.delivery_way = delivery_way;
        this.pay_way = pay_way;
        this.status = status;
        this.created_at = created_at;
    }

    protected Order(Parcel in) {
        id = in.readString();
        total = in.readString();
        discount_total = in.readString();
        items = in.readString();
        delivery_way = in.readString();
        pay_way = in.readString();
        status = in.readString();
        created_at = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(total);
        dest.writeString(discount_total);
        dest.writeString(items);
        dest.writeString(delivery_way);
        dest.writeString(pay_way);
        dest.writeString(status);
        dest.writeString(created_at);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getDelivery_way() {
        return delivery_way;
    }

    public void setDelivery_way(String delivery_way) {
        this.delivery_way = delivery_way;
    }

    public String getPay_way() {
        return pay_way;
    }

    public void setPay_way(String pay_way) {
        this.pay_way = pay_way;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDiscount_total() {
        return discount_total;
    }

    public void setDiscount_total(String discount_total) {
        this.discount_total = discount_total;
    }
}
