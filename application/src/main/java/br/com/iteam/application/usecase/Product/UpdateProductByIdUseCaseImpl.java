package br.com.iteam.application.usecase.Product;

import br.com.iteam.application.gateway.Product.UpdateProductByIdGateway;
import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.usecase.Category.FindCategoryByIdUseCase;
import br.com.iteam.usecase.Product.FindProductByIdUseCase;
import br.com.iteam.usecase.Product.UpdateProductByIdUseCase;
import java.util.UUID;

public class UpdateProductByIdUseCaseImpl implements UpdateProductByIdUseCase {

    private final UpdateProductByIdGateway updateProductByIdGateway;
    private final FindProductByIdUseCase findProductByIdUseCase;
    private final FindCategoryByIdUseCase findCategoryByIdUseCase;

    public UpdateProductByIdUseCaseImpl(UpdateProductByIdGateway updateProductByIdGateway, FindProductByIdUseCase findProductByIdUseCase, FindCategoryByIdUseCase findCategoryByIdUseCase) {
        this.updateProductByIdGateway = updateProductByIdGateway;
        this.findProductByIdUseCase = findProductByIdUseCase;
        this.findCategoryByIdUseCase = findCategoryByIdUseCase;
    }

    @Override
    public Product updateById(UUID id, Product product) {
        Product existingProduct = findProductByIdUseCase.findById(id);

        if(product.getCategory().getId() != null){
            Category category = findCategoryByIdUseCase.findById(product.getCategory().getId());
            product.setCategory(category);
        }

       return updateProductByIdGateway.updateById(id, existingProduct, product);
    }
}
