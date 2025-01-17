package com.epam.gymappmainservice.global;

import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.CurrentTraceContext;
import io.micrometer.tracing.TraceContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CorrelationIDHandler {
    private final Tracer tracer;

    public CorrelationIDHandler(Tracer tracer) {
        this.tracer = tracer;
    }

    public String getCorrelationId() {
        return Optional.of(tracer)
                       .map(Tracer::currentTraceContext)
                       .map(CurrentTraceContext::context)
                       .map(TraceContext::traceId)
                       .orElse("");
    }
}
