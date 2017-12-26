package com.example.rezeq.nusret.models;

/**
 * Created by Rezeq on 12/25/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class Category {
    private int id;
    private String title;
    private String image;

    public Category() {
    }

    public Category(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
