package org.example.productcatalogservice_dec2024.TableInheritanceExamples.MappedSuperclass;

import jakarta.persistence.*;

import java.util.UUID;

@MappedSuperclass
public abstract class User {
    @Id
    UUID id;
    String email;
}
