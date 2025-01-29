package org.example.productcatalogservice_dec2024.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SortParam {
    private SortType sortType;
    private String sortCriteria; //"id" or "price"

}
