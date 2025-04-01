package br.com.iteam.infrastructure.service.Category;

import static br.com.iteam.infrastructure.utils.Utilities.serviceLog;
import br.com.iteam.application.gateway.Category.FindAllCategoriesGateway;
import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.infrastructure.exception.NotFoundException;
import br.com.iteam.infrastructure.mapper.CategoryMapper;
import br.com.iteam.infrastructure.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class FindAllCategoriesGatewayImpl implements FindAllCategoriesGateway {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public FindAllCategoriesGatewayImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        serviceLog.info("Starting findAll::FindAllCategoriesGatewayImpl");

        List<Category> results = categoryRepository.findAll().stream()
                .map(categoryMapper::toCategory)
                .toList();

        if(results.isEmpty()) {
            throw new NotFoundException("Not found categories.");
        }

        serviceLog.info("Categories found successfully::FindAllCategoriesGatewayImpl");
        return results;
    }
}
