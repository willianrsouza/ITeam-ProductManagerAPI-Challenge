package br.com.iteam.infrastructure.mapper;

import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.infrastructure.entity.CategoryEntity;
import br.com.iteam.infrastructure.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final CategoryMapper categoryMapper;

    public ProductMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public ProductEntity toProductEntity(Product product) {
        CategoryEntity categoryEntity = categoryMapper.toCategoryEntity(product.getCategory());

        return new ProductEntity(
                product.getId(),
                categoryEntity,
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    public Product toProduct(ProductEntity productEntity) {
        Category category = categoryMapper.toCategory(productEntity.getCategoryEntity());

        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getPrice(),
                category,
                productEntity.getStock(),
                productEntity.getCreatedAt(),
                productEntity.getUpdatedAt()
        );
    }
}
