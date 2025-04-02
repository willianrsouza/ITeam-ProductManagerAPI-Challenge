package br.com.iteam.application.usecase.Product;

import br.com.iteam.application.gateway.Product.CreateProductGateway;
import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.usecase.Category.FindCategoryByIdUseCase;
import br.com.iteam.usecase.Product.CreateProductUseCase;

public class CreateProductUseCaseImpl implements CreateProductUseCase {

    private final CreateProductGateway createProductGateway;
    private final FindCategoryByIdUseCase findCategoryByIdUseCase;

    public CreateProductUseCaseImpl(CreateProductGateway createProductGateway, FindCategoryByIdUseCase findCategoryByIdUseCase) {
        this.createProductGateway = createProductGateway;
        this.findCategoryByIdUseCase = findCategoryByIdUseCase;
    }

    @Override
    public Product create(Product product) {
        Category category = findCategoryByIdUseCase.findById(product.getCategory().getId());
        product.setCategory(category);

        return createProductGateway.create(product);
    }
}
