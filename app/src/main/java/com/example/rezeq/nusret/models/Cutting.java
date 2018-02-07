package com.example.rezeq.nusret.models;

/**
 * Created by Rezeq on 2/6/2018.
 * Email : rezeq.elewa@gmail.com
 */

public class Cutting {
    private String id;
    private String name;

    public Cutting() {
    }

    public Cutting(String id, String name) {
        this.id = id;
        this.name = name;
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
}
