package br.com.iteam.infrastructure.config.injections;

import br.com.iteam.application.gateway.Category.FindAllCategoriesGateway;
import br.com.iteam.application.gateway.Category.FindCategoryByIdGateway;
import br.com.iteam.application.usecase.Category.FindAllCategoriesImpl;
import br.com.iteam.application.usecase.Category.FindCategoryByIdImpl;
import br.com.iteam.usecase.Category.FindAllCategories;
import br.com.iteam.usecase.Category.FindCategoryById;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryConfig {

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
    public FindAllCategories findAllCategoriesUseCase(FindAllCategoriesGateway findAllCategoriesGateway) {
        return new FindAllCategoriesImpl(findAllCategoriesGateway);
    }

    @Bean
    public FindCategoryById findCategoryById(FindCategoryByIdGateway findCategoryByIdGateway) {
        return new FindCategoryByIdImpl(findCategoryByIdGateway);
    }
}
