package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.List;

import static com.sun.tools.doclint.Entity.ne;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(DatabaseTestRunner.class)
public class MyBatisProductRepositoryTest {

    @Inject
    ProductRepository productRepository;

    @Test
    public void should_save_and_find_product_by_id() {
        Product product = productRepository.createProduct(TestHelper.productMap("apple"));
        assertThat(product.getName(), is("apple"));
    }

    @Test
    public void should_get_product_by_id() {
        Product product = productRepository.createProduct(TestHelper.productMap("apple"));
        Product product_get = productRepository.findById(product.getId());
        assertThat(product_get.getId(), is(product.getId()));
    }

    @Test
    public void should_get_all_products() {
        Product product1 = productRepository.createProduct(TestHelper.productMap("apple"));
        Product product2 = productRepository.createProduct(TestHelper.productMap("duck"));
        List<Product> ret = productRepository.findAllProducts();
        assertThat(ret.size(), is(2));
    }

}
