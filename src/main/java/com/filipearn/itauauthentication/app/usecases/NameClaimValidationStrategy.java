package com.filipearn.itauauthentication.app.usecases;

public class NameClaimValidationStrategy implements JWTValidationStrategy{
    @Override
    public boolean validateClaim(String claimValue) {
        return false;
    }
}
