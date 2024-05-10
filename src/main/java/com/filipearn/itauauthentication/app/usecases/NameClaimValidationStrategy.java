package com.filipearn.itauauthentication.app.usecases;

import lombok.extern.slf4j.Slf4j;

import static com.filipearn.itauauthentication.infra.utils.MessageConstants.CLAIM_VALIDATION;

@Slf4j
public class NameClaimValidationStrategy implements JWTValidationStrategy{
    @Override
    public boolean validateClaim(String claimName, String claimValue) {
        //considero que se o valor da claim Name for nulo ou em branco é uma claim inválida
        if(claimValue == null || claimValue.isBlank()){
            log.info(CLAIM_VALIDATION, claimName, false, "valor de claim é nulo ou em branco");
            return false;
        }

        for (char c : claimValue.toCharArray()) {
            if (Character.isDigit(c)) {
                log.info(CLAIM_VALIDATION, claimName, false, "valor de claim é contém dígito");
                return false;
            }
        }
        log.info(CLAIM_VALIDATION, claimName, false, "valor de claim é contém dígito");
        return true;
    }
}
