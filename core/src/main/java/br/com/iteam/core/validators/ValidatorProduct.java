package br.com.iteam.core.validators;

import br.com.fluentvalidator.AbstractValidator;
import br.com.iteam.core.domain.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ValidatorProduct extends AbstractValidator<Product> {
    private static final List<String> VALID_CATEGORIES = Arrays.asList("Eletrônicos", "Roupas", "Alimentos");

    @Override
    public void rules() {
        setPropertyOnContext("product");

        ruleFor(Product::getName)
                .must(name -> name != null && name.length() >= 3 && name.length() <= 100)
                    .withMessage("The product name must be between 3 and 100 characters")
                    .withFieldName("name")
                    .withCode("product.name.invalid.length");

        ruleFor(Product::getDescription)
                .must(description -> description != null && description.length() >= 10 && description.length() <= 500)
                    .withMessage("The product description must be between 10 and 500 characters")
                    .withFieldName("description")
                    .withCode("product.description.invalid.length");

        ruleFor(Product::getPrice)
                .must(price -> price != null && price.compareTo(BigDecimal.valueOf(0.01)) >= 0 && price.compareTo(BigDecimal.valueOf(999999.99)) <= 0)
                    .withMessage("The product price must be between 0.01 and 999999.99")
                    .withFieldName("price")
                    .withCode("product.price.invalid.range");


        ruleFor(Product::getCategory)
                .must(category -> category != null && VALID_CATEGORIES.contains(category.getName()))
                    .withMessage("The product category must be one of the valid categories (e.g., 'Eletrônicos', 'Roupas', 'Alimentos')")
                    .withFieldName("category")
                    .withCode("product.category.invalid");

        ruleFor(Product::getStock)
                .must(stock -> stock != null && stock >= 0 && stock <= 10000)
                    .withMessage("The product stock must be between 0 and 10,000")
                    .withFieldName("stock")
                    .withCode("product.stock.invalid.range");
    }
}