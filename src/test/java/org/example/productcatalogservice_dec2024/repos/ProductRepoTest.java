package org.example.productcatalogservice_dec2024.repos;

import org.example.productcatalogservice_dec2024.models.Category;
import org.example.productcatalogservice_dec2024.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;


    @Test
    public void insertDataInRDS() {
        Product product1 = new Product();
        product1.setName("Pen");
        product1.setPrice(10D);
        product1.setId(1L);

        Product product2 = new Product();
        product2.setName("MacBook M1");
        product2.setPrice(100000D);
        product2.setId(2L);
        productRepo.save(product1);
        productRepo.save(product2);
    }

    //@Test
    public void testJpa() {
//      List<Product> productList = productRepo.findProductByOrderByPrice();
//      for(Product product : productList) {
//          System.out.println(product.getPrice());
//      }

        System.out.println(productRepo.findProductTitleById(25L));

        System.out.println(productRepo.findCategoryNameFromProductId(25L));
    }


}