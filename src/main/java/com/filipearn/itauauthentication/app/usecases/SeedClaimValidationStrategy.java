package com.filipearn.itauauthentication.app.usecases;

import lombok.extern.slf4j.Slf4j;

import static com.filipearn.itauauthentication.infra.utils.MessageConstants.CLAIM_VALIDATION;

@Slf4j
public class SeedClaimValidationStrategy extends AbstractJWTValidationStrategy{
    @Override
    public boolean validateClaim(String claimName, String claimValue) {

        if(!super.validateClaim(claimName, claimValue)){
            return false;
        }

        long number;
        try{
            number = Long.parseLong(claimValue);
        } catch (Exception e){
            log.info(CLAIM_VALIDATION, claimName, false, "valor de claim não é um número");
            return false;
        }

        boolean isPrime = isPrime(number);
        log.info(CLAIM_VALIDATION, claimName, isPrime, isPrime ? "validada com sucesso" : "valor de claim não é um número primo");

        return isPrime;
    }

    private boolean isPrime(long number){
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}