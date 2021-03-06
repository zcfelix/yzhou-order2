package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.web.exception.InvalidParameterException;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("users/{userId}/orders")
public class OrdersApi {

    @Context
    UserRepository userRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(Map<String, Object> info,
                                @PathParam("userId") int userId,
                                @Context Routes routes) {
        List<String> invalidParamsList = new ArrayList<>();
        if (info.getOrDefault("name", "").toString().trim().isEmpty())
            invalidParamsList.add("name");
        if (info.getOrDefault("address", "").toString().trim().isEmpty())
            invalidParamsList.add("address");
        if (info.getOrDefault("phone", "").toString().trim().isEmpty())
            invalidParamsList.add("phone");
        if (invalidParamsList.size() > 0)
            throw new InvalidParameterException(invalidParamsList);
        User user = userRepository.findById(userId).get();
        Order order = user.createOrder(info);
        return Response.created(routes.orderUri(order)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> findAllOrders(@PathParam("userId") int userId) {
        User user = userRepository.findById(userId).get();
        return user.findAllOrders();
    }

    @Path("{orderId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Order findOrderById(@PathParam("userId") int userId,
                               @PathParam("orderId") int orderId) {
        User user = userRepository.findById(userId).get();
        return user.findOrderById(orderId).orElseThrow(() -> new NotFoundException("order not found"));
        //return Response.status(200).build();
    }
}
