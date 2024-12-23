package org.example.productcatalogservice_dec2024.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Product extends BaseModel {
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    private Boolean isPrime;

    public Product() {
        setCreatedAt(new Date());
        setLastUpdatedAt(new Date());
        setState(State.ACTIVE);
    }
}
