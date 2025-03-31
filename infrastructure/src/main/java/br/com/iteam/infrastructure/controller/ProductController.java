package br.com.iteam.infrastructure.controller;

import br.com.iteam.infrastructure.dto.request.CreateProductRequest;
import br.com.iteam.infrastructure.dto.response.BaseResponse;
import br.com.iteam.infrastructure.mapper.ProductMapper;
import br.com.iteam.usecase.Product.CreateProduct;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import static br.com.iteam.infrastructure.utils.Utilities.controllerLog;

@RestController
@RequestMapping("api/products")
@Tag(name = "Product", description = "Endpoints para gerenciamento de produtos")
public class ProductController {
    private final CreateProduct createProductUseCase;
    private final ProductMapper productMapper;

    public ProductController(CreateProduct createProductUseCase, ProductMapper productMapper) {
        this.createProductUseCase = createProductUseCase;
        this.productMapper = productMapper;
    }

    @PostMapping("/createProduct")
    @Operation(summary = "Criar um novo produto", description = "Recebe um payload de criação de produto e o registra no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto criado com sucesso",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public BaseResponse<String> createProduct(@RequestBody CreateProductRequest request){
        controllerLog.info("Start createProductUseCase::ProductController");
        createProductUseCase.create(productMapper.toProduct(request));
        controllerLog.info("Done createProductUseCase::ProductController");

        return BaseResponse.<String>builder().success(true).message("Success").build();
    }
}
