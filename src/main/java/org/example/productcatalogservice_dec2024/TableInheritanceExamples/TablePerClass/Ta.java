package org.example.productcatalogservice_dec2024.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_ta")
public class Ta extends User {
    int helpRequests;
}
