package br.com.iteam.usecase.Category;

import br.com.iteam.core.domain.entity.Category;
import java.util.UUID;

public interface FindCategoryById {
    Category findById(UUID id);
}
