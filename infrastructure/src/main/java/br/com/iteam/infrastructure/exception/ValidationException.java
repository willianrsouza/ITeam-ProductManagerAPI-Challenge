package br.com.iteam.infrastructure.exception;

import br.com.fluentvalidator.context.ValidationResult;
import java.util.List;
import java.util.stream.Collectors;

public class ValidationException extends RuntimeException {
    private static final String TITLE = "Validation failures";
    private static final int STATUS = 400;
    private final String entityName;
    private final String detail;
    private final List<ValidationError> errors;

    public ValidationException(String entityName, ValidationResult validationResult) {
        super(TITLE);
        this.entityName = entityName;
        this.detail = "Entity: " + entityName + " - Error: One or more fields are invalid.";
        this.errors = validationResult.getErrors().stream()
                .map(error -> new ValidationError(error.getField(), error.getMessage()))
                .collect(Collectors.toList());
    }

    public String getEntityName() {
        return entityName;
    }

    public String getDetail() {
        return detail;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public record ValidationError(String field, String message) {
    }
}
