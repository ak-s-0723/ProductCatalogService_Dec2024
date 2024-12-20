package org.example.productcatalogservice_dec2024.TableInheritanceExamples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="st_ta")
@DiscriminatorValue(value = "10")
public class Ta extends User {
    int helpRequests;
}
