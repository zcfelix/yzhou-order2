package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(ApiTestRunner.class)

public class ProductsApiTest extends ApiSupport{

    @Inject
    ProductRepository productRepository;

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

    @Test
    public void should_return_200_when_get_all_products () {
        Product product1 = productRepository.createProduct(TestHelper.productMap("duck"));
        Product product2 = productRepository.createProduct(TestHelper.productMap("apple"));
        final Response GET = get("products");
        final List<Map<String, Object>> RET_INFO = GET.readEntity(List.class);
        assertThat(GET.getStatus(), is(200));
        assertThat(RET_INFO.size(), is(2));
        assertThat(RET_INFO.get(0).get("name"), is("duck"));
        assertThat(RET_INFO.get(1).get("name"), is("apple"));
    }

    @Test
    public void should_return_200_when_get_product() {
        Product product = productRepository.createProduct(TestHelper.productMap("duck"));
        final Response GET = get("products/" + product.getId());
        assertThat(GET.getStatus(), is(200));
        final Map<String, Object> RET_INFO = GET.readEntity(Map.class);
        assertThat(RET_INFO.get("name"), is("duck"));
        assertThat(RET_INFO.get("uri"), is("/products/" + product.getId()));
    }
}
