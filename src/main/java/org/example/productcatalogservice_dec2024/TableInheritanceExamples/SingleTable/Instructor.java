package org.example.productcatalogservice_dec2024.TableInheritanceExamples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="st_instructor")
@DiscriminatorValue(value = "30")
public class Instructor extends User {
    String company;
}
