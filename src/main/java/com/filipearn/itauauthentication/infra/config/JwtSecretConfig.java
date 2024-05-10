package com.filipearn.itauauthentication.infra.config;

import com.filipearn.itauauthentication.infra.utils.SecretUtils;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Getter
@Configuration
public class JwtSecretConfig {

    @Value("${application.token.signing-key-path}")
    private String signingKeyPath;

    private String signingKey;

    @PostConstruct
    public void init() throws IOException {
        this.signingKey = SecretUtils.extract(getSigningKeyPath());
    }
}
