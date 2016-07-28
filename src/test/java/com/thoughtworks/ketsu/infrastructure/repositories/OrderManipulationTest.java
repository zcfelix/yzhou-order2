package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(DatabaseTestRunner.class)
public class OrderManipulationTest {

    private User user;
    private Product product1, product2;
    private String ordersBaseUri;

    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
        user = userRepository.createUser(TestHelper.userMap("felix"));
        product1 = productRepository.createProduct(TestHelper.productMap("apple"));
        product2 = productRepository.createProduct(TestHelper.productMap("duck"));
        ordersBaseUri = "users/" + user.getId() + "/orders/";
    }


    @Test
    public void should_create_an_order_without_items_find_order_by_id() {
        Map<String, Object> orderInfo = TestHelper.orderMap("felix", product1.getId(), product2.getId());
        user.createOrder(orderInfo);
        int orderId = Integer.valueOf(orderInfo.get("id").toString());
        Optional<Order> orderOptional = user.findOrderById(orderId);
        assertThat(orderOptional.get().getId(), is(Integer.valueOf(orderInfo.get("id").toString())));
    }
}
