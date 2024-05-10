package com.filipearn.itauauthentication.app.usecases;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static com.filipearn.itauauthentication.infra.utils.MessageConstants.CLAIM_VALIDATION;

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

            //foi considerado que se o valor da claim Name for nulo ou em branco é uma claim inválida
            if(claimValue == null || claimValue.isBlank()){
                log.info(CLAIM_VALIDATION, claimName, false, "valor de claim é nulo ou em branco");
                return false;
            }

            JWTValidationStrategy strategy = strategyMap.get(claimName);
            if(strategy == null){
                log.error("Claim {} nao existente no JWT: falso", claimName);
                return false;
            } else if (!strategy.validateClaim(claimName, claimValue)) {
                return false;
            }
        }
        return true;
    }
}