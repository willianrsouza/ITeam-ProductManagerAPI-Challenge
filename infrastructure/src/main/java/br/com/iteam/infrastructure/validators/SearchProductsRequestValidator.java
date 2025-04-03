package br.com.iteam.infrastructure.validators;

import br.com.fluentvalidator.AbstractValidator;
import br.com.iteam.infrastructure.dto.request.SearchProductsRequest;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class SearchProductsRequestValidator extends AbstractValidator<SearchProductsRequest> {

    @Override
    public void rules() {
        setPropertyOnContext("searchProducts");

        ruleFor(SearchProductsRequest::minPrice)
                .must(minPrice -> minPrice == null || minPrice.compareTo(BigDecimal.ZERO) >= 0)
                .withMessage("The minimum price must be zero or positive.")
                .withFieldName("minPrice")
                .withCode("searchProducts.minPrice.invalid");

        ruleFor(SearchProductsRequest::maxPrice)
                .must(maxPrice -> maxPrice == null || maxPrice.compareTo(BigDecimal.ZERO) >= 0)
                .withMessage("The maximum price must be zero or positive.")
                .withFieldName("maxPrice")
                .withCode("searchProducts.maxPrice.invalid");

        ruleFor(SearchProductsRequest::sortBy)
                .must(sortBy -> sortBy == null || sortBy.matches("^(name|price|createdAt)$"))
                .withMessage("Invalid sorting field. Allowed values: name, price, createdAt.")
                .withFieldName("sortBy")
                .withCode("searchProducts.sortBy.invalid");

        ruleFor(SearchProductsRequest::order)
                .must(order -> order == null || order.matches("^(asc|desc)$"))
                .withMessage("Invalid order. Allowed values: asc, desc.")
                .withFieldName("order")
                .withCode("searchProducts.order.invalid");

        ruleFor(SearchProductsRequest::page)
                .must(page -> page >= 0)
                .withMessage("The page number must be greater than zero.")
                .withFieldName("page")
                .withCode("searchProducts.page.invalid");
    }
}