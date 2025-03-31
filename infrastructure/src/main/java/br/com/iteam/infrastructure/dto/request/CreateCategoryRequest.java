package br.com.iteam.infrastructure.dto.request;

import br.com.iteam.core.domain.enums.CategoryName;

public record CreateCategoryRequest(Long id, CategoryName name) {
}
