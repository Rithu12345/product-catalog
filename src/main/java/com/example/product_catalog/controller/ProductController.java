package com.example.product_catalog.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.product_catalog.entity.Category;
import com.example.product_catalog.service.ProductService;

import com.example.product_catalog.entity.Product;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product p) {

        Product temp = productService.addProduct(p);
        return new ResponseEntity<>(temp, HttpStatus.CREATED);

    }

    @GetMapping("/{product_id}")
    public ResponseEntity<Product> getProductById(@PathVariable("product_id") Long id) {

        Product temp = productService.getById(id);
        return ResponseEntity.ok(temp);
    }

    @GetMapping("/categories")
    public ResponseEntity<Set<String>> getUniqueCategories() {

        Set<String> category = productService.uniqueCategory();
        return ResponseEntity.ok(category);
    }

    @GetMapping("group-category")
    public ResponseEntity<Map<Category, List<Product>>> groupProductByCategory() {

        Map<Category, List<Product>> p = productService.groupProductByCategory();
        return ResponseEntity.ok(p);

    }

}
