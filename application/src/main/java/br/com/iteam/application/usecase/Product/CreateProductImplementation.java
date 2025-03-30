package br.com.iteam.application.usecase.Product;

import br.com.fluentvalidator.context.ValidationResult;
import br.com.iteam.application.exception.ValidationException;
import br.com.iteam.application.gateway.CreateProductGateway;
import br.com.iteam.application.validators.CategoryValidator;
import br.com.iteam.application.validators.ProductValidator;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.usecase.Product.CreateProduct;

public class CreateProductImplementation implements CreateProduct {

    private final ProductValidator productValidator = new ProductValidator();
    private final CategoryValidator categoryValidator = new CategoryValidator();
    private final CreateProductGateway createProductGateway;

    public CreateProductImplementation(CreateProductGateway createProductGateway) {
        this.createProductGateway = createProductGateway;
    }

    @Override
    public Product create(Product product) {
        ValidationResult categoryValidationResult = categoryValidator.validate(product.getCategory());

        if (!categoryValidationResult.isValid()) {
            throw new ValidationException("Category", categoryValidationResult);
        }

        ValidationResult productValidationResult = productValidator.validate(product);

        if (!productValidationResult.isValid()) {
            throw new ValidationException("Product", productValidationResult);
        }

        return createProductGateway.create(product);
    }
}
