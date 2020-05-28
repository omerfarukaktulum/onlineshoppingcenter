package com.ofa.epttavm.product.repository;

import com.ofa.epttavm.product.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByCode(String code);
    List<Product> findAll();
}
