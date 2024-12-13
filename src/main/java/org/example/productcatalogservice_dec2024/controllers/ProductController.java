package org.example.productcatalogservice_dec2024.controllers;

import org.example.productcatalogservice_dec2024.dtos.CategoryDto;
import org.example.productcatalogservice_dec2024.dtos.ProductDto;
import org.example.productcatalogservice_dec2024.models.Product;
import org.example.productcatalogservice_dec2024.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

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
    public ProductDto findProductById(@PathVariable Long productId) {
      Product product = productService.getProductById(productId);
      return from(product);
    }

    private ProductDto from (Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return product;
    }

}
