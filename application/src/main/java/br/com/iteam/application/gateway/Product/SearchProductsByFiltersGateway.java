package br.com.iteam.application.gateway.Product;

import br.com.iteam.core.domain.entity.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface SearchProductsByFiltersGateway {
    List<Product> search(UUID category, BigDecimal minPrice, BigDecimal maxPrice, String sortBy, String order, int page, int pageSize);
}
