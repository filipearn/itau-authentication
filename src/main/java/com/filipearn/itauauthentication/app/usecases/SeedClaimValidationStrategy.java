package com.filipearn.itauauthentication.app.usecases;

public class SeedClaimValidationStrategy implements JWTValidationStrategy{
    @Override
    public boolean validateClaim(String claimName, String claimValue) {
        return false;
    }
}
