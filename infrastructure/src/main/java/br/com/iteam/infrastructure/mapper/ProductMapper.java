package br.com.iteam.infrastructure.mapper;

import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.infrastructure.dto.request.CreateProductRequest;
import br.com.iteam.infrastructure.dto.request.UpdateProductRequest;
import br.com.iteam.infrastructure.entity.CategoryEntity;
import br.com.iteam.infrastructure.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Optional;

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

    public ProductEntity toProductEntityPartial(Product product) {
        CategoryEntity categoryEntity = (product.getCategory() != null) ?
                categoryMapper.toCategoryEntity(product.getCategory()) : null;

        return new ProductEntity(
                null,
                categoryEntity,
                product.getName() != null ? product.getName() : null,
                product.getDescription() != null ? product.getDescription() : null,
                product.getPrice() != null ? product.getPrice() : null,
                product.getStock() != null ? product.getStock() : null,
                null,
                OffsetDateTime.now()
        );
    }

    public ProductEntity merge(ProductEntity product, ProductEntity productPartial) {
        if (product == null) return productPartial;
        if (productPartial == null) return product;

        Optional.ofNullable(productPartial.getName()).ifPresent(product::setName);
        Optional.ofNullable(productPartial.getPrice()).ifPresent(product::setPrice);
        Optional.ofNullable(productPartial.getDescription()).ifPresent(product::setDescription);
        Optional.ofNullable(productPartial.getStock()).ifPresent(product::setStock);
        Optional.ofNullable(productPartial.getCategory()).ifPresent(product::setCategory);
        Optional.ofNullable(productPartial.getUpdatedAt()).ifPresent(product::setUpdatedAt);

        return product;
    }

    public Product toProduct(ProductEntity productEntity) {
        Category category = categoryMapper.toCategory(productEntity.getCategory());

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

    public Product toProduct(CreateProductRequest productRequest) {
        return new Product(
                productRequest.name(),
                productRequest.description(),
                productRequest.price(),
                new Category(productRequest.categoryId()),
                productRequest.stock()
        );
    }

    public Product toProduct(UpdateProductRequest updateProductRequest) {
        return new Product(
                updateProductRequest.name(),
                updateProductRequest.description(),
                updateProductRequest.price(),
                new Category(updateProductRequest.categoryId()),
                updateProductRequest.stock(),
                null,
                null
        );
    }
}
