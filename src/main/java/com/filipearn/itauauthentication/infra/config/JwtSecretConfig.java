package com.filipearn.itauauthentication.infra.config;

import com.filipearn.itauauthentication.infra.utils.SecretUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Getter
@Setter
@Configuration
public class JwtSecretConfig {

    @Value("${token.signingkey:default}")
    private String signingKey;

    @Bean
    public JwtSecretConfig jwtSecret() throws IOException {
        JwtSecretConfig jwtSecretConfig = new JwtSecretConfig();
        jwtSecretConfig.setSigningKey(SecretUtils.extract(getSigningKey()));
        return jwtSecretConfig;
    }
}
