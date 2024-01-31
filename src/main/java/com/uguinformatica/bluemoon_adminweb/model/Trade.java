package com.uguinformatica.bluemoon_adminweb.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class Trade {

    private int id;
    private Date date;
    private Boolean validated;
    private User user;
    private Set<Tradeable> tradeables;

}
