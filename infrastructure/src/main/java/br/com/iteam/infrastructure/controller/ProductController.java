package br.com.iteam.infrastructure.controller;

import br.com.iteam.infrastructure.dto.request.CreateProductRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.iteam.infrastructure.dto.response.BaseResponse;

@RestController
@RequestMapping("api/products")
public class ProductController {

    public BaseResponse createProduct(@RequestBody CreateProductRequest request){
        return null;
    }
}
