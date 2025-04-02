package br.com.iteam.usecase.Category;

import br.com.iteam.core.domain.entity.Category;
import java.util.List;

public interface FindAllCategoriesUseCase {
    List<Category> findAll();
}
