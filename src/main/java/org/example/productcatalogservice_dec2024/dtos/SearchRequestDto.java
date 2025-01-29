package org.example.productcatalogservice_dec2024.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchRequestDto {
    int pageSize;
    int pageNumber;
    String searchQuery;
    List<SortParam> sortParams = new ArrayList<>();
}
