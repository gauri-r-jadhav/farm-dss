package com.example.farm.service;

import com.example.farm.entity.Product;
import com.example.farm.exception.FDException;
import com.example.farm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        try {
            logger.debug("Attempting to fetch all products from repository");
            return productRepository.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while fetching products from repository", e);
            throw new FDException("PRODUCT_FETCH_ERROR", "FETCH_ERROR", "Error fetching products from repository", e);
        }
    }

    public Product getProductById(Long id) {
        try {
            logger.debug("Attempting to fetch product with ID: {}", id);
            return productRepository.findById(id)
                    .orElseThrow(() -> new FDException("PRODUCT_NOT_FOUND", "NOT_FOUND", "Product not found"));
        } catch (FDException e) {
            logger.warn("Product not found with ID: {}", id);
            throw e;
        } catch (Exception e) {
            logger.error("Error occurred while fetching product by ID: {}", id, e);
            throw new FDException("PRODUCT_FETCH_ERROR", "FETCH_ERROR", "Error fetching product by id from repository", e);
        }
    }
}
