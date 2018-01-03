package com.example.rezeq.nusret.api.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.rezeq.nusret.models.Product;

/**
 * Created by Rezeq on 1/2/2018.
 * Email : rezeq.elewa@gmail.com
 */

public class showProductResult implements Parcelable{
    private Product product;

    public showProductResult() {
    }

    public showProductResult(Product product) {
        this.product = product;
    }

    protected showProductResult(Parcel in) {
        product = in.readParcelable(Product.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(product, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<showProductResult> CREATOR = new Creator<showProductResult>() {
        @Override
        public showProductResult createFromParcel(Parcel in) {
            return new showProductResult(in);
        }

        @Override
        public showProductResult[] newArray(int size) {
            return new showProductResult[size];
        }
    };

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
