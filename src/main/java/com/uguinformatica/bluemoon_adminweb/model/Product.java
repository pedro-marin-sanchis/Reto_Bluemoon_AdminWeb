package com.uguinformatica.bluemoon_adminweb.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private long id;
    private String name;
    private String description;
    private double price;
    private String img;
    private Boolean disabled;

}
