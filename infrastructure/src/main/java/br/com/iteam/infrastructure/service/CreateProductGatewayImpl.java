package br.com.iteam.infrastructure.service;

import static br.com.iteam.infrastructure.utils.Utilities.serviceLog;
import br.com.fluentvalidator.context.ValidationResult;
import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.application.gateway.CreateProductGateway;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.infrastructure.entity.ProductEntity;
import br.com.iteam.infrastructure.exception.ValidationException;
import br.com.iteam.infrastructure.mapper.CategoryMapper;
import br.com.iteam.infrastructure.mapper.ProductMapper;
import br.com.iteam.infrastructure.repository.CategoryRepository;
import br.com.iteam.infrastructure.repository.ProductRepository;
import br.com.iteam.infrastructure.validators.ProductValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;


@Service
@Transactional
public class CreateProductGatewayImpl implements CreateProductGateway {

    private final ProductValidator productValidator;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public CreateProductGatewayImpl(ProductValidator productValidator, ProductMapper productMapper, CategoryMapper categoryMapper, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productValidator = productValidator;
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product create(Product product) {
        serviceLog.info("Starting createProduct::CreateProductGatewayImpl");

        Optional<Category> categoryOpt = categoryRepository.findById(product.getCategory().getId())
                .map(categoryMapper::toCategory);

        if (categoryOpt.isEmpty()) {
            throw new ValidationException("Category not found.");
        }

        product.setCategory(categoryOpt.get());
        ValidationResult productValidation = productValidator.validate(product);

        if (!productValidation.isValid()) {
            throw new ValidationException("Product Validation Failed", productValidation);
        }

        ProductEntity productToEntityMapped = productMapper.toProductEntity(product);
        ProductEntity productSaved = productRepository.save(productToEntityMapped);

        serviceLog.info("Product created successfully::CreateProductGatewayImpl");
        return productMapper.toProduct(productSaved);
    }
}
