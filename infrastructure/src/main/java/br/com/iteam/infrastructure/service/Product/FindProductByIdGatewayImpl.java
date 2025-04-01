package br.com.iteam.infrastructure.service.Product;

import static br.com.iteam.infrastructure.utils.Utilities.serviceLog;
import br.com.iteam.application.gateway.Product.FindProductByIdGateway;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.infrastructure.exception.NotFoundException;
import br.com.iteam.infrastructure.mapper.ProductMapper;
import br.com.iteam.infrastructure.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class FindProductByIdGatewayImpl implements FindProductByIdGateway {
    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public FindProductByIdGatewayImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product findById(UUID id) {
        serviceLog.info("Starting findProductById::FindProductByIdGatewayImpl");

        Optional<Product> product = productRepository.findById(id)
                .map(productMapper::toProduct);

        if (product.isEmpty()) {
            throw new NotFoundException("Product with ID: " + id + " not found.");
        }

        serviceLog.info("Product found successfully::FindProductByIdGatewayImpl");
        return product.get();
    }
}
