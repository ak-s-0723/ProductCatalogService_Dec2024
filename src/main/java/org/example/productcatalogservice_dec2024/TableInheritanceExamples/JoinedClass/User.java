package org.example.productcatalogservice_dec2024.TableInheritanceExamples.JoinedClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import java.util.UUID;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name="jc_user")
public class User {
    @Id
    UUID id;
    String email;
}
