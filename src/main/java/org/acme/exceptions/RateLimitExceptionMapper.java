package org.acme.exceptions;

import org.acme.responses.JsonResponse;

import io.smallrye.faulttolerance.api.RateLimitException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RateLimitExceptionMapper implements ExceptionMapper<RateLimitException> {
    @Override
    public Response toResponse(RateLimitException exception) {
        return Response.status(429)
                       .entity(new JsonResponse(false, "Too Many Requests"))
                       .build();
    }
}