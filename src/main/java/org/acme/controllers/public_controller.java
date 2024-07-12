package org.acme.controllers;

import java.util.List;

import org.acme.exceptions.ValidationException;
import org.acme.models.user;
import org.acme.responses.JsonResponse;
import org.acme.services.user_service;
import org.acme.validations.validate_object;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/public")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class public_controller {

    @Inject
    private user_service user_service;


    @POST
    @Transactional
    public Response createUser(user user) {
        try {
            //List<String> errorMessages = user_service.validateUser(user);
            List<String> errorMessages = validate_object.validate(user); // Access static method correctly
            if (errorMessages != null) {
                throw new ValidationException(String.join(", ", errorMessages));
            }
            /* if (errorMessages != null) {
                throw new IllegalArgumentException(String.join(", ", errorMessages));
            } */
            user_service.create_user(user);
            return Response.status(Response.Status.CREATED)
                    .entity(new JsonResponse(true, "User created successfully", user)).build();
           
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
            .entity(new JsonResponse(false, e.getMessage()))
            .build();
        }
    }

}