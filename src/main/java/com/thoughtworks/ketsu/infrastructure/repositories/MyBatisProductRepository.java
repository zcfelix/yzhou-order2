package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.ProductMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MyBatisProductRepository implements ProductRepository {

    @Inject
    ProductMapper productMapper;

    @Override
    public Product createProduct(Map<String, Object> info) {
        productMapper.save(info);
        return productMapper.findById(Integer.valueOf(info.get("id").toString()));
    }

    @Override
    public Optional<Product> findById(int id) {
        return Optional.ofNullable(productMapper.findById(id));
    }

    @Override
    public List<Product> findAllProducts() {
        return productMapper.findAllProducts();
    }
}
