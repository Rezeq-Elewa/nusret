package com.example.rezeq.nusret.api.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.rezeq.nusret.models.Order;
import com.example.rezeq.nusret.models.OrderItem;

import java.util.ArrayList;

/**
 * Created by Rezeq on 12/26/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class OrderDetailsResponse extends ApiResponse implements Parcelable {

    private OrderDetailsResult result;
    private String error_num;
    private String error;

    public OrderDetailsResponse() {
    }

    public OrderDetailsResponse(boolean status, OrderDetailsResult result, String error_num, String error) {
        this.setStatus(status);
        this.result = result;
        this.error_num = error_num;
        this.error = error;
    }

    protected OrderDetailsResponse(Parcel in) {
        result = in.readParcelable(OrderDetailsResult.class.getClassLoader());
        error_num = in.readString();
        error = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(result, flags);
        dest.writeString(error_num);
        dest.writeString(error);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OrderDetailsResponse> CREATOR = new Creator<OrderDetailsResponse>() {
        @Override
        public OrderDetailsResponse createFromParcel(Parcel in) {
            return new OrderDetailsResponse(in);
        }

        @Override
        public OrderDetailsResponse[] newArray(int size) {
            return new OrderDetailsResponse[size];
        }
    };

    public OrderDetailsResult getResult() {
        return result;
    }

    public void setResult(OrderDetailsResult result) {
        this.result = result;
    }

    public String getError_num() {
        return error_num;
    }

    public void setError_num(String error_num) {
        this.error_num = error_num;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


}


