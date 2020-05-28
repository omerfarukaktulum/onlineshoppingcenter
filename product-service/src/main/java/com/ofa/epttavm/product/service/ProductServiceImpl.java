package com.ofa.epttavm.product.service;

import com.ofa.epttavm.product.client.UserClient;
import com.ofa.epttavm.product.dto.User;
import com.ofa.epttavm.product.dto.UserRole;
import com.ofa.epttavm.product.entity.Product;
import com.ofa.epttavm.product.exceptions.ProductDoesNotExistException;
import com.ofa.epttavm.product.exceptions.UserLoginOrRegisterException;
import com.ofa.epttavm.product.exceptions.UserNotAllowedForOperationException;
import com.ofa.epttavm.product.messaging.MessagePublisher;
import com.ofa.epttavm.product.messaging.ProductUpdatedMessage;
import com.ofa.epttavm.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserClient userClient;
    private final MessagePublisher messagePublisher;

    @Autowired
    public ProductServiceImpl(final ProductRepository productRepository,
                              final UserClient userClient,
                              final MessagePublisher messagePublisher) {
        this.productRepository = productRepository;
        this.userClient = userClient;
        this.messagePublisher = messagePublisher;
    }

    /**
     * Call UserService to get user details.
     * Decide whether the user is elective to operation, and check user's session.
     * If it exists, update it. Otherwise, create it as a new product.
     * Finally, publish a message to update count of related category.
     * @param product
     * @param username
     */
    @Override
    public void createOrUpdateProduct(Product product, String username) {
        if (product == null) {
            throw new ProductDoesNotExistException();
        }

        User user = userClient.retrieveUserDetailsByUsername(username);
        Product existingProduct = productRepository.findByCode(product.getCode());
        Integer stockCountDiff = product.getStockCount();
        if (user != null && user.getRole() == UserRole.ADMIN) {
            if (!user.getLoggedIn()) {
                throw new UserLoginOrRegisterException();
            } else if (existingProduct != null) {
                stockCountDiff -= existingProduct.getStockCount();
                existingProduct.setPrice(product.getPrice());
                existingProduct.setStockCount(product.getStockCount());
                productRepository.save(existingProduct);
            } else {
                productRepository.save(product);
            }

            messagePublisher.sendCategoryCountUpdateMessage(
                    new ProductUpdatedMessage(product.getCode(), stockCountDiff));
        } else {
            throw new UserNotAllowedForOperationException();
        }
    }

    @Override
    public void decreaseProductCount(List<String> productCodes) {
        for (String code : productCodes) {
            Product product = productRepository.findByCode(code);
            if (product == null) {
                throw new ProductDoesNotExistException();
            }
            product.setStockCount(product.getStockCount()-1);
            productRepository.save(product);
        }
    }

    @Override
    public Product findProduct(String code) {
        return productRepository.findByCode(code);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
}
