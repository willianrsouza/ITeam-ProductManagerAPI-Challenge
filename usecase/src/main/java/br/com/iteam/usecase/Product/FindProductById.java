package br.com.iteam.usecase.Product;

import br.com.iteam.core.domain.entity.Product;
import java.util.UUID;

public interface FindProductById {
    Product findById(UUID id);
}
