package br.com.iteam.application.usecase.Product;

import br.com.iteam.application.gateway.Product.SearchProductsByFiltersGateway;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.usecase.Category.FindCategoryByIdUseCase;
import br.com.iteam.usecase.Product.SearchProductsByFiltersUseCase;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class SearchProductsByFiltersUseCaseImpl implements SearchProductsByFiltersUseCase {

    private final SearchProductsByFiltersGateway searchProductsByFiltersGateway;
    private final FindCategoryByIdUseCase findCategoryByIdUseCase;

    public SearchProductsByFiltersUseCaseImpl(SearchProductsByFiltersGateway searchProductsByFiltersGateway, FindCategoryByIdUseCase findCategoryByIdUseCase) {
        this.searchProductsByFiltersGateway = searchProductsByFiltersGateway;
        this.findCategoryByIdUseCase = findCategoryByIdUseCase;
    }

    @Override
    public List<Product> search(UUID categoryId, BigDecimal minPrice, BigDecimal maxPrice, String sortBy, String order, int page, int pageSize) {
        UUID validCategory =  categoryId != null ? findCategoryByIdUseCase.findById(categoryId).getId() : null;
        return searchProductsByFiltersGateway.search(validCategory, minPrice, maxPrice, sortBy, order, page, pageSize);
    }
}
