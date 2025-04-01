package br.com.iteam.application.gateway.Category;

import br.com.iteam.core.domain.entity.Category;
import java.util.UUID;

public interface FindCategoryByIdGateway {
    Category findById(UUID id);
}
