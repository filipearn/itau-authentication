package com.filipearn.itauauthentication.app.usecases;

import lombok.extern.slf4j.Slf4j;

import static com.filipearn.itauauthentication.infra.utils.MessageConstants.CLAIM_VALIDATION;

@Slf4j
public class NameClaimValidationStrategy extends AbstractJWTValidationStrategy{
    @Override
    public boolean validateClaim(String claimName, String claimValue) {

        if(!super.validateClaim(claimName, claimValue)){
            return false;
        }

        if(claimValue.length() > 256){
            log.info(CLAIM_VALIDATION, claimName, false, "valor de claim é maior que 256 caracteres");
            return false;
        }

        for (char c : claimValue.toCharArray()) {
            if (Character.isDigit(c)) {
                log.info(CLAIM_VALIDATION, claimName, false, "valor de claim possui caracter de números");
                return false;
            }
        }
        log.info(CLAIM_VALIDATION, claimName, true, "validada com sucesso");
        return true;
    }
}
