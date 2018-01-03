package com.example.rezeq.nusret.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rezeq on 12/31/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class OrderItem  implements Parcelable{
    private String name;
    private String amount;
    private String total;
    private String coupon_discount;
    private String coupon_total;

    public OrderItem() {
    }

    public OrderItem(String name, String amount, String total, String coupon_discount, String coupon_total) {
        this.name = name;
        this.amount = amount;
        this.total = total;
        this.coupon_discount = coupon_discount;
        this.coupon_total = coupon_total;
    }

    protected OrderItem(Parcel in) {
        name = in.readString();
        amount = in.readString();
        total = in.readString();
        coupon_discount = in.readString();
        coupon_total = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(amount);
        dest.writeString(total);
        dest.writeString(coupon_discount);
        dest.writeString(coupon_total);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OrderItem> CREATOR = new Creator<OrderItem>() {
        @Override
        public OrderItem createFromParcel(Parcel in) {
            return new OrderItem(in);
        }

        @Override
        public OrderItem[] newArray(int size) {
            return new OrderItem[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCoupon_discount() {
        return coupon_discount;
    }

    public void setCoupon_discount(String coupon_discount) {
        this.coupon_discount = coupon_discount;
    }

    public String getCoupon_total() {
        return coupon_total;
    }

    public void setCoupon_total(String coupon_total) {
        this.coupon_total = coupon_total;
    }
}
