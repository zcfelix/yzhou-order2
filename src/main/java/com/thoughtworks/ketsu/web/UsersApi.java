package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.*;
import com.thoughtworks.ketsu.web.exception.InvalidParameterException;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("users")
public class UsersApi {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(Map<String, Object> userInfo,
                               @Context UserRepository userRepository,
                               @Context Routes routes) {
        List<String> invalidParamsList = new ArrayList<>();
        if (userInfo.getOrDefault("name", "").toString().trim().isEmpty())
            invalidParamsList.add("name");
        if (invalidParamsList.size() > 0)
            throw new InvalidParameterException(invalidParamsList);
        return Response.created(routes.userUri(userRepository.createUser(userInfo))).status(201).build();
    }

    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User findUserById(@PathParam("userId") int userId,
                                 @Context UserRepository userRepository,
                                 @Context Routes routes) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));
    }

}
