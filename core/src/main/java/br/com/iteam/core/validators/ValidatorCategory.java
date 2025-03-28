package br.com.iteam.core.validators;

import br.com.fluentvalidator.AbstractValidator;
import br.com.iteam.core.domain.Category;

public class ValidatorCategory extends AbstractValidator<Category> {
    @Override
    public void rules() {
        setPropertyOnContext("category");

        ruleFor(Category::getName)
                .must(name -> name != null && name.length() >= 3 && name.length() <= 50)
                    .withMessage("The name must be between 3 and 50 characters")
                    .withFieldName("name")
                    .withCode("name.invalid.length");
    }
}
