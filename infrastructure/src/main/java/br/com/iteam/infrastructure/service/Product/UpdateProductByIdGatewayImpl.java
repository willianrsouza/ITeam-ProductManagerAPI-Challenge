package br.com.iteam.infrastructure.service.Product;

import static br.com.iteam.infrastructure.utils.Utilities.serviceLog;
import br.com.fluentvalidator.context.ValidationResult;
import br.com.iteam.application.gateway.Product.UpdateProductByIdGateway;
import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.infrastructure.entity.ProductEntity;
import br.com.iteam.infrastructure.exception.NotFoundException;
import br.com.iteam.infrastructure.exception.ValidationException;
import br.com.iteam.infrastructure.mapper.CategoryMapper;
import br.com.iteam.infrastructure.mapper.ProductMapper;
import br.com.iteam.infrastructure.repository.CategoryRepository;
import br.com.iteam.infrastructure.repository.ProductRepository;
import br.com.iteam.infrastructure.validators.PartialProductValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UpdateProductByIdGatewayImpl implements UpdateProductByIdGateway {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    private final PartialProductValidator partialProductValidator;

    public UpdateProductByIdGatewayImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper, CategoryMapper categoryMapper, PartialProductValidator partialProductValidator) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
        this.partialProductValidator = partialProductValidator;
    }

    @Override
    public Product updateById(UUID id, Product productUpdateData) {
        serviceLog.info("Starting updateById for Product ID: {}", id);

        Optional<ProductEntity> existingProduct = productRepository.findById(id);

        if (existingProduct.isEmpty()) {
            throw new NotFoundException("Product with ID: " + id + " not found.");
        }

        if(productUpdateData.getCategory().getId() != null){
            Optional<Category> category = categoryRepository.findById(productUpdateData.getCategory().getId())
                    .map(categoryMapper::toCategory);

            if (category.isEmpty()) {
                throw new NotFoundException("Category not found.");
            }

            productUpdateData.setCategory(category.get());
        }

        ValidationResult validationResult = partialProductValidator.validate(productUpdateData);

        if (!validationResult.isValid()) {
            throw new ValidationException("Product Validation Failed", validationResult);
        }

        ProductEntity updatedProductDataMapped = productMapper.toProductEntityPartial(productUpdateData);
        ProductEntity mergedProduct = productMapper.merge(existingProduct.get(), updatedProductDataMapped);

        productRepository.save(mergedProduct);

        serviceLog.info("Product with ID: {} successfully updated. ::UpdateProductByIdGatewayImpl", id);
        return productMapper.toProduct(mergedProduct);
    }
}