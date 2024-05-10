package com.filipearn.itauauthentication.infra.exception;

public class JWTParseException extends RuntimeException {

    public JWTParseException(String message){
        super(message);
    }
}
