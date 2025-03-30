package br.com.iteam.infrastructure.validators;

import br.com.fluentvalidator.AbstractValidator;
import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.core.domain.enums.CategoryName;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class CategoryValidator extends AbstractValidator<Category> {
    @Override
    public void rules() {
        setPropertyOnContext("category");

        ruleFor(Category::getName)
                .must(this::isValidCategory)
                .withMessage("The product category must be one of the valid categories: " + getValidCategories())
                .withFieldName("category")
                .withCode("product.category.invalid");
    }

    private boolean isValidCategory(CategoryName categoryName) {
        return categoryName != null && Arrays.asList(CategoryName.values()).contains(categoryName);
    }

    private String getValidCategories() {
        return Arrays.stream(CategoryName.values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }
}
