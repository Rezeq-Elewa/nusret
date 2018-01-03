package com.example.rezeq.nusret.api.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.rezeq.nusret.models.Order;
import com.example.rezeq.nusret.models.OrderItem;

import java.util.ArrayList;

/**
 * Created by Rezeq on 1/2/2018.
 * Email : rezeq.elewa@gmail.com
 */

public class OrderDetailsResult implements Parcelable {
    private Order order;
    private ArrayList<OrderItem> items;

    public OrderDetailsResult() {
    }

    public OrderDetailsResult(Order order, ArrayList<OrderItem> items) {
        this.order = order;
        this.items = items;
    }

    protected OrderDetailsResult(Parcel in) {
        order = in.readParcelable(Order.class.getClassLoader());
        items = in.createTypedArrayList(OrderItem.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(order, flags);
        dest.writeTypedList(items);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OrderDetailsResult> CREATOR = new Creator<OrderDetailsResult>() {
        @Override
        public OrderDetailsResult createFromParcel(Parcel in) {
            return new OrderDetailsResult(in);
        }

        @Override
        public OrderDetailsResult[] newArray(int size) {
            return new OrderDetailsResult[size];
        }
    };

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }
}
