package br.com.iteam.infrastructure.service.Product;

import static br.com.iteam.infrastructure.utils.Utilities.serviceLog;
import br.com.fluentvalidator.context.ValidationResult;
import br.com.iteam.application.gateway.Product.UpdateProductByIdGateway;
import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.infrastructure.entity.ProductEntity;
import br.com.iteam.infrastructure.exception.ValidationException;
import br.com.iteam.infrastructure.mapper.ProductMapper;
import br.com.iteam.infrastructure.repository.ProductRepository;
import br.com.iteam.infrastructure.validators.PartialProductValidator;
import br.com.iteam.usecase.Category.FindCategoryById;
import br.com.iteam.usecase.Product.FindProductById;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@Transactional
public class UpdateProductByIdGatewayImpl implements UpdateProductByIdGateway {
    private final ProductRepository productRepository;
    private final FindProductById findProductById;
    private final FindCategoryById findCategoryById;
    private final ProductMapper productMapper;
    private final PartialProductValidator partialProductValidator;

    public UpdateProductByIdGatewayImpl(ProductRepository productRepository, FindProductById findProductById, FindCategoryById findCategoryById, ProductMapper productMapper, PartialProductValidator partialProductValidator) {
        this.productRepository = productRepository;
        this.findProductById = findProductById;
        this.findCategoryById = findCategoryById;
        this.productMapper = productMapper;
        this.partialProductValidator = partialProductValidator;
    }

    @Override
    public Product updateById(UUID id, Product productUpdateData) {
        serviceLog.info("Starting updateById for Product ID: {}", id);
        ProductEntity existingProductEntity = productMapper.toProductEntity(findProductById.findById(id));

        if(productUpdateData.getCategory().getId() != null){
            Category category = findCategoryById.findById(productUpdateData.getCategory().getId());
            productUpdateData.setCategory(category);
        }

        ValidationResult validationResult = partialProductValidator.validate(productUpdateData);

        if (!validationResult.isValid()) {
            throw new ValidationException("Product Validation Failed", validationResult);
        }

        ProductEntity updatedProductDataMapped = productMapper.toProductEntityPartial(productUpdateData);
        ProductEntity mergedProduct = productMapper.merge(existingProductEntity, updatedProductDataMapped);

        productRepository.save(mergedProduct);

        serviceLog.info("Product with ID: {} successfully updated. ::UpdateProductByIdGatewayImpl", id);
        return productMapper.toProduct(mergedProduct);
    }
}