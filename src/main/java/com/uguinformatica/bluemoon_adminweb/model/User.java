package com.uguinformatica.bluemoon_adminweb.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class User {

    public User() {}

    public User(String username, String name, String lastName, String email, String phoneNumber, String address, Double balance, String password, Set<Role> rolesAssociated) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.balance = balance;
        this.password = password;
        this.rolesAssociated = rolesAssociated;
    }

    private Long id;
    private String username;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private Double balance;
    private String password;
    private Set<Role> rolesAssociated = new HashSet<>();

}