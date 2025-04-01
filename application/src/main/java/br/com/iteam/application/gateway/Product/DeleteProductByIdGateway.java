package br.com.iteam.application.gateway.Product;

import java.util.UUID;

public interface DeleteProductByIdGateway {
    boolean deleteById(UUID id);
}
