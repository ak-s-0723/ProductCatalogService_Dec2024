package org.example.productcatalogservice_dec2024.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_mentor")
public class Mentor extends User {
    Double ratings;
}
