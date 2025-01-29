package org.example.productcatalogservice_dec2024.services;

import org.example.productcatalogservice_dec2024.dtos.SortParam;
import org.example.productcatalogservice_dec2024.dtos.SortType;
import org.example.productcatalogservice_dec2024.models.Product;
import org.example.productcatalogservice_dec2024.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaSearchService implements ISearchService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Page<Product> searchProducts(String searchQuery, int pageSize, int pageNumber, List<SortParam> sortParams) {

        if(sortParams.isEmpty()) {
            return productRepo.findProductsByName(searchQuery,
                    PageRequest.of(pageNumber, pageSize));
        }

        Sort sort = null;
        if(sortParams.get(0).getSortType().equals(SortType.ASC)) {
            sort = Sort.by(sortParams.get(0).getSortCriteria());
        }else {
            sort = Sort.by(sortParams.get(0).getSortCriteria()).descending();
        }


        for(int i=1;i<sortParams.size();i++) {
            if(sortParams.get(i).getSortType().equals(SortType.ASC)) {
                sort = sort.and(Sort.by(sortParams.get(i).getSortCriteria()));
            }else {
                sort = sort.and(Sort.by(sortParams.get(i).getSortCriteria()).descending());
            }
        }

//        Sort sort = Sort.by("price").and(Sort.by("id").descending());
//
//        Page<Product> products = productRepo.findProductsByName(searchQuery,
//                PageRequest.of(pageNumber, pageSize,sort));
//        return products;

        return productRepo.findProductsByName(searchQuery,
                PageRequest.of(pageNumber, pageSize,sort));
    }
}
