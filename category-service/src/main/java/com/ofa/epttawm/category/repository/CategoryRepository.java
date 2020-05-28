package com.ofa.epttawm.category.repository;

import com.ofa.epttawm.category.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByCode(String code);
    List<Category> findAll();
}
