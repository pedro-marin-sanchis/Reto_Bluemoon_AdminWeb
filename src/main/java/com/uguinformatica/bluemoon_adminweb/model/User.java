package com.uguinformatica.bluemoon_adminweb.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class User {

    public User() {}

    public User(String username, String name, String surnames, String email, String address, Double balance, String password, Set<Role> rolesAssociated, String authToken) {
        this.username = username;
        this.name = name;
        this.surnames = surnames;
        this.email = email;
        this.address = address;
        this.balance = balance;
        this.password = password;
        this.rolesAssociated = rolesAssociated;
        this.authToken = authToken;
    }

    private Long id;
    private String username;
    private String name;
    private String surnames;
    private String email;
    private String address;
    private Double balance;
    private String password; // Never received. REST API does not disclose passwords.
    private Set<Role> rolesAssociated = new HashSet<>();
    private String authToken;

}