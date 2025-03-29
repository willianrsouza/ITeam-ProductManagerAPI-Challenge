package br.com.iteam.core.validators;

import br.com.fluentvalidator.AbstractValidator;
import br.com.iteam.core.domain.Product;
import br.com.iteam.core.domain.enums.ProductCategories;

import java.math.BigDecimal;
import java.util.Arrays;

public class ValidatorProduct extends AbstractValidator<Product> {
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
                .must(category -> category != null && isValidCategory(category.getName()))
                .withMessage("The product category must be one of the valid categories: " + getValidCategories())
                .withFieldName("category")
                .withCode("product.category.invalid");


        ruleFor(Product::getStock)
                .must(stock -> stock != null && stock >= 0 && stock <= 10000)
                    .withMessage("The product stock must be between 0 and 10,000")
                    .withFieldName("stock")
                    .withCode("product.stock.invalid.range");
    }

    private boolean isValidCategory(String categoryName) {
        return Arrays.stream(ProductCategories.values())
                .anyMatch(validCategory -> validCategory.name().equalsIgnoreCase(categoryName));
    }

    private String getValidCategories() {
        return Arrays.stream(ProductCategories.values())
                .map(Enum::name)
                .toList()
                .toString();
    }
}