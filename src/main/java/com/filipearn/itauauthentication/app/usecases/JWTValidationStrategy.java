package com.filipearn.itauauthentication.app.usecases;

public interface JWTValidationStrategy {
    boolean validateClaim(String claimValue);
}
