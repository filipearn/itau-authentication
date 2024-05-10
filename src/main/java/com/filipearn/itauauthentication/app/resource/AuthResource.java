package com.filipearn.itauauthentication.app.resource;

import com.filipearn.itauauthentication.app.api.AuthApiDelegate;
import com.filipearn.itauauthentication.app.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class AuthResource implements AuthApiDelegate {

    @Autowired
    private AuthService authService;

    @Override
    public CompletableFuture<ResponseEntity<Object>> authenticationPost(final String jwt) {
        String validation = authService.checkJwt(jwt);
        return CompletableFuture.completedFuture(ResponseEntity.ok().body(validation));
    }
}
