package br.com.iteam.application.gateway;

import br.com.iteam.core.domain.entity.Product;

public interface CreateProductGateway {
    Product create(Product product);
}
