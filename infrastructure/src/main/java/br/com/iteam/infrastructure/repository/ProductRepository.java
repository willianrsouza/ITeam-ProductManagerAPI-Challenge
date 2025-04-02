package br.com.iteam.infrastructure.repository;

import br.com.iteam.infrastructure.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    @Query("SELECT p FROM ProductEntity p " +
            "WHERE (:categoryId IS NULL OR p.category.id = :categoryId) " +
            "AND p.price BETWEEN :minPrice AND :maxPrice")
    Page<ProductEntity> findByFilters(UUID categoryId, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

}
