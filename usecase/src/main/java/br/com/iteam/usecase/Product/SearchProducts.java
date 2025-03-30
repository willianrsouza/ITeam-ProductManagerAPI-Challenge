package br.com.iteam.usecase.Product;

import br.com.iteam.core.domain.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface SearchProducts {
    List<Product> search(String category,
                         BigDecimal minPrice,
                         BigDecimal maxPrice,
                         String sortBy,
                         String order,
                         int page,
                         int pageSize);
}
