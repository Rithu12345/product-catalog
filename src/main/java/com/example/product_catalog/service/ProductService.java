package com.example.product_catalog.service;

import java.util.stream.Collectors;
import com.example.product_catalog.entity.Category;
import com.example.product_catalog.entity.Product;
import com.example.product_catalog.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product p) {
        return productRepository.save(p);

    }

    public Product getById(Long id) {

        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with the id: " + id));

        return p;

    }

    public Set<String> uniqueCategory() {

        return productRepository.findAll()
                .stream()
                .map(Product::getCategory)
                .collect(Collectors.toSet());
    }

    public Map<Category, List<Product>> groupProductByCategory() {

        return productRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Product::getCategory));

    }

}
