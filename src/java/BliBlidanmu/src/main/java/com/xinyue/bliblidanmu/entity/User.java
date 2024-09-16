package com.xinyue.bliblidanmu.entity;

@lombok.Data
public class User {
    private String id;
    private String name;
    private String email;
    private String password;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
