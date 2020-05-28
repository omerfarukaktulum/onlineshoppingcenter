package com.ofa.epttavm.product.service;

import com.ofa.epttavm.product.entity.Product;

import java.util.List;

public interface ProductService {
    void createOrUpdateProduct(Product product, String username);
    void decreaseProductCount(List<String> productCodes);
    Product findProduct(String code);
    List<Product> findAllProducts();
}
