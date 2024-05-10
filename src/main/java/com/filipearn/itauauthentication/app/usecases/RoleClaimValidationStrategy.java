package com.filipearn.itauauthentication.app.usecases;

public class RoleClaimValidationStrategy implements JWTValidationStrategy{
    @Override
    public boolean validateClaim(String claimName, String claimValue) {
        return false;
    }
}
