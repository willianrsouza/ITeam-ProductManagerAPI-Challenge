package br.com.iteam.application.usecase.Product;

import br.com.iteam.application.gateway.Product.UpdateProductByIdGateway;
import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.usecase.Category.FindCategoryById;
import br.com.iteam.usecase.Product.FindProductById;
import br.com.iteam.usecase.Product.UpdateProductById;
import java.util.UUID;

public class UpdateProductByIdImpl implements UpdateProductById {

    private final UpdateProductByIdGateway updateProductByIdGateway;
    private final FindProductById findProductByIdUseCase;
    private final FindCategoryById findCategoryByIdUseCase;

    public UpdateProductByIdImpl(UpdateProductByIdGateway updateProductByIdGateway, FindProductById findProductByIdUseCase, FindCategoryById findCategoryByIdUseCase) {
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
