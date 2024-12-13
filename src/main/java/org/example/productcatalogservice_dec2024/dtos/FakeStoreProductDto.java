package org.example.productcatalogservice_dec2024.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
}
