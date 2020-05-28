package com.ofa.epttavm.product.controller;

import com.ofa.epttavm.product.dto.CustomHttpResponse;
import com.ofa.epttavm.product.entity.Product;
import com.ofa.epttavm.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="Product Service", tags = {"product-service"})
@SwaggerDefinition(tags = {@Tag(
        name = "product-service",
        description = "Product-related operations are handled in this service.")
})
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "Create new product or update existing product (only by Admin).")
    @PostMapping("/")
    public @ResponseBody CustomHttpResponse createOrUpdate(@RequestBody final Product product, @RequestParam final String username) {
        productService.createOrUpdateProduct(product, username);
        return new CustomHttpResponse(HttpStatus.OK, "Product is created/updated!");
    }

    @ApiOperation(value = "List all products.")
    @GetMapping("/")
    public List<Product> getAll() {
        return productService.findAllProducts();
    }

    @ApiOperation(value = "List a product with given code.")
    @GetMapping("/{code}")
    public Product get(@PathVariable(name = "code") final String code) {
        return productService.findProduct(code);
    }
}