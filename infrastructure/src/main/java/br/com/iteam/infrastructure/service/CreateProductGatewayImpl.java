package br.com.iteam.infrastructure.service;

import br.com.fluentvalidator.context.ValidationResult;
import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.infrastructure.exception.ValidationException;
import br.com.iteam.application.gateway.CreateProductGateway;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.infrastructure.entity.ProductEntity;
import br.com.iteam.infrastructure.mapper.CategoryMapper;
import br.com.iteam.infrastructure.mapper.ProductMapper;
import br.com.iteam.infrastructure.repository.CategoryRepository;
import br.com.iteam.infrastructure.repository.ProductRepository;
import br.com.iteam.infrastructure.validators.CategoryValidator;
import br.com.iteam.infrastructure.validators.ProductValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static br.com.iteam.infrastructure.utils.Utilities.serviceLog;

@Service
@Transactional
public class CreateProductGatewayImpl implements CreateProductGateway {

    private final ProductValidator productValidator;
    private final CategoryValidator categoryValidator;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public CreateProductGatewayImpl(ProductValidator productValidator, CategoryValidator categoryValidator, ProductMapper productMapper, CategoryMapper categoryMapper, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productValidator = productValidator;
        this.categoryValidator = categoryValidator;
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product create(Product product) {
        serviceLog.info("Starting creating product::CreateProductGatewayImpl");
        serviceLog.info("PRODUTO  ======" + product);

        var categoryEntity =  categoryRepository.findById(product.getCategory().getId());

        serviceLog.info("CATEGORIA BUSCADA ======" + categoryEntity);

        Category category = categoryMapper.toCategory(categoryEntity);

        product.setCategory(category);
        ValidationResult productValidationResult = productValidator.validate(product);

        if (!productValidationResult.isValid()) {
            throw new ValidationException("Product", productValidationResult);
        }

        ProductEntity productToEntityMapped = productMapper.toProductEntity(product);
        ProductEntity productSaved = productRepository.save(productToEntityMapped);

        serviceLog.info("Product created successfully::CreateProductGatewayImpl");
        return productMapper.toProduct(productSaved);
    }
}
