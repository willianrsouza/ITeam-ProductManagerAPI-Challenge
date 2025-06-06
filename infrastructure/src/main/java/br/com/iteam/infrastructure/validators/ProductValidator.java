package br.com.iteam.infrastructure.validators;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class ProductValidator extends ProductValidatorBase {

    @Override
    protected boolean validateName(String name) {
        return super.validateName(name);
    }

    @Override
    protected boolean validateDescription(String description) {
        return super.validateDescription(description);
    }

    @Override
    protected boolean validatePrice(BigDecimal price) {
        return super.validatePrice(price);
    }

    @Override
    protected boolean validateStock(Integer stock) {
        return super.validateStock(stock);
    }
}