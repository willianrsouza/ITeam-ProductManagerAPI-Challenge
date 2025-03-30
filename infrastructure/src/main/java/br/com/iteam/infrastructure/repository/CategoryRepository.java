package br.com.iteam.infrastructure.repository;

import br.com.iteam.infrastructure.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    
}
