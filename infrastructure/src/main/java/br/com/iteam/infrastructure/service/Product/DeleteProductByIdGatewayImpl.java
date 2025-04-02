package br.com.iteam.infrastructure.service.Product;

import br.com.iteam.application.gateway.Product.DeleteProductByIdGateway;
import br.com.iteam.infrastructure.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import static br.com.iteam.infrastructure.utils.Utilities.serviceLog;

@Service
@Transactional
public class DeleteProductByIdGatewayImpl implements DeleteProductByIdGateway {
    private final ProductRepository productRepository;

    public DeleteProductByIdGatewayImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean deleteById(UUID id) {
        serviceLog.info("Starting deleteProductById::deleteProductByIdGatewayImpl");

        productRepository.deleteById(id);

        serviceLog.info("Product delete successfully::deleteProductByIdGatewayImpl");
        return true;
    }
}
