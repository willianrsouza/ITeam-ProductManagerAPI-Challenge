package br.com.iteam.application.usecase.Product;

import br.com.iteam.application.gateway.Product.CreateProductGateway;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.usecase.Product.CreateProduct;

public class CreateProductImpl implements CreateProduct {

    private final CreateProductGateway createProductGateway;

    public CreateProductImpl(CreateProductGateway createProductGateway) {
        this.createProductGateway = createProductGateway;
    }

    @Override
    public Product create(Product product) {
        return createProductGateway.create(product);
    }
}
