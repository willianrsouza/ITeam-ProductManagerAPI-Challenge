package br.com.iteam.infrastructure.config.injections;

import br.com.iteam.application.gateway.Product.*;
import br.com.iteam.application.usecase.Product.*;
import br.com.iteam.usecase.Category.FindCategoryByIdUseCase;
import br.com.iteam.usecase.Product.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductInjection {

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
    public CreateProductUseCase createProductUseCase(CreateProductGateway createProductGateway, FindCategoryByIdUseCase findCategoryByIdUseCase) {
        return new CreateProductUseCaseImpl(createProductGateway, findCategoryByIdUseCase);
    }

    @Bean
    public FindProductByIdUseCase findProductUseCase(FindProductByIdGateway findProductByIdGateway) {
        return new FindProductByIdUseCaseImpl(findProductByIdGateway);
    }

    @Bean
    public DeleteProductByIdUseCase deleteProductByIdUseCase(DeleteProductByIdGateway deleteProductByIdGateway, FindProductByIdUseCase findProductByIdUseCase) {
        return new DeleteProductByIdUseCaseImpl(deleteProductByIdGateway, findProductByIdUseCase);
    }

    @Bean
    public UpdateProductByIdUseCase updateProductByIdUseCase(UpdateProductByIdGateway updateProductByIdGateway, FindProductByIdUseCase findProductByIdUseCase, FindCategoryByIdUseCase findCategoryByIdUseCase) {
        return new UpdateProductByIdUseCaseImpl(updateProductByIdGateway, findProductByIdUseCase, findCategoryByIdUseCase);
    }

    @Bean
    public SearchProductsByFiltersUseCase searchProductsByFiltersUseCase(SearchProductsByFiltersGateway searchProductsByFiltersGateway, FindCategoryByIdUseCase findCategoryByIdUseCase) {
        return new SearchProductsByFiltersUseCaseImpl(searchProductsByFiltersGateway, findCategoryByIdUseCase);
    }
}
