package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(ApiTestRunner.class)

public class ProductsApiTest extends ApiSupport{

    @Test
    public void should_return_201_when_create_a_product() {
        final Response POST = post("products", TestHelper.productMap("apple"));
        assertThat(POST.getStatus(), is(201));
        assertThat(Pattern.matches(".*?/products/[0-9-]*", POST.getLocation().toASCIIString()), is(true));
    }

    @Test
    public void should_return_400_when_create_a_product_with_some_missed_parameters() {
        final Response POST = post("products", TestHelper.productMap(""));
        assertThat(POST.getStatus(), is(400));
    }
}
