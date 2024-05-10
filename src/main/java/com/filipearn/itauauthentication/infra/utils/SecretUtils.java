package com.filipearn.itauauthentication.infra.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SecretUtils {

    public static String extract(final String secret) throws IOException {
        Path secretPath = Path.of(secret);

        if(Files.exists(secretPath) && !Files.isDirectory(secretPath)){
            return Files.readString(secretPath);
        } else {
            return secret;
        }
    }
}
