package br.com.iteam.application.gateway.Category;

import br.com.iteam.core.domain.entity.Category;
import java.util.List;

public interface FindAllCategoriesGateway {
    List<Category> findAll();
}
