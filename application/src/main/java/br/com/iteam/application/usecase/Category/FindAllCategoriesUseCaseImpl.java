package br.com.iteam.application.usecase.Category;

import br.com.iteam.application.gateway.Category.FindAllCategoriesGateway;
import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.usecase.Category.FindAllCategoriesUseCase;
import java.util.List;

public class FindAllCategoriesUseCaseImpl implements FindAllCategoriesUseCase {
    private final FindAllCategoriesGateway findAllCategoriesGateway;

    public FindAllCategoriesUseCaseImpl(FindAllCategoriesGateway findAllCategoriesGateway) {
        this.findAllCategoriesGateway = findAllCategoriesGateway;
    }

    @Override
    public List<Category> findAll() {
        return findAllCategoriesGateway.findAll();
    }
}
