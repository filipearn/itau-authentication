package com.filipearn.itauauthentication.infra.utils;

import com.filipearn.itauauthentication.infra.exception.JWTParseException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtParserUtils {

    public static Map<String, String> getClaims(final String jwt, final String secretKey) {

        Map<String, String> claimsMap = new HashMap<>();

        try {
            Jws<Claims> parsedToken = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwt);

            Claims claims = parsedToken.getBody();
            for (Map.Entry<String, Object> entry : claims.entrySet()) {
                claimsMap.put(entry.getKey(), entry.getValue().toString());
            }
        } catch (Exception e) {
            throw new JWTParseException("JWT invalido");
        }

        return claimsMap;
    }
}
