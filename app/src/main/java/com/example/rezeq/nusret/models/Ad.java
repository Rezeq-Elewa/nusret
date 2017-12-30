package com.example.rezeq.nusret.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rezeq on 12/30/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class Ad implements Parcelable{
    private String id;
    private String description;
    private String img;
    private String link;
    private String status;
    private String created_at;
    private String updated_at;

    public Ad() {
    }

    public Ad(String id, String description, String img, String link, String status, String created_at, String updated_at) {
        this.id = id;
        this.description = description;
        this.img = img;
        this.link = link;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    private Ad(Parcel in) {
        id = in.readString();
        description = in.readString();
        img = in.readString();
        link = in.readString();
        status = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
    }

    public static final Creator<Ad> CREATOR = new Creator<Ad>() {
        @Override
        public Ad createFromParcel(Parcel in) {
            return new Ad(in);
        }

        @Override
        public Ad[] newArray(int size) {
            return new Ad[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(description);
        parcel.writeString(img);
        parcel.writeString(link);
        parcel.writeString(status);
        parcel.writeString(created_at);
        parcel.writeString(updated_at);
    }
}
