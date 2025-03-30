package br.com.iteam.infrastructure.controller;

import br.com.iteam.infrastructure.dto.request.CreateProductRequest;
import br.com.iteam.infrastructure.mapper.ProductMapper;
import br.com.iteam.usecase.Product.CreateProduct;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.iteam.infrastructure.dto.response.BaseResponse;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private CreateProduct createProductUseCase;
    private ProductMapper productMapper;

    public ProductController(CreateProduct createProductUseCase) {
        this.createProductUseCase = createProductUseCase;
    }

    @PostMapping("/createProduct")
    public BaseResponse<String> createProduct(@RequestBody CreateProductRequest request){
        createProductUseCase.create(productMapper.toProduct(request));
        return BaseResponse.<String>builder().success(true).message("Success").build();
    }
}
