package br.com.iteam.application.usecase.Category;

import br.com.iteam.application.gateway.Category.FindCategoryByIdGateway;
import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.usecase.Category.FindCategoryById;
import java.util.UUID;

public class FindCategoryByIdImpl implements FindCategoryById {
    private final FindCategoryByIdGateway findCategoryByIdGateway;

    public FindCategoryByIdImpl(FindCategoryByIdGateway findCategoryByIdGateway) {
        this.findCategoryByIdGateway = findCategoryByIdGateway;
    }

    @Override
    public Category findById(UUID id) {
        return findCategoryByIdGateway.findById(id);
    }
}
