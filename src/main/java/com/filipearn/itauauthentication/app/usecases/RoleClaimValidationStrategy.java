package com.filipearn.itauauthentication.app.usecases;

import lombok.extern.slf4j.Slf4j;

import static com.filipearn.itauauthentication.app.enumeration.RoleEnum.*;
import static com.filipearn.itauauthentication.infra.utils.MessageConstants.CLAIM_VALIDATION;

@Slf4j
public class RoleClaimValidationStrategy extends AbstractJWTValidationStrategy{
    @Override
    public boolean validateClaim(String claimName, String claimValue) {

        if(!super.validateClaim(claimName, claimValue)){
            return false;
        }

        if(ADMIN.getDescription().equals(claimValue) ||
                MEMBER.getDescription().equals(claimValue) ||
                EXTERNAL.getDescription().equals(claimValue)){
            log.info(CLAIM_VALIDATION, claimName, true, "validada com sucesso");
            return true;
        }

        log.info(CLAIM_VALIDATION, claimName, false, "valor de claim não contém os valores definidos");
        return false;
    }
}
