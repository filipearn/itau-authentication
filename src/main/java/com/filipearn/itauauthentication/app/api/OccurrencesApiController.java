package com.filipearn.itauauthentication.app.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class OccurrencesApiController implements AuthApi {

    private  final AuthApiDelegate delegate;

    public OccurrencesApiController(@Autowired(required = false) AuthApiDelegate delegate){
        this.delegate = Optional.ofNullable(delegate).orElse(new AuthApiDelegate() {});
    }

    @Override
    public AuthApiDelegate getDelegate() { return  delegate; }
}
