package org.example.productcatalogservice_dec2024.services;

import org.example.productcatalogservice_dec2024.clients.FakeStoreApiClient;
import org.example.productcatalogservice_dec2024.dtos.FakeStoreProductDto;
import org.example.productcatalogservice_dec2024.models.Category;
import org.example.productcatalogservice_dec2024.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//TODO : Rearrange code to Client layer

@Service("fkps")
@Primary
public class FakeStoreProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private FakeStoreApiClient fakeStoreApiClient;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


//    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplateBuilder = restTemplateBuilder;
//    }

    public Product getProductById(Long productId) {
        //if (found in redis)
        //   return
        //else
        //   call fakestore
        //   cache it
        //   return it
        FakeStoreProductDto fakeStoreProductDto = null;

        fakeStoreProductDto = (FakeStoreProductDto) redisTemplate.opsForHash().get("products",productId);

        if(fakeStoreProductDto == null) {
            System.out.println("got from fakestore");
            fakeStoreProductDto = fakeStoreApiClient.getProductById(productId);
            redisTemplate.opsForHash().put("products",productId,fakeStoreProductDto);
        } else {
            System.out.println("got from redis");
        }

        return from(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> listResponseEntity =
                restTemplate.getForEntity("http://fakestoreapi.com/products",
                        FakeStoreProductDto[].class);

        for(FakeStoreProductDto fakeStoreProductDto : listResponseEntity.getBody()) {
         products.add(from(fakeStoreProductDto));
        }

        return products;
    }

    @Override
    public Product replaceProduct(Long productId, Product request) {
        FakeStoreProductDto fakeStoreProductDtoRequest = from(request);
        FakeStoreProductDto response =
                requestForEntity("http://fakestoreapi.com/products/{productId}",HttpMethod.PUT,fakeStoreProductDtoRequest, FakeStoreProductDto.class,productId).getBody();

        return from(response);
    }

    //ToDo: To be done by learners
    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Product getProductBasedOnUser(Long productId, Long userId) {
        return null;
    }

    private <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    private FakeStoreProductDto from(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        if(product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
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
