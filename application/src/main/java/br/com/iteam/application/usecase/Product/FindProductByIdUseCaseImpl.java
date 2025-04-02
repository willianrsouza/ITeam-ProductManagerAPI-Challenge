package br.com.iteam.application.usecase.Product;

import br.com.iteam.application.gateway.Product.FindProductByIdGateway;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.usecase.Product.FindProductByIdUseCase;
import java.util.UUID;

public class FindProductByIdUseCaseImpl implements FindProductByIdUseCase {

    private final FindProductByIdGateway findProductByIdGateway;

    public FindProductByIdUseCaseImpl(FindProductByIdGateway findProductByIdGateway) {
        this.findProductByIdGateway = findProductByIdGateway;
    }

    @Override
    public Product findById(UUID id) {
        return findProductByIdGateway.findById(id);
    }
}
