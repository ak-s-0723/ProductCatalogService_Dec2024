package org.example.productcatalogservice_dec2024.controllers;

import org.example.productcatalogservice_dec2024.dtos.CategoryDto;
import org.example.productcatalogservice_dec2024.dtos.ProductDto;
import org.example.productcatalogservice_dec2024.models.Category;
import org.example.productcatalogservice_dec2024.models.Product;
import org.example.productcatalogservice_dec2024.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<ProductDto> productDtos = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        for(Product product  : products) {
            productDtos.add(from(product));
        }

        return productDtos;
    }


    @GetMapping("{productId}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable Long productId) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        if(productId <= 0) {
            headers.add("called by","bhudwak");
            return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        }

        Product product = productService.getProductById(productId);
        headers.add("called by","intelligent");
      if(product == null) return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
      return new ResponseEntity<>(from(product),headers, HttpStatus.OK);
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

    //HW -: TO BE DONE BY LEARNERS
    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto product) {
       return null;
    }

    @PutMapping("/{id}")
    public ProductDto replaceProduct(@PathVariable Long id,@RequestBody ProductDto request) {
     Product productRequest = from(request);
     Product product  = productService.replaceProduct(id,productRequest);
     return  from(product);
    }

    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setName(productDto.getCategory().getName());
            product.setCategory(category);
        }
        return product;
    }

}
