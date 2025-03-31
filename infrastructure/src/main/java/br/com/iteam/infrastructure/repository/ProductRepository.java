package br.com.iteam.infrastructure.repository;

import br.com.iteam.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
}
