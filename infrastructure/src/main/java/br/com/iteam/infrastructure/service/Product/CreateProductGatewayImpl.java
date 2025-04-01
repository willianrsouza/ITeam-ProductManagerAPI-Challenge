package br.com.iteam.infrastructure.service.Product;

import static br.com.iteam.infrastructure.utils.Utilities.serviceLog;
import br.com.fluentvalidator.context.ValidationResult;
import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.application.gateway.Product.CreateProductGateway;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.infrastructure.entity.ProductEntity;
import br.com.iteam.infrastructure.exception.ValidationException;
import br.com.iteam.infrastructure.mapper.ProductMapper;
import br.com.iteam.infrastructure.repository.ProductRepository;
import br.com.iteam.infrastructure.validators.ProductValidator;
import br.com.iteam.usecase.Category.FindCategoryById;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CreateProductGatewayImpl implements CreateProductGateway {

    private final ProductValidator productValidator;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final FindCategoryById findCategoryById;

    public CreateProductGatewayImpl(ProductValidator productValidator, ProductMapper productMapper, ProductRepository productRepository, FindCategoryById findCategoryById) {
        this.productValidator = productValidator;
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.findCategoryById = findCategoryById;
    }

    @Override
    public Product create(Product product) {
        serviceLog.info("Starting createProduct::CreateProductGatewayImpl");

        Category category = findCategoryById.findById(product.getCategory().getId());
        product.setCategory(category);

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
