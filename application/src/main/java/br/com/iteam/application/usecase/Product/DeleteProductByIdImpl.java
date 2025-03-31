package br.com.iteam.application.usecase.Product;

import br.com.iteam.application.gateway.DeleteProductByIdGateway;
import br.com.iteam.usecase.Product.DeleteProductById;
import java.util.UUID;

public class DeleteProductByIdImpl implements DeleteProductById {

    private DeleteProductByIdGateway deleteProductByIdGateway;

    public DeleteProductByIdImpl(DeleteProductByIdGateway deleteProductByIdGateway) {
        this.deleteProductByIdGateway = deleteProductByIdGateway;
    }

    @Override
    public boolean deleteProductById(UUID id) {
        return deleteProductByIdGateway.deleteById(id);
    }
}
