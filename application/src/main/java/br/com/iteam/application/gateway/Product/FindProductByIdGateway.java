package br.com.iteam.application.gateway.Product;

import br.com.iteam.core.domain.entity.Product;
import java.util.UUID;

public interface FindProductByIdGateway {
    Product findById(UUID id);
}
