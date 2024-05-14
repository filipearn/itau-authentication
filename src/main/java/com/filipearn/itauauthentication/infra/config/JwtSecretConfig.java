package com.filipearn.itauauthentication.infra.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.filipearn.itauauthentication.infra.utils.MessageConstants;
import com.filipearn.itauauthentication.infra.utils.SecretUtils;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@Getter
@Configuration
public class JwtSecretConfig {

    @Autowired
    private Environment environment;

    @Value("${application.token.aws-secret-signing-key-name}")
    private String awsSecretName;

    @Value("${application.token.signing-key-name}")
    private String signingKeyName;

    private String signingKey;

    @PostConstruct
    public void initLocal() throws IOException {
        if (Arrays.asList(environment.getActiveProfiles()).contains("local")) {
            log.info("Getting local configuration: Signing Key Path={}", getSigningKeyName());
            this.signingKey = SecretUtils.extract(getSigningKeyName());
        }
    }

    @PostConstruct
    public void init() throws IOException {

        if (Arrays.asList(environment.getActiveProfiles()).contains("local")) {
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();

        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(Region.US_EAST_1)
                .build();

        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(getAwsSecretName())
                .build();

        GetSecretValueResponse getSecretValueResponse;

        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            log.error(MessageConstants.SIGNING_KEY_ERROR_AWS, e);
            throw e;
        }

        String keyMap = getSecretValueResponse.secretString();

        TypeFactory typeFactory = objectMapper.getTypeFactory();

        MapType mapType = typeFactory.constructMapType(Map.class, String.class, Object.class);

        Map<String, Object> map = objectMapper.readValue(keyMap, mapType);

        log.info("Aws Secret Name: {}", getAwsSecretName());
        log.info("Aws Key Name: {}", getSigningKeyName());

        this.signingKey = map.get(getSigningKeyName()).toString();

        client.close();
    }
}
