package org.example.productcatalogservice_dec2024.services;

import org.example.productcatalogservice_dec2024.dtos.FakeStoreProductDto;
import org.example.productcatalogservice_dec2024.models.Category;
import org.example.productcatalogservice_dec2024.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;


//    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplateBuilder = restTemplateBuilder;
//    }

    public Product getProductById(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        //restTemplateBuilder.rootUri("http://fakestoreapi.com/");
        FakeStoreProductDto fakeStoreProductDto =  restTemplate.getForEntity("http://fakestoreapi.com/products/{productId}", FakeStoreProductDto.class,productId).getBody();
        return from(fakeStoreProductDto);
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }


}
