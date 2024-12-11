package org.example.productcatalogservice_dec2024.controllers;

import org.example.productcatalogservice_dec2024.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<Product> getAllProducts() {
        Product product = new Product();
        product.setId(2L);
        product.setName("Iphone");
        List<Product> products = new ArrayList<>();
        products.add(product);
        return  products;
    }


    @GetMapping("{productId}")
    public Product findProductById(@PathVariable Long productId) {
        Product product = new Product();
        product.setId(productId);
        return product;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return product;
    }

}
