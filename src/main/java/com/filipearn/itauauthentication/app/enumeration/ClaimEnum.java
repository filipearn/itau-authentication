package com.filipearn.itauauthentication.app.enumeration;

import lombok.Getter;

@Getter
public enum ClaimEnum {

    NAME            ("Name"),
    ROLE            ("Role"),
    SEED            ("Seed");

    private final String description;

    ClaimEnum(String description){
        this.description = description;
    }
}
