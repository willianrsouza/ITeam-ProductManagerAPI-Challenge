package br.com.iteam.infrastructure.config.injections;

import br.com.iteam.application.gateway.Product.CreateProductGateway;
import br.com.iteam.application.gateway.Product.DeleteProductByIdGateway;
import br.com.iteam.application.gateway.Product.FindProductByIdGateway;
import br.com.iteam.application.gateway.Product.UpdateProductByIdGateway;
import br.com.iteam.application.usecase.Product.CreateProductImpl;
import br.com.iteam.application.usecase.Product.DeleteProductByIdImpl;
import br.com.iteam.application.usecase.Product.FindProductByIdImpl;
import br.com.iteam.application.usecase.Product.UpdateProductByIdImpl;
import br.com.iteam.usecase.Category.FindCategoryById;
import br.com.iteam.usecase.Product.CreateProduct;
import br.com.iteam.usecase.Product.DeleteProductById;
import br.com.iteam.usecase.Product.FindProductById;
import br.com.iteam.usecase.Product.UpdateProductById;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    /*
    Since no framework was used in the core, use case,
    and application layers, it is necessary to explicitly
    inform Spring Boot about the correlation with the UseCase.
    Notice that Spring would not complain about the Gateway
    because, although the interface is defined in the application
    layer, its implementation is in the infrastructure layer.
    Therefore, Spring is already aware of its existence.
    */
    @Bean
    public CreateProduct createProductUseCase(CreateProductGateway createProductGateway, FindCategoryById findCategoryById) {
        return new CreateProductImpl(createProductGateway, findCategoryById);
    }

    @Bean
    public FindProductById findProductUseCase(FindProductByIdGateway findProductByIdGateway) {
        return new FindProductByIdImpl(findProductByIdGateway);
    }

    @Bean
    public DeleteProductById deleteProductByIdUseCase(DeleteProductByIdGateway deleteProductByIdGateway, FindProductById findProductById) {
        return new DeleteProductByIdImpl(deleteProductByIdGateway, findProductById);
    }

    @Bean
    public UpdateProductById updateProductByIdUseCase(UpdateProductByIdGateway updateProductByIdGateway, FindProductById findProductById, FindCategoryById findCategoryById) {
        return new UpdateProductByIdImpl(updateProductByIdGateway, findProductById, findCategoryById);
    }
}
