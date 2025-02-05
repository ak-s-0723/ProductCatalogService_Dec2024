package org.example.productcatalogservice_dec2024.services;

import org.example.productcatalogservice_dec2024.models.Product;

import java.util.List;

public interface IProductService {
     Product getProductById(Long productId);

     List<Product> getAllProducts();

     Product replaceProduct(Long productId,Product request);

     Product save(Product product);

     Product getProductBasedOnUser(Long productId,Long userId);
}
