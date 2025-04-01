package br.com.iteam.application.gateway.Product;

import br.com.iteam.core.domain.entity.Product;
import java.util.UUID;

public interface UpdateProductByIdGateway {
    Product updateById(UUID id, Product productUpdateData);
}
