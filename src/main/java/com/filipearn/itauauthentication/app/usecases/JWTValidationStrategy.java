package com.filipearn.itauauthentication.app.usecases;

public interface JWTValidationStrategy {
    boolean validateClaim(final String claimName, final String claimValue);
}
