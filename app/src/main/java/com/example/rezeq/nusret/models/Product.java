package com.example.rezeq.nusret.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rezeq on 12/25/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class Product implements Parcelable{
    private String id;
    private String category_id;
    private String name;
    private String price;
    private String description;
    private String img;
    private String status;
    private String created_at;
    private String updated_at;


    public Product() {
    }

    public Product(String id, String category_id, String name, String price, String description, String img, String status, String created_at, String updated_at) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.img = img;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    protected Product(Parcel in) {
        id = in.readString();
        category_id = in.readString();
        name = in.readString();
        price = in.readString();
        description = in.readString();
        img = in.readString();
        status = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(category_id);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(description);
        dest.writeString(img);
        dest.writeString(status);
        dest.writeString(created_at);
        dest.writeString(updated_at);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
