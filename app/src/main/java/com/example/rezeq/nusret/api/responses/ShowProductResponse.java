package com.example.rezeq.nusret.api.responses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rezeq on 12/26/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class ShowProductResponse extends ApiResponse implements Parcelable{

    private showProductResult result;
    private String error_num;
    private String error;

    public ShowProductResponse() {
    }

    public ShowProductResponse(boolean status, showProductResult result, String error_num, String error) {
        this.setStatus(status);
        this.result = result;
        this.error_num = error_num;
        this.error = error;
    }

    protected ShowProductResponse(Parcel in) {
        result = in.readParcelable(com.example.rezeq.nusret.api.responses.showProductResult.class.getClassLoader());
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

    public static final Creator<ShowProductResponse> CREATOR = new Creator<ShowProductResponse>() {
        @Override
        public ShowProductResponse createFromParcel(Parcel in) {
            return new ShowProductResponse(in);
        }

        @Override
        public ShowProductResponse[] newArray(int size) {
            return new ShowProductResponse[size];
        }
    };

    public showProductResult getResult() {
        return result;
    }

    public void setResult(showProductResult result) {
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
