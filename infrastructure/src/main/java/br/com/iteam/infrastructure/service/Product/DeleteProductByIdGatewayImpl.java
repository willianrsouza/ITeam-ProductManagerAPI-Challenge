package br.com.iteam.infrastructure.service.Product;

import br.com.iteam.application.gateway.Product.DeleteProductByIdGateway;
import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.infrastructure.repository.ProductRepository;
import br.com.iteam.usecase.Product.FindProductById;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import static br.com.iteam.infrastructure.utils.Utilities.serviceLog;

@Service
@Transactional
public class DeleteProductByIdGatewayImpl implements DeleteProductByIdGateway {

    private final FindProductById findProductById;
    private final ProductRepository productRepository;

    public DeleteProductByIdGatewayImpl(FindProductById findProductById, ProductRepository productRepository) {
        this.findProductById = findProductById;
        this.productRepository = productRepository;
    }

    @Override
    public boolean deleteById(UUID id) {
        serviceLog.info("Starting deleteProductById::deleteProductByIdGatewayImpl");

        Product product = findProductById.findById(id);
        productRepository.deleteById(product.getId());

        serviceLog.info("Product delete successfully::deleteProductByIdGatewayImpl");
        return true;
    }
}
