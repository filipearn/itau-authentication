package com.filipearn.itauauthentication.app.api;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.CompletableFuture;

public interface AuthApi {

    default AuthApiDelegate getDelegate() {
        return new AuthApiDelegate(){};
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "v1/authentications"
    )
    default CompletableFuture<ResponseEntity<Object>> authenticationPost(
            @RequestParam(name = "jwt") String jwt){
        return getDelegate().authenticationPost(jwt);
    }
}
