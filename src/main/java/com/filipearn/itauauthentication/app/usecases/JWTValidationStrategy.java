package com.filipearn.itauauthentication.app.usecases;

public interface JWTValidationStrategy {
    boolean validateClaim(String claimName, String claimValue);
}
