package br.com.iteam.application.gateway;

import br.com.iteam.core.domain.entity.Product;
import java.util.UUID;

public interface FindProductByIdGateway {
    Product findById(UUID id);
}
