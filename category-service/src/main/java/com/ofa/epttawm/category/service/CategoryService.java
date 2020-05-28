package com.ofa.epttawm.category.service;

import com.ofa.epttawm.category.entity.Category;

import java.util.List;

public interface CategoryService {
    void createOrUpdateCategory(Category category, String username);
    void updateCategoryCount(String code, Integer count);
    Category findCategory(String code);
    List<Category> findCategories();
}
