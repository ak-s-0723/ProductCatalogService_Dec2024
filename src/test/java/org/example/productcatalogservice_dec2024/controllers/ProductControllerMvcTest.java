package org.example.productcatalogservice_dec2024.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.productcatalogservice_dec2024.dtos.ProductDto;
import org.example.productcatalogservice_dec2024.models.Product;
import org.example.productcatalogservice_dec2024.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {

    @MockBean
    private IProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void Test_GetAllProducts_RunsSuccessfully() throws Exception {
        //Arrange
        Product product1 = new Product();
        product1.setName("Iphone12");
        product1.setId(1L);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Macbook");
        product2.setPrice(1212121D);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        when(productService.getAllProducts()).thenReturn(productList);

        ProductDto productDto1 = new ProductDto();
        productDto1.setName("Iphone12");
        productDto1.setId(1L);

        ProductDto productDto2 = new ProductDto();
        productDto2.setId(2L);
        productDto2.setName("Macbook");
        productDto2.setPrice(1212121D);

        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(productDto1);
        productDtos.add(productDto2);

       mockMvc.perform(get("/products"))
               .andExpect(status().isOk())
               .andExpect(content()
                       .string(objectMapper.writeValueAsString(productDtos)))
               .andExpect(jsonPath("$.length()").value(2))
               .andExpect(jsonPath("$[0].name").value("Iphone12"))
               .andExpect(jsonPath("$[1].length()").value(3));
               //.andExpect(jsonPath("$[1].name.length()").value(7));   //this doesn't work
    }


    @Test
    public void Test_CreateProduct_RunsSuccessfully() throws Exception {
        //Arrange
        Product product = new Product();
        product.setId(5L);
        product.setName("Ipad");

        when(productService.save(any(Product.class))).thenReturn(product);

        ProductDto productDto = new ProductDto();
        productDto.setId(5L);
        productDto.setName("Ipad");

        //Act and Assert
        mockMvc.perform(post("/products").content(
                objectMapper.writeValueAsString(productDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content().string(objectMapper.writeValueAsString(productDto)))
                .andExpect(jsonPath("$.id").value(product.getId()))
                .andExpect(jsonPath("$.name").value(product.getName()));

    }
}


//{
//   "id" : 5,
//   "name" : "Ipad"
//}