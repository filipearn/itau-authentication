package com.filipearn.itauauthentication.app.usecases;

import lombok.extern.slf4j.Slf4j;

import static com.filipearn.itauauthentication.infra.utils.MessageConstants.CLAIM_VALIDATION;

@Slf4j
public class SeedClaimValidationStrategy implements JWTValidationStrategy{
    @Override
    public boolean validateClaim(String claimName, String claimValue) {
        long number = 0;
        try{
            number = Long.parseLong(claimValue);
        } catch (Exception e){
            log.info(CLAIM_VALIDATION, claimName, false, "valor de claim não é um número");
            return false;
        }
        
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                log.info(CLAIM_VALIDATION, claimName, false, "valor de claim não é um número primo");
                return false;
            }
        }

        log.info(CLAIM_VALIDATION, claimName, true, "validada com sucesso");
        return true;
    }
}
