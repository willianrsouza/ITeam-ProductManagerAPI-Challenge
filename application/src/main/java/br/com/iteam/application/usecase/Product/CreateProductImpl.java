package br.com.iteam.application.usecase.Product;

import br.com.iteam.application.gateway.Product.CreateProductGateway;
import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.usecase.Category.FindCategoryById;
import br.com.iteam.usecase.Product.CreateProduct;

public class CreateProductImpl implements CreateProduct {

    private final CreateProductGateway createProductGateway;
    private final FindCategoryById findCategoryByIdUseCase;

    public CreateProductImpl(CreateProductGateway createProductGateway, FindCategoryById findCategoryByIdUseCase) {
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
