package com.uguinformatica.bluemoon_adminweb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role(){}

    @ManyToMany(mappedBy = "rolesAssociated", fetch = FetchType.LAZY)
    private Set<AppUser> usersAssociated = new LinkedHashSet<>();

}