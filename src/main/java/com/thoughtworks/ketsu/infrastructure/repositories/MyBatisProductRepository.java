package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.ProductMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class MyBatisProductRepository implements ProductRepository {

    @Inject
    ProductMapper productMapper;

    @Override
    public Product createProduct(Map<String, Object> info) {
        productMapper.save(info);
        return productMapper.findById(Integer.valueOf(info.get("id").toString()));
    }

    @Override
    public Product findById(int id) {
        return productMapper.findById(id);
    }

    @Override
    public List<Product> findAllProducts() {
        return productMapper.findAllProducts();
    }
}
