package com.thoughtworks.ketsu.domain.product;

import java.util.Map;

public interface ProductRepository {
    Product createProduct(Map<String, Object> info);

    Product findById(int id);
}

