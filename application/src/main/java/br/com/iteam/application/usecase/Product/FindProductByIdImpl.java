package br.com.iteam.application.usecase.Product;

import br.com.iteam.application.gateway.FindProductByIdGateway;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.usecase.Product.FindProductById;
import java.util.UUID;

public class FindProductByIdImpl implements FindProductById {

    private final FindProductByIdGateway findProductByIdGateway;

    public FindProductByIdImpl(FindProductByIdGateway findProductByIdGateway) {
        this.findProductByIdGateway = findProductByIdGateway;
    }

    @Override
    public Product findById(UUID id) {
        return findProductByIdGateway.findById(id);
    }
}
