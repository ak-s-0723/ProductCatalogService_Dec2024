package org.example.productcatalogservice_dec2024.services;

import org.example.productcatalogservice_dec2024.models.Product;

public interface IProductService {
     Product getProductById(Long productId);
}
