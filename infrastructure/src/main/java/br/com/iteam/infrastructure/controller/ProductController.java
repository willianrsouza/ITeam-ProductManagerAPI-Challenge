package br.com.iteam.infrastructure.controller;

import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.infrastructure.dto.request.CreateProductRequest;
import br.com.iteam.infrastructure.dto.response.BaseResponse;
import br.com.iteam.infrastructure.dto.response.SuccessResponse;
import br.com.iteam.infrastructure.mapper.ProductMapper;
import br.com.iteam.usecase.Product.CreateProduct;
import br.com.iteam.usecase.Product.FindProductById;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static br.com.iteam.infrastructure.utils.Utilities.controllerLog;

@RestController
@RequestMapping("api/products")
@Tag(name = "Product", description = "Endpoints for product management")
public class ProductController {
    private final CreateProduct createProductUseCase;
    private final FindProductById findProductByIdUseCase;
    private final ProductMapper productMapper;

    public ProductController(CreateProduct createProductUseCase, FindProductById findProductByIdUseCase, ProductMapper productMapper) {
        this.createProductUseCase = createProductUseCase;
        this.findProductByIdUseCase = findProductByIdUseCase;
        this.productMapper = productMapper;
    }

    @PostMapping("/createProduct")
    @Operation(summary = "Create a new product", description = "Receives a product creation payload and registers it in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product successfully created",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public BaseResponse<Product> createProduct(@Valid @RequestBody CreateProductRequest request) {
        controllerLog.info("Start createProductUseCase::ProductController");

        Product productMapped = productMapper.toProduct(request);
        Product response = createProductUseCase.create(productMapped);

        controllerLog.info("Done createProductUseCase::ProductController");
        return SuccessResponse.of(HttpStatus.CREATED.value(), response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find product by ID", description = "Retrieves a product based on the provided ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product found successfully", content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public SuccessResponse<Product> findProductById(@PathVariable UUID id) {
        controllerLog.info("Start findProductByIdUseCase::ProductController");

        Product product = findProductByIdUseCase.findById(id);

        controllerLog.info("Done findProductByIdUseCase::ProductController");

        return SuccessResponse.of(HttpStatus.OK.value(), product);
    }
}
