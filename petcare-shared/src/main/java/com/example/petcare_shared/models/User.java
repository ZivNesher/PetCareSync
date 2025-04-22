package com.example.petcare_shared.models;

public class User {
    private String id;
    private String email;
    private String role; // "owner" or "vet"

    public User() {}

    public User(String id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }
    public String getId() {
        return id;
    }
}
