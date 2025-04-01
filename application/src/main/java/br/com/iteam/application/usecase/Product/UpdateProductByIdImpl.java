package br.com.iteam.application.usecase.Product;

import br.com.iteam.application.gateway.Product.UpdateProductByIdGateway;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.usecase.Product.UpdateProductById;
import java.util.UUID;

public class UpdateProductByIdImpl implements UpdateProductById {

    private final UpdateProductByIdGateway updateProductByIdGateway;

    public UpdateProductByIdImpl(UpdateProductByIdGateway updateProductByIdGateway) {
        this.updateProductByIdGateway = updateProductByIdGateway;
    }

    @Override
    public Product updateById(UUID id, Product product) {
       return updateProductByIdGateway.updateById(id, product);
    }
}
