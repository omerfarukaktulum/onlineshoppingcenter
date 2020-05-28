package com.ofa.epttawm.category.service;

import com.ofa.epttawm.category.client.UserClient;
import com.ofa.epttawm.category.dto.User;
import com.ofa.epttawm.category.dto.UserRole;
import com.ofa.epttawm.category.entity.Category;
import com.ofa.epttawm.category.exceptions.CategoryCodeAlreadyExistsException;
import com.ofa.epttawm.category.exceptions.UserNotAllowedForOperationException;
import com.ofa.epttawm.category.exceptions.UserSessionClosedException;
import com.ofa.epttawm.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserClient userClient;

    @Autowired
    public CategoryServiceImpl(final CategoryRepository categoryRepository, final UserClient userClient) {
        this.categoryRepository = categoryRepository;
        this.userClient = userClient;
    }

    /**
     * Call UserService to get user details.
     * Decide whether the user is elective to create category or not.
     * Check user's session, and category code to avoid duplicate category credentials.
     * @param category
     * @param username
     */
    @Override
    public void createOrUpdateCategory(Category category, String username) {
        User user = userClient.retrieveUserDetailsByUsername(username);
        if (user != null && user.getRole() == UserRole.ADMIN) {
            if (!user.getLoggedIn()) {
                throw new UserSessionClosedException();
            } else if (categoryRepository.findByCode(category.getCode()) != null) {
                throw new CategoryCodeAlreadyExistsException();
            } else {
                categoryRepository.save(category);
            }
        } else {
            throw new UserNotAllowedForOperationException();
        }
    }

    @Override
    public void updateCategoryCount(String code, Integer count) {
        Category category = categoryRepository.findByCode(code);
        category.setCount(category.getCount() + count);
        categoryRepository.save(category);
    }

    @Override
    public Category findCategory(String code) {
        return categoryRepository.findByCode(code);
    }

    @Override
    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }
}
