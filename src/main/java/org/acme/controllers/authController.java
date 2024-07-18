package org.acme.controllers;

import org.acme.models.user;
import org.acme.responses.JsonResponse;
import org.acme.responses.actionResponse;
import org.acme.services.authService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api/v1/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class authController {

    @Inject
    authService authService;

    @POST
    @Path("/login")
    public Response login(user user) {
        try {
            String token = authService.login(user);
            return Response.ok(new JsonResponse(true, actionResponse.LOGIN.label, token)).build();
        } catch (Exception e) {
            JsonResponse errorResponse = new JsonResponse(false, e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
        }
    }
}
