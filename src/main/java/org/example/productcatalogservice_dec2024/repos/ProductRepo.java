package org.example.productcatalogservice_dec2024.repos;

import org.example.productcatalogservice_dec2024.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Optional<Product> findProductById(Long id);

    Page<Product> findProductsByName(String query, Pageable pageable);

    Product save(Product p);

    List<Product> findAll();

    //List<Product> findProductOrderByPrice();

    List<Product> findProductByOrderByPrice();

    @Query("SELECT p.name from Product p where p.id=?1")
    String findProductTitleById(Long pid);

    @Query("SELECT c.name from Category c join Product p on p.category.id=c.id where p.id=:pid")
    String findCategoryNameFromProductId(Long pid);


}
