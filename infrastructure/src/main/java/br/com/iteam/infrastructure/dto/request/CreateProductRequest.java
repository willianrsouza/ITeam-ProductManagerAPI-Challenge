package br.com.iteam.infrastructure.dto.request;

import java.math.BigDecimal;

public record CreateProductRequest(String name, String description, BigDecimal price, CreateCategoryRequest  category, Integer stock) {
}
