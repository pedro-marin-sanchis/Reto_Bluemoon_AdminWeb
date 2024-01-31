package com.uguinformatica.bluemoon_adminweb.model;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
public class Role {

    private Long id;
    private String name;
    private Set<User> usersAssociated = new LinkedHashSet<>();

}