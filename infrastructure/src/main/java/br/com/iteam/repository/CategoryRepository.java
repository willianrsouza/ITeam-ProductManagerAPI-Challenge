package br.com.iteam.repository;

import br.com.iteam.infrastructure.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
