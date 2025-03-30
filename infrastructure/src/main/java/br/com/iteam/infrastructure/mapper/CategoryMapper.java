package br.com.iteam.infrastructure.mapper;

import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.infrastructure.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryEntity toCategoryEntity(Category category) {
        return new CategoryEntity(
                category.getName(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }

    public Category toCategory(CategoryEntity categoryEntity) {
        return new Category(
                categoryEntity.getId(),
                categoryEntity.getName(),
                categoryEntity.getCreatedAt(),
                categoryEntity.getUpdatedAt()
        );
    }
}
