package com.ofa.epttawm.category.controller;

import com.ofa.epttawm.category.entity.Category;
import com.ofa.epttawm.category.dto.CustomHttpResponse;
import com.ofa.epttawm.category.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="Category Service", tags = {"category-service"})
@SwaggerDefinition(tags = {@Tag(
        name = "category-service",
        description = "Category-related operations are handled in this service.")
})
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "Create new category or update existing category (only by Admin).")
    @PostMapping("/")
    public @ResponseBody
    CustomHttpResponse createOrUpdate(@RequestBody final Category category, @RequestParam final String username) {
        categoryService.createOrUpdateCategory(category, username);
        return new CustomHttpResponse(HttpStatus.OK, "Category is created/updated!");
    }

    @ApiOperation(value = "List all categories.")
    @GetMapping("/")
    public List<Category> getAll() {
        return categoryService.findCategories();
    }

    @ApiOperation(value = "List a category with given code.")
    @GetMapping("/{code}")
    public Category get(@PathVariable(value = "code") final String code) {
        return categoryService.findCategory(code);
    }
}