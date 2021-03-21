package com.github.andrelugomes.model;

import java.io.Serializable;

public class ClientSerializable implements Serializable {

    private Integer id;
    private String token;
    private String name;

    public ClientSerializable() {
    }

    public ClientSerializable(Integer id, String token, String name) {
        this.id = id;
        this.token = token;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}