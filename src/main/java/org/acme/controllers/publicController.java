package org.acme.controllers;

import java.util.List;

import org.acme.exceptions.ValidationException;
import org.acme.models.user;
import org.acme.responses.JsonResponse;
import org.acme.responses.actionResponse;
import org.acme.security.jwtSecurity;
import org.acme.services.userService;
import org.acme.validations.validate_object;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/public")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class publicController {

    @Inject
    private userService userService;

    @POST
    @Transactional
    @Path("/createUser")
    // @RolesAllowed({ "User", "Admin" })
    public Response createUser(user user) {
        try {
           
            List<String> errorMessages = validate_object.validate(user);
            
            if (errorMessages != null) {
                throw new ValidationException(String.join(", ", errorMessages));
            }

            userService.createUser(user);

            return Response.status(Response.Status.CREATED)
                    .entity(new JsonResponse(true, "User created successfully", user)).build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new JsonResponse(false, e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/hello/public")
    @PermitAll
    public Response sayHello() {
        return Response.status(Response.Status.OK)
                .entity(new JsonResponse(true, "public hello")).build();
    }

    @GET
    @Path("/hello/user")
    @RolesAllowed({ "User" })
    public Response sayHelloUser() {
        return Response.status(Response.Status.OK)
                .entity(new JsonResponse(true, "user hello")).build();
    }

    @GET
    @Path("/hello/admin")
    @RolesAllowed({ "Admin" })
    public Response sayHelloAdmin() {
        return Response.status(Response.Status.OK)
                .entity(new JsonResponse(true, "admin hello")).build();
    }

    @GET
    @Path("/helloWithToken")
    @RolesAllowed({ "User", "Admin" })
    // @PermitAll
    public Response sayHelloWithToken() {
        return Response.status(Response.Status.OK)
                .entity(new JsonResponse(true,
                        "Hello in roles allowed user and admin (" + actionResponse.SUCCESS.label + ")"))
                .build();
    }


}