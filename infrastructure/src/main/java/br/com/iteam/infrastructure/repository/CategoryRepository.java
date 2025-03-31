package br.com.iteam.infrastructure.repository;

import br.com.iteam.infrastructure.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
}
