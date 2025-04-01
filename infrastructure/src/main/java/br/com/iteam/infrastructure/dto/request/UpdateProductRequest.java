package br.com.iteam.infrastructure.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateProductRequest(String name, String description, BigDecimal price, UUID categoryId, Integer stock) {
}
