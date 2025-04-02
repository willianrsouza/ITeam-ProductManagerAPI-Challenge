package br.com.iteam.infrastructure.service.Product;

import br.com.fluentvalidator.context.ValidationResult;
import br.com.iteam.application.gateway.Product.SearchProductsByFiltersGateway;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.infrastructure.dto.request.SearchProductsRequest;
import br.com.iteam.infrastructure.entity.ProductEntity;
import br.com.iteam.infrastructure.exception.ValidationException;
import br.com.iteam.infrastructure.mapper.ProductMapper;
import br.com.iteam.infrastructure.repository.ProductRepository;
import br.com.iteam.infrastructure.validators.SearchProductsRequestValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SearchProductsByFiltersGatewayImpl implements SearchProductsByFiltersGateway {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SearchProductsRequestValidator searchProductsRequestValidator;

    public SearchProductsByFiltersGatewayImpl(ProductRepository productRepository, ProductMapper productMapper, SearchProductsRequestValidator searchProductsRequestValidator) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.searchProductsRequestValidator = searchProductsRequestValidator;
    }

    @Override
    public List<Product> search(UUID category, BigDecimal minPrice, BigDecimal maxPrice, String sortBy, String order, int page, int pageSize) {
        SearchProductsRequest request = new SearchProductsRequest(category, minPrice, maxPrice, sortBy, order, page, pageSize);
        ValidationResult requestValidation = searchProductsRequestValidator.validate(request);

        if (!requestValidation.isValid()) {
            throw new ValidationException("Request Validation Failed", requestValidation);
        }

        minPrice = (minPrice != null) ? minPrice : BigDecimal.ZERO;
        maxPrice = (maxPrice != null) ? maxPrice : BigDecimal.valueOf(Double.MAX_VALUE);

        Pageable pageable = PageRequest.of(page, pageSize, getSort(sortBy, order));
        Page<ProductEntity> productPage = productRepository.findByFilters(category, minPrice, maxPrice, pageable);

        return productMapper.toProductList(productPage.getContent());
    }

    private Sort getSort(String sortBy, String order) {
        Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
        return Sort.by(direction, sortBy);
    }
}
