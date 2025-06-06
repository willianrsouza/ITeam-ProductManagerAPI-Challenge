package br.com.iteam.infrastructure.mapper;

import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.core.domain.enums.CategoryName;
import br.com.iteam.infrastructure.dto.request.CreateCategoryRequest;
import br.com.iteam.infrastructure.entity.CategoryEntity;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class CategoryMapper {

    public CategoryEntity toCategoryEntity(Category category) {
        if (category.getId() == null) {
            return null;
        }

        return new CategoryEntity(
                category.getId(),
                category.getName().name(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }

    public Category toCategory(CategoryEntity categoryEntity) {
        return new Category(
                categoryEntity.getId(),
                CategoryName.valueOf(categoryEntity.getName()),
                categoryEntity.getCreatedAt(),
                categoryEntity.getUpdatedAt()
        );
    }

    public Category toCategory(Optional<CategoryEntity> categoryEntity) {
        return new Category(
                categoryEntity.get().getId(),
                CategoryName.valueOf(categoryEntity.get().getName()),
                categoryEntity.get().getCreatedAt(),
                categoryEntity.get().getUpdatedAt()
        );
    }

    public Category toCategory(CreateCategoryRequest createCategoryRequest) {
        return new Category(
                createCategoryRequest.categoryId()
        );
    }
}
