package org.example.productcatalogservice_dec2024.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class UserDto {
    private Long id;
    private String email;
   // private List<Role> roles = new ArrayList();
}
