package com.sg.ceci.model;

import lombok.Getter;

@Getter
public enum Type {
    PAY("pay"),
    PRIME("prime"),
    RTT("rtt"),
    STOCK_OPTION("STOCK_OPTION");

    private final String name;

    Type(String name) {
        this.name = name;
    }
}
