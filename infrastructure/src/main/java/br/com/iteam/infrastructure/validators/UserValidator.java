package br.com.iteam.infrastructure.validators;

import br.com.fluentvalidator.AbstractValidator;
import br.com.iteam.core.domain.entity.User;

public class UserValidator extends AbstractValidator<User> {
    @Override
    public void rules() {
        setPropertyOnContext("user");

        ruleFor(User::getName)
                .must(name -> name != null && name.length() >= 3 && name.length() <= 100)
                    .withMessage("The name must be between 3 and 100 characters")
                    .withFieldName("name")
                    .withCode("name.invalid.length")
                .must(name -> name.matches("^[a-zA-Z0-9 ]+$"))
                    .withMessage("The name must not contain invalid special characters (e.g., @, #, $)")
                    .withFieldName("name")
                    .withCode("name.invalid.characters");

        ruleFor(User::getEmail)
                .must(email -> email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
                    .withMessage("The email must be a valid format (e.g., user@domain.com)")
                    .withFieldName("email")
                    .withCode("email.invalid.format");

        ruleFor(User::getPassword)
                .must(password -> password != null && password.length() >= 8)
                    .withMessage("The password must have at least 8 characters")
                    .withFieldName("password")
                    .withCode("password.invalid.length")
                .must(password -> password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$"))
                    .withMessage("The password must contain uppercase, lowercase letters, numbers, and special characters")
                    .withFieldName("password")
                    .withCode("password.invalid.format");

        ruleFor(User::getRole)
                .must(role -> role != null && (role.equals("admin") || role.equals("user")))
                    .withMessage("The role must be either 'admin' or 'user'")
                    .withFieldName("role")
                    .withCode("role.invalid.value");
    }
}
