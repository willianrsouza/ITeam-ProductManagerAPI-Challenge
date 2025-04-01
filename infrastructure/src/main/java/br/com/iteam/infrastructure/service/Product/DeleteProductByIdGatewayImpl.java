package br.com.iteam.infrastructure.service.Product;

import br.com.iteam.application.gateway.Product.DeleteProductByIdGateway;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.infrastructure.exception.NotFoundException;
import br.com.iteam.infrastructure.mapper.ProductMapper;
import br.com.iteam.infrastructure.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.UUID;
import static br.com.iteam.infrastructure.utils.Utilities.serviceLog;

@Service
@Transactional
public class DeleteProductByIdGatewayImpl implements DeleteProductByIdGateway {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public DeleteProductByIdGatewayImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public boolean deleteById(UUID id) {
        serviceLog.info("Starting deleteProductById::deleteProductByIdGatewayImpl");

        Optional<Product> product = productRepository.findById(id)
                .map(productMapper::toProduct);

        if (product.isEmpty()) {
            throw new NotFoundException("Product with ID: " + id + " not found.");
        }

        productRepository.deleteById(id);
        serviceLog.info("Product delete successfully::deleteProductByIdGatewayImpl");

        return true;
    }
}
