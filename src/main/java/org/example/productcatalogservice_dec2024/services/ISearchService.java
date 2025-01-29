package org.example.productcatalogservice_dec2024.services;

import org.example.productcatalogservice_dec2024.dtos.SortParam;
import org.example.productcatalogservice_dec2024.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchService {

    Page<Product> searchProducts(String searchQuery, int pageSize, int pageNumber, List<SortParam> sortParams);
}
