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

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(ApiTestRunner.class)
public class PaymentsApiTest extends ApiSupport{

    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    private User user;
    private Product product1, product2;
    private Order order;
    private String paymentBaseUri;

    @Override
    @Before
    public void setUp() throws Exception{
        super.setUp();
        user = userRepository.createUser(TestHelper.userMap("felix"));
        product1 = productRepository.createProduct(TestHelper.productMap("pig"));
        product2 = productRepository.createProduct(TestHelper.productMap("dog"));
        order = user.createOrder(TestHelper.orderMap("kitty", product1.getId(), product2.getId()));
        paymentBaseUri = "users/" + user.getId() + "/orders/" + order.getId() + "/payment";
    }

    @Test
    public void should_return_201_when_create_a_payment() {
        final Response POST = post(paymentBaseUri, TestHelper.paymentMap());
        assertThat(POST.getStatus(), is(201));
    }

    @Test
    public void should_return_400_when_create_a_payment_with_missing_params() {
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("pay_type", "");
            put("amount", 1234d);
        }};
        final Response POST = post(paymentBaseUri, map);
        assertThat(POST.getStatus(), is(400));
    }

    @Test
    public void should_return_200_when_get_a_payment() {
        //post(paymentBaseUri, TestHelper.paymentMap());
        order.createPayment(TestHelper.paymentMap());
        final Response GET = get(paymentBaseUri);
        assertThat(GET.getStatus(), is(200));
    }

    @Test
    public void should_return_200_and_details_when_get_a_payment() {
        order.createPayment(TestHelper.paymentMap());
        final Response GET = get(paymentBaseUri);
        final Map<String, Object> RET = GET.readEntity(Map.class);

        assertThat(GET.getStatus(), is(200));
        assertThat(RET.get("pay_type"), is("CASH"));
        assertThat(RET.get("order_uri"), is("/users/" + user.getId() + "/orders/" + order.getId()));
        assertThat(RET.get("uri"), is("/users/" + user.getId() + "/orders/" + order.getId() + "/payment"));
    }
}
