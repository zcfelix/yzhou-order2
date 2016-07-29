package com.thoughtworks.ketsu.domain.order;

import com.thoughtworks.ketsu.domain.payment.Payment;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.PaymentMapper;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.inject.Inject;
import java.util.*;

public class Order implements Record {
    private int id;
    private int userId;
    private String name;
    private String address;
    private String phone;
    private double totalPrice;
    private Date time;
    private List<OrderItem> itemsList;

    public Order() {
    }

    public Order(int id, int userId, String name, String address, String phone, double totalPrice, Date time) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.totalPrice = totalPrice;
        this.time = time;
    }

    @Inject
    PaymentMapper paymentMapper;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public Payment createPayment(Map<String, Object> info) {
        info.put("orderId", id);
        paymentMapper.save(info);
        return paymentMapper.findByOrderId(id);
    }

    public Optional<Payment> findPayment(int orderId) {
        return Optional.ofNullable(paymentMapper.findByOrderId(orderId));
    }

    // 直接插入itemsList, 该变量中会包含oderId, 而结果并不需要, 需要将其去掉。
    @Override
    public Map<String, Object> toJson(Routes routes) {
        List<Map<String, Object>> itemInfoRetList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < itemsList.size(); ++i) {
            itemInfoRetList.add(i, itemsList.get(i).toJson(routes));
        }
        return new HashMap<String, Object>() {{
            put("uri", routes.orderUri(Order.this));
            put("name", name);
            put("address", address);
            put("phone", phone);
            put("total_price", totalPrice);
            put("created_at", time);
            put("order_items", itemInfoRetList);
        }};
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("uri", routes.orderUri(Order.this));
            put("name", name);
            put("address", address);
            put("phone", phone);
            put("total_price", totalPrice);
            put("created_at", time);
        }};
    }
}
