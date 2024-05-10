package com.filipearn.itauauthentication.app.usecases;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class JWTValidator {
    private final Map<String, JWTValidationStrategy> strategyMap;

    public JWTValidator(Map<String, JWTValidationStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }

    public boolean validateJWT(Map<String, String> claims) {
        for (Map.Entry<String, String> entry : claims.entrySet()) {
            String claimName = entry.getKey();
            String claimValue = entry.getValue();

            JWTValidationStrategy strategy = strategyMap.get(claimName);
            if(strategy == null){
                log.error("Claim {} nao existente no JWT: falso", claimName);
                return false;
            } else if (!strategy.validateClaim(claimValue)) {
                return false;
            }
        }
        return true;
    }
}