package com.filipearn.itauauthentication.app.enumeration;

import lombok.Getter;

@Getter
public enum RoleEnum {

    ADMIN               ("Admin"),
    MEMBER              ("Member"),
    EXTERNAL            ("External");

    private final String description;

    RoleEnum(String description){
        this.description = description;
    }
}
