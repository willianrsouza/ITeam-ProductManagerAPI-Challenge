package br.com.iteam.infrastructure.config.injections;

import br.com.iteam.application.gateway.Category.FindAllCategoriesGateway;
import br.com.iteam.application.gateway.Category.FindCategoryByIdGateway;
import br.com.iteam.application.usecase.Category.FindAllCategoriesUseCaseImpl;
import br.com.iteam.application.usecase.Category.FindCategoryByIdUseCaseImpl;
import br.com.iteam.usecase.Category.FindAllCategoriesUseCase;
import br.com.iteam.usecase.Category.FindCategoryByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryInjection {

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
    public FindAllCategoriesUseCase findAllCategoriesUseCase(FindAllCategoriesGateway findAllCategoriesGateway) {
        return new FindAllCategoriesUseCaseImpl(findAllCategoriesGateway);
    }

    @Bean
    public FindCategoryByIdUseCase findCategoryById(FindCategoryByIdGateway findCategoryByIdGateway) {
        return new FindCategoryByIdUseCaseImpl(findCategoryByIdGateway);
    }
}
