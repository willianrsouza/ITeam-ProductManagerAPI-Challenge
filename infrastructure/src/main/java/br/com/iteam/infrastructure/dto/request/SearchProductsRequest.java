package br.com.iteam.infrastructure.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public record SearchProductsRequest(UUID category, BigDecimal minPrice, BigDecimal maxPrice, String sortBy, String order, int page, int pageSize){}
