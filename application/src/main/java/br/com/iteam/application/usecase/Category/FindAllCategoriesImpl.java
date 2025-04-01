package br.com.iteam.application.usecase.Category;

import br.com.iteam.application.gateway.Category.FindAllCategoriesGateway;
import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.usecase.Category.FindAllCategories;
import java.util.List;

public class FindAllCategoriesImpl implements FindAllCategories {
    private final FindAllCategoriesGateway findAllCategoriesGateway;

    public FindAllCategoriesImpl(FindAllCategoriesGateway findAllCategoriesGateway) {
        this.findAllCategoriesGateway = findAllCategoriesGateway;
    }

    @Override
    public List<Category> findAll() {
        return findAllCategoriesGateway.findAll();
    }
}
