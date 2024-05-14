package com.filipearn.itauauthentication.infra.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class SecretUtils {

    public static String extract(final String secret) throws IOException {
        Path secretPath = Path.of(secret);

        if(Files.exists(secretPath) && !Files.isDirectory(secretPath)){
            return Files.readString(secretPath);
        } else {
            log.error(String.format(MessageConstants.SIGNING_KEY_ERROR, secret));
            throw new IOException(String.format(MessageConstants.SIGNING_KEY_ERROR, secret));
        }
    }
}
