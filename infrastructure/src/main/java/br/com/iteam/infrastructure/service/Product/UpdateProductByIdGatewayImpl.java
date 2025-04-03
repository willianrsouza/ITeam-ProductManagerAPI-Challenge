package br.com.iteam.infrastructure.service.Product;

import static br.com.iteam.infrastructure.utils.Utilities.serviceLog;
import br.com.fluentvalidator.context.ValidationResult;
import br.com.iteam.application.gateway.Product.UpdateProductByIdGateway;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.infrastructure.entity.ProductEntity;
import br.com.iteam.infrastructure.exception.ValidationException;
import br.com.iteam.infrastructure.mapper.ProductMapper;
import br.com.iteam.infrastructure.repository.ProductRepository;
import br.com.iteam.infrastructure.validators.PartialProductValidator;
import br.com.iteam.usecase.Category.FindCategoryByIdUseCase;
import br.com.iteam.usecase.Product.FindProductByIdUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@Transactional
public class UpdateProductByIdGatewayImpl implements UpdateProductByIdGateway {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final PartialProductValidator partialProductValidator;

    public UpdateProductByIdGatewayImpl(ProductRepository productRepository, FindProductByIdUseCase findProductByIdUseCase, FindCategoryByIdUseCase findCategoryByIdUseCase, ProductMapper productMapper, PartialProductValidator partialProductValidator) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.partialProductValidator = partialProductValidator;
    }

    @Override
    public Product updateById(UUID id, Product existingProduct, Product productUpdateData) {
        serviceLog.info("Starting updateById ID: {}::UpdateProductByIdGatewayImpl", id);

        ValidationResult validationResult = partialProductValidator.validate(productUpdateData);

        if (!validationResult.isValid()) {
            throw new ValidationException("Product Validation Failed", validationResult);
        }

        ProductEntity updatedProductDataMapped = productMapper.toProductEntityPartial(productUpdateData);
        ProductEntity existingProductMapped = productMapper.toProductEntity(existingProduct);

        ProductEntity mergedProduct = productMapper.merge(existingProductMapped, updatedProductDataMapped);

        productRepository.save(mergedProduct);

        serviceLog.info("Product with ID: {} successfully updated::UpdateProductByIdGatewayImpl", id);
        return productMapper.toProduct(mergedProduct);
    }
}