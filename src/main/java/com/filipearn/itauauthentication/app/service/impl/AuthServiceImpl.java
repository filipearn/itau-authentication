package com.filipearn.itauauthentication.app.service.impl;

import com.filipearn.itauauthentication.app.service.AuthService;
import com.filipearn.itauauthentication.app.usecases.*;
import com.filipearn.itauauthentication.infra.utils.JwtParserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.filipearn.itauauthentication.app.enumeration.ClaimEnum.*;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String checkJwt(String jwt) {

        Map<String, JWTValidationStrategy> strategies = new HashMap<>();
        strategies.put(NAME.getDescription(), new NameClaimValidationStrategy());
        strategies.put(ROLE.getDescription(), new RoleClaimValidationStrategy());
        strategies.put(SEED.getDescription(), new SeedClaimValidationStrategy());

        JWTValidator validator = new JWTValidator(strategies);

        Map<String, String> claims = JwtParserUtils.getClaims(jwt);

        boolean isValid = validator.validateJWT(claims);

        String result = isValid ? "verdadeiro" : "falso";

        log.info("Result: {} - JWT: {}", result, jwt);

        return result;
    }


}
