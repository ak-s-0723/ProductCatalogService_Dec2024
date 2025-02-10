package org.example.productcatalogservice_dec2024.services;

import org.example.productcatalogservice_dec2024.dtos.UserDto;
import org.example.productcatalogservice_dec2024.models.Product;
import org.example.productcatalogservice_dec2024.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service("sps")
public class StorageProductService implements IProductService {

    @Autowired
    private ProductRepo productRepo;


    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Product getProductById(Long productId) {
        Optional<Product> productOptional = productRepo.findProductById(productId);
        if(productOptional.isEmpty()) return null;

        return productOptional.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product replaceProduct(Long productId, Product request) {
        return null;
    }

    @Override
    public Product save(Product product) {
       return productRepo.save(product);
    }

    @Override
    public Product getProductBasedOnUser(Long productId, Long userId) {
        Optional<Product> productOptional = productRepo.findProductById(productId);
        UserDto userDto = restTemplate.getForEntity("http://userservice/users/{userId}", UserDto.class,userId).getBody();
        System.out.println(userDto.getEmail());

        if(userDto != null) {
            return productOptional.get();
        }

        return  null;
    }
}
