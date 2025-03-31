package br.com.iteam.infrastructure.exception;

import br.com.fluentvalidator.context.ValidationResult;
import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {
    private final ValidationResult validationResult;

    public ValidationException(String message, ValidationResult validationResult) {
        super(message);
        this.validationResult = validationResult;
    }

    public ValidationException(String message) {
        super(message);
        this.validationResult = null;
    }
}
