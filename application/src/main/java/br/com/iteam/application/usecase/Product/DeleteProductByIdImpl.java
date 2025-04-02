package br.com.iteam.application.usecase.Product;

import br.com.iteam.application.gateway.Product.DeleteProductByIdGateway;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.usecase.Product.DeleteProductById;
import br.com.iteam.usecase.Product.FindProductById;

import java.util.UUID;

public class DeleteProductByIdImpl implements DeleteProductById {

    private final DeleteProductByIdGateway deleteProductByIdGateway;
    private final FindProductById findProductById;

    public DeleteProductByIdImpl(DeleteProductByIdGateway deleteProductByIdGateway, FindProductById findProductById) {
        this.deleteProductByIdGateway = deleteProductByIdGateway;
        this.findProductById = findProductById;
    }

    @Override
    public boolean deleteProductById(UUID id) {
        Product product = findProductById.findById(id);
        return deleteProductByIdGateway.deleteById(product.getId());
    }
}
