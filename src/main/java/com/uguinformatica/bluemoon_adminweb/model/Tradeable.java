package com.uguinformatica.bluemoon_adminweb.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tradeable {

    private int id;
    private double weight;
    private double sellPrice;
    private String description;
    private Trade trade;
    private SilverType silverType;

}
