package br.com.iteam.infrastructure.validators;

import br.com.fluentvalidator.AbstractValidator;
import br.com.iteam.core.domain.entity.Product;
import java.math.BigDecimal;

public abstract class ProductValidatorBase extends AbstractValidator<Product> {

    @Override
    public void rules() {
        setPropertyOnContext("product");

        ruleFor(Product::getName)
                .must(this::validateName)
                .withMessage("The product name must be between 3 and 100 characters")
                .withFieldName("name")
                .withCode("product.name.invalid.length");

        ruleFor(Product::getDescription)
                .must(this::validateDescription)
                .withMessage("The product description must be between 10 and 500 characters")
                .withFieldName("description")
                .withCode("product.description.invalid.length");

        ruleFor(Product::getPrice)
                .must(this::validatePrice)
                .withMessage("The product price must be between 0.01 and 999999.99")
                .withFieldName("price")
                .withCode("product.price.invalid.range");

        ruleFor(Product::getStock)
                .must(this::validateStock)
                .withMessage("The product stock must be between 0 and 10,000")
                .withFieldName("stock")
                .withCode("product.stock.invalid.range");
    }

    protected boolean validateName(String name) {
        return name != null && name.length() >= 3 && name.length() <= 100;
    }

    protected boolean validateDescription(String description) {
        return description != null && description.length() >= 10 && description.length() <= 500;
    }

    protected boolean validatePrice(BigDecimal price) {
        return price != null && price.compareTo(BigDecimal.valueOf(0.01)) >= 0 && price.compareTo(BigDecimal.valueOf(999999.99)) <= 0;
    }

    protected boolean validateStock(Integer stock) {
        return stock != null && stock >= 0 && stock <= 10000;
    }
}
