package com.filipearn.itauauthentication.app.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface AuthApiDelegate {

    default Optional<NativeWebRequest> getRequest() { return Optional.empty(); };

    default CompletableFuture<ResponseEntity<Object>> authenticationPost(String jwt){
        return CompletableFuture.completedFuture(new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED));
    }
}
