package org.example.springbootdemo.repository;

import org.example.springbootdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameAndPrice(String name, Long price);

    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);

    @Query("select p from Product p where "+
            "(:name is null or p.name like %:name% )"+
            "and (:minPrice is null or p.price >= :minPrice )"+
            "and (:maxPrice is null or p.price <= :maxPrice) "
    )
    List<Product> filterProduct(@Param("name") String name,
                                @Param("minPrice") Long minPrice,
                                @Param("maxPrice") Long maxPrice);
}
