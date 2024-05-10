package com.filipearn.itauauthentication.infra.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;
import java.util.function.Function;

import static com.filipearn.itauauthentication.infra.utils.LogConstants.*;

@Component
public class MdcInterceptorConfig implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put(TRACE_ID, request.getHeader(TRACE_ID) != null ?
        request.getHeader(TRACE_ID) : getRandomUUID.apply(null));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(TRACE_ID);
    }

    public Function<Void, String> getRandomUUID = (nothing) -> UUID.randomUUID().toString();
}
