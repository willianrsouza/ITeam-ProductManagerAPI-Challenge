package br.com.iteam.infrastructure.validators;

import br.com.fluentvalidator.AbstractValidator;
import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.core.domain.enums.ProductCategories;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class CategoryValidator extends AbstractValidator<Category> {
    @Override
    public void rules() {
        setPropertyOnContext("category");

        ruleFor(Category::getName)
                .must(name -> name != null && name.length() >= 3 && name.length() <= 50)
                .withMessage("The name must be between 3 and 50 characters")
                .withFieldName("name")
                .withCode("name.invalid.length");

        ruleFor(Category::getName)
                .must(name -> name != null && isValidCategory(name))
                .withMessage("The product category must be one of the valid categories: " + getValidCategories())
                .withFieldName("category")
                .withCode("product.category.invalid");
    }

    private boolean isValidCategory(String categoryName) {
        return Arrays.stream(ProductCategories.values())
                .anyMatch(validCategory -> validCategory.name().equalsIgnoreCase(categoryName));
    }

    private String getValidCategories() {
        return Arrays.stream(ProductCategories.values())
                .map(Enum::name)
                .collect(Collectors.joining(", ")); //
    }
}
