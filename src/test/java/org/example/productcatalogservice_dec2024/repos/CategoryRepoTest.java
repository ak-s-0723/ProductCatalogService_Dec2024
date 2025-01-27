package org.example.productcatalogservice_dec2024.repos;

import jakarta.transaction.Transactional;
import org.example.productcatalogservice_dec2024.models.Category;
import org.example.productcatalogservice_dec2024.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    //@Test
    //@Transactional
    void testFetchTypes() {
        Category category = categoryRepo.findById(22L).get();
        for(Product product  : category.getProducts()) {
            System.out.println(product.getName());
        }
    }


    //@Test
    //@Transactional
    void testSomething() {
      List<Category> categoryList = categoryRepo.findAll();
      for(Category category :  categoryList) {
          for(Product p : category.getProducts()) {
              System.out.println(p.getName());
          }
      }
    }
}