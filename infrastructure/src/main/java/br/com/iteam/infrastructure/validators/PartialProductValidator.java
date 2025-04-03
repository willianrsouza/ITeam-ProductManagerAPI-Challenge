package br.com.iteam.infrastructure.validators;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class PartialProductValidator extends ProductValidatorBase {

    @Override
    protected boolean validateName(String name) {
        return name == null || super.validateName(name);
    }

    @Override
    protected boolean validateDescription(String description) {
        return description == null || super.validateDescription(description);
    }

    @Override
    protected boolean validatePrice(BigDecimal price) {
        return price == null || super.validatePrice(price);
    }

    @Override
    protected boolean validateStock(Integer stock) {
        return stock == null || super.validateStock(stock);
    }
}
