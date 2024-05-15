package com.filipearn.itauauthentication.app.service.impl;

import com.filipearn.itauauthentication.app.service.AuthService;
import com.filipearn.itauauthentication.app.usecases.*;
import com.filipearn.itauauthentication.infra.config.JwtSecretConfig;
import com.filipearn.itauauthentication.infra.config.LogObservationHandler;
import com.filipearn.itauauthentication.infra.utils.JwtParserUtils;
import com.filipearn.itauauthentication.presentation.ValidationMapper;
import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.filipearn.itauauthentication.app.enumeration.ClaimEnum.*;
import static com.filipearn.itauauthentication.infra.utils.MessageConstants.INIT_VALIDATION;
import static com.filipearn.itauauthentication.infra.utils.MessageConstants.RESULT;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(LogObservationHandler.class);

    @Autowired
    private JwtSecretConfig secretConfig;

    @Observed(name = "check-jwt", contextualName = "check-jwt-method-span", lowCardinalityKeyValues = {"low", "low"})
    @Override
    public String checkJwt(final String jwt) {
        log.info(INIT_VALIDATION, jwt);

        if(jwt == null){
            log.info("JWT é nulo");
            log.info(RESULT, false);
            return ValidationMapper.toDTO(false);
        }

        Map<String, JWTValidationStrategy> strategies = new HashMap<>();
        strategies.put(NAME.getDescription(), new NameClaimValidationStrategy());
        strategies.put(ROLE.getDescription(), new RoleClaimValidationStrategy());
        strategies.put(SEED.getDescription(), new SeedClaimValidationStrategy());

        JWTValidator validator = new JWTValidator(strategies);

        Map<String, String> claims;

        try {
            claims = JwtParserUtils.getClaims(jwt, secretConfig.getSigningKey());
        } catch (Exception ex){
            log.error(ex.getMessage(), ex);
            log.info(RESULT, false);
            return ValidationMapper.toDTO(false);
        }

        boolean isValid = validator.validateJWT(claims);

        log.info(RESULT, isValid);

        return ValidationMapper.toDTO(isValid);
    }


}
