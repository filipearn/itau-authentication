package com.filipearn.itauauthentication.app.usecases;

import lombok.extern.slf4j.Slf4j;

import static com.filipearn.itauauthentication.infra.utils.MessageConstants.CLAIM_VALIDATION;

@Slf4j
public class SeedClaimValidationStrategy extends AbstractJWTValidationStrategy{
    @Override
    public boolean validateClaim(final String claimName, final String claimValue) {

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

    private boolean isPrime(final long number){
        if (number <= 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        for (int i = 3; i <= Math.sqrt(number); i+=2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
