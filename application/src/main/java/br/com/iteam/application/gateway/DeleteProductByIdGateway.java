package br.com.iteam.application.gateway;

import java.util.UUID;

public interface DeleteProductByIdGateway {
    boolean deleteById(UUID id);
}
