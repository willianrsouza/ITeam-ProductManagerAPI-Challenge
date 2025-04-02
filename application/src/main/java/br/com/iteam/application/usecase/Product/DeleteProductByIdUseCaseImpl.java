package br.com.iteam.application.usecase.Product;

import br.com.iteam.application.gateway.Product.DeleteProductByIdGateway;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.usecase.Product.DeleteProductByIdUseCase;
import br.com.iteam.usecase.Product.FindProductByIdUseCase;

import java.util.UUID;

public class DeleteProductByIdUseCaseImpl implements DeleteProductByIdUseCase {

    private final DeleteProductByIdGateway deleteProductByIdGateway;
    private final FindProductByIdUseCase findProductByIdUseCase;

    public DeleteProductByIdUseCaseImpl(DeleteProductByIdGateway deleteProductByIdGateway, FindProductByIdUseCase findProductByIdUseCase) {
        this.deleteProductByIdGateway = deleteProductByIdGateway;
        this.findProductByIdUseCase = findProductByIdUseCase;
    }

    @Override
    public boolean deleteProductById(UUID id) {
        Product product = findProductByIdUseCase.findById(id);
        return deleteProductByIdGateway.deleteById(product.getId());
    }
}
