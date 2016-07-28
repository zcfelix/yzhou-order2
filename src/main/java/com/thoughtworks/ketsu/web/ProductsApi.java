package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.web.exception.InvalidParameterException;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("products")
public class ProductsApi {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(Map<String, Object> productInfo,
                                  @Context Routes routes,
                                  @Context ProductRepository productRepository) {
        List<String> invalidParamsList = new ArrayList<>();
        if (productInfo.getOrDefault("name", "").toString().trim().isEmpty())
            invalidParamsList.add("name");
        if (productInfo.getOrDefault("description", "").toString().trim().isEmpty())
            invalidParamsList.add("description");
        if (productInfo.getOrDefault("price", "").toString().trim().isEmpty())
            invalidParamsList.add("price");
        if (invalidParamsList.size() > 0)
            throw new InvalidParameterException(invalidParamsList);
        return Response.created(routes.productUri(productRepository.createProduct(productInfo))).build();
    }

}
