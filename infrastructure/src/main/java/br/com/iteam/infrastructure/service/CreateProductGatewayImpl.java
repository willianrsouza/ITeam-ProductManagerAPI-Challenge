package br.com.iteam.infrastructure.service;

import br.com.fluentvalidator.context.ValidationResult;
import br.com.iteam.infrastructure.exception.ValidationException;
import br.com.iteam.application.gateway.CreateProductGateway;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.infrastructure.entity.ProductEntity;
import br.com.iteam.infrastructure.mapper.ProductMapper;
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
    private final ProductRepository productRepository;

    public CreateProductGatewayImpl(ProductValidator productValidator, CategoryValidator categoryValidator, ProductMapper productMapper, ProductRepository productRepository) {
        this.productValidator = productValidator;
        this.categoryValidator = categoryValidator;
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        serviceLog.info("Starting creating product::CreateProductGatewayImpl");

        ValidationResult categoryValidationResult = categoryValidator.validate(product.getCategory());

        if (!categoryValidationResult.isValid()) {
            throw new ValidationException("Category", categoryValidationResult);
        }

        ValidationResult productValidationResult = productValidator.validate(product);

        if (!productValidationResult.isValid()) {
            throw new ValidationException("Product", productValidationResult);
        }

        ProductEntity productToEntityMapped = productMapper.toProductEntity(product);

        serviceLog.info("productToEntityMapped ====== " + productToEntityMapped);
        ProductEntity productSaved = productRepository.save(productToEntityMapped);

        serviceLog.info("Product created successfully::CreateProductGatewayImpl");
        return productMapper.toProduct(productSaved);
    }
}
