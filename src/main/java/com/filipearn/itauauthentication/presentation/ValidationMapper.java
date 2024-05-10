package com.filipearn.itauauthentication.presentation;

public class ValidationMapper {

    public static final String VERDADEIRO = "verdadeiro";
    public static final String FALSO = "falso";

    public static String toDTO(boolean isValid){
        return isValid ? VERDADEIRO : FALSO;
    }
}
