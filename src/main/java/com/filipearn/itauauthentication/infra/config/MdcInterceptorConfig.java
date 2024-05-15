package com.filipearn.itauauthentication.infra.config;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanContext;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.filipearn.itauauthentication.infra.utils.LogConstants.SPAN_ID;
import static com.filipearn.itauauthentication.infra.utils.LogConstants.TRACE_ID;

@Component
public class MdcInterceptorConfig implements HandlerInterceptor {

    private final Tracer tracer;

    public MdcInterceptorConfig(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Context context = Context.current();

        Span span = Span.fromContext(context);

        SpanContext spanContext = span.getSpanContext();

        MDC.put(TRACE_ID, spanContext.getTraceId());
        MDC.put(SPAN_ID, spanContext.getSpanId());

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(TRACE_ID);
    }
}
