package br.com.iteam.infrastructure.service.Category;

import br.com.iteam.application.gateway.Category.FindCategoryByIdGateway;
import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.infrastructure.entity.CategoryEntity;
import br.com.iteam.infrastructure.exception.NotFoundException;
import br.com.iteam.infrastructure.mapper.CategoryMapper;
import br.com.iteam.infrastructure.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static br.com.iteam.infrastructure.utils.Utilities.serviceLog;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class FindCategoryByIdGatewayImpl implements FindCategoryByIdGateway {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public FindCategoryByIdGatewayImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findById(UUID id) {
        serviceLog.info("Starting findCategoryById::FindCategoryByIdGatewayImpl");

        Optional<CategoryEntity> result = categoryRepository.findById(id);

        if (result.isEmpty()) {
            throw new NotFoundException("Category not found.");
        }

        Category category = categoryMapper.toCategory(result);

        serviceLog.info("Category found successfully::FindCategoryByIdGatewayImpl");
        return category;
    }
}