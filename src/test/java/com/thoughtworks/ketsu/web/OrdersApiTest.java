package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Before;
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
public class OrdersApiTest extends ApiSupport{

    private User user;
    private Product product1, product2;
    private String ordersBaseUri;

    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        user = userRepository.createUser(TestHelper.userMap("felix"));
        product1 = productRepository.createProduct(TestHelper.productMap("apple"));
        product2 = productRepository.createProduct(TestHelper.productMap("duck"));
        ordersBaseUri = "users/" + user.getId() + "/orders/";
    }

    @Test
    public void should_return_201_when_create_an_order() {
        final Response POST = post(ordersBaseUri, TestHelper.orderMap("felix", product1.getId(), product2.getId()));
        assertThat(POST.getStatus(), is(201));
        assertThat(Pattern.matches(".*?/users/[0-9-]*/orders/[0-9-]*", POST.getLocation().toASCIIString()), is(true));
    }

    @Test
    public void should_return_400_when_create_an_order_with_missing_params() {
        final Response POST = post(ordersBaseUri, TestHelper.orderMap("", product1.getId(), product2.getId()));
        assertThat(POST.getStatus(), is(400));
    }


    @Test
    public void should_return_200_and_orders_list_when_get_all_orders() {
        post(ordersBaseUri, TestHelper.orderMap("felix", product1.getId(), product2.getId()));
        post(ordersBaseUri, TestHelper.orderMap("kitty", product1.getId(), product2.getId()));
        final Response GET = get(ordersBaseUri);
        final List<Map<String, Object>> RET = GET.readEntity(List.class);

        assertThat(GET.getStatus(), is(200));
        assertThat(RET.size(), is(2));
        assertThat(RET.get(0).get("name"), is("felix"));
        assertThat(RET.get(1).get("name"), is("kitty"));
    }

    @Test
    public void should_return_200_and_order_details_when_get_an_order() {
        Order order = user.createOrder(TestHelper.orderMap("kitty", product1.getId(), product2.getId()));
        final Response GET = get(ordersBaseUri + order.getId());
        final Map<String, Object> RET = GET.readEntity(Map.class);
        assertThat(GET.getStatus(), is(200));
        assertThat(RET.get("name"), is("kitty"));
        assertThat(RET.get("total_price"), is(1007.5));
        assertThat(RET.get("uri"), is("/users/" + user.getId() + "/orders/" + order.getId()));
    }

    @Test
    public void should_return_404_when_order_not_found() {
        Order order = user.createOrder(TestHelper.orderMap("kitty", product1.getId(), product2.getId()));
        final Response GET = get(ordersBaseUri + (order.getId() + 1));
        assertThat(GET.getStatus(), is(404));
    }

}
