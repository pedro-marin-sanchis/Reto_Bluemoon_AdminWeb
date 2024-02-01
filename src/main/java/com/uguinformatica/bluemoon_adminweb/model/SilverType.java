package com.uguinformatica.bluemoon_adminweb.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SilverType {

    private int id;
    private String name;
    private double currentPrice;
    private Boolean disabled;

}
