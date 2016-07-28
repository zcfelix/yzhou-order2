package com.thoughtworks.ketsu.support;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserId;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.domain.user.UserRole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestHelper {
    private static int auto_increment_key = 1;

    public static Map<String, Object> productMap(String name) {
        return new HashMap<String, Object>() {{
            put("name", name);
            put("description", "delicious");
            put("price", 2.5);
        }};
    }

    public static Map<String, Object> userMap(String name) {
        return new HashMap<String, Object>() {{
            put("name", name);
        }};
    }

    public static Map<String, Object> orderMap(String name, int productId1, int productId2) {
        List<Map<String, Object>> orderItems = new ArrayList<Map<String, Object>>();
        orderItems.add(0, new HashMap<String, Object>() {{
            put("product_id", productId1);
            put("quantity", 2);
        }});
//        orderItems.add(1, new HashMap<String, Object>() {{
//            put("product_id", productId2);
//            put("product_id", productId2;)
//        }});

        return new HashMap<String, Object>() {{
            put("name", name);
            put("address", "beijing");
            put("phone", "15184452287");
            put("order_items", orderItems);
        }};
    }
}
