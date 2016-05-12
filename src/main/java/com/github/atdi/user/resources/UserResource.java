package com.github.atdi.user.resources;

import com.github.atdi.user.model.User;
import com.github.atdi.user.services.repositories.UserRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * Created by aurelavramescu on 11/05/16.
 */
@Path("user")
public class UserResource {

    private final UserRepository userRepository;

    @Inject
    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response save(@Size(min = 1) @NotNull @FormParam("firstName") String firstName,
                         @Size(min = 1) @NotNull @FormParam("lastName") String lastName,
                         @Size(min = 1) @NotNull @FormParam("email") String email,
                         @Size(min = 1) @NotNull @FormParam("password") String password) {
        User user = userRepository.save(User.builder()
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .build());
        return Response.created(URI.create(String.format("user/%d", user.getId()))).build();
    }

    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PUT
    public Response update(@PathParam("id") Long id,
                           @Valid User user) {
        if (userRepository.findOne(id) == null) {
            throw new NotFoundException("User not found");
        }
        User saved = userRepository.save(user);
        return Response.ok(saved).build();
    }

    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response get(@PathParam("id") Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new NotFoundException(String.format("Entity with id %s not found", id));
        }
        return Response.ok(user)
                .build();
    }



}
