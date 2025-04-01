package br.com.iteam.application.gateway.Product;

import br.com.iteam.core.domain.entity.Product;

public interface CreateProductGateway {
    Product create(Product product);
}
