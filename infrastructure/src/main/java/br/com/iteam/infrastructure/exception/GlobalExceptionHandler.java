package br.com.iteam.infrastructure.exception;

import br.com.fluentvalidator.context.ValidationResult;
import br.com.iteam.infrastructure.dto.response.ErrorResponse;
import br.com.iteam.infrastructure.dto.response.ErrorDetail;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleFluentValidationException(ValidationException ex) {
        ValidationResult validationResult = ex.getValidationResult();

        List<ErrorDetail> errors = validationResult.getErrors()
                .stream()
                .map(error -> new ErrorDetail(error.getField(), error.getMessage()))
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.of(
                "Validation Failures.",
                HttpStatus.BAD_REQUEST.value(),
                "One or more fields are invalid",
                errors
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        List<ErrorDetail> errors = List.of(new ErrorDetail("generic", ex.getMessage()));

        ErrorResponse errorResponse = ErrorResponse.of(
                "Internal Server Error.",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred",
                errors
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler({InvalidFormatException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponse> handleJsonParseException(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.of(
                "Invalid UUID.",
                HttpStatus.BAD_REQUEST.value(),
                "The provided ID is not a valid UUID format.",
                null
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(
            NotFoundException ex) {

        ErrorResponse errorResponse = ErrorResponse.of(
                "Resource Not Found.",
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
