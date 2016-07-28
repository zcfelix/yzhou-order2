package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.OrderMapper;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class User implements Record {

    @Inject
    OrderMapper orderMapper;

    private int id;
    private String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Order createOrder(Map<String, Object> orderInfo) {
        orderInfo.put("userId", id);
        orderMapper.save(orderInfo);
        return orderMapper.findById(Integer.valueOf(orderInfo.get("id").toString()));
    }

    public Optional<Order> findOrderById(int orderId) {
        return Optional.ofNullable(orderMapper.findById(orderId));
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("id", id);
            put("name", name);
            put("uri", routes.userUri(User.this));
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }

}
