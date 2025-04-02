package br.com.iteam.infrastructure.controller;

import br.com.iteam.core.domain.entity.Product;
import br.com.iteam.infrastructure.dto.request.CreateProductRequest;
import br.com.iteam.infrastructure.dto.request.UpdateProductRequest;
import br.com.iteam.infrastructure.dto.response.BaseResponse;
import br.com.iteam.infrastructure.dto.response.ErrorResponse;
import br.com.iteam.infrastructure.dto.response.SuccessResponse;
import br.com.iteam.infrastructure.mapper.ProductMapper;
import br.com.iteam.usecase.Product.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static br.com.iteam.infrastructure.utils.Utilities.controllerLog;

@RestController
@RequestMapping("api/products")
@Tag(name = "Product", description = "Endpoints for product management")
public class ProductController {
    private final CreateProductUseCase createProductUseCase;
    private final FindProductByIdUseCase findProductByIdUseCase;
    private final DeleteProductByIdUseCase deleteProductByIdUseCase;
    private final UpdateProductByIdUseCase updateProductByIdUseCase;
    private final SearchProductsByFiltersUseCase searchProductsByFiltersUseCase;
    private final ProductMapper productMapper;

    public ProductController(CreateProductUseCase createProductUseCase, FindProductByIdUseCase findProductByIdUseCase, DeleteProductByIdUseCase deleteProductByIdUseCase, UpdateProductByIdUseCase updateProductByIdUseCase, SearchProductsByFiltersUseCase searchProductsByFiltersUseCase, ProductMapper productMapper) {
        this.createProductUseCase = createProductUseCase;
        this.findProductByIdUseCase = findProductByIdUseCase;
        this.deleteProductByIdUseCase = deleteProductByIdUseCase;
        this.updateProductByIdUseCase = updateProductByIdUseCase;
        this.searchProductsByFiltersUseCase = searchProductsByFiltersUseCase;
        this.productMapper = productMapper;
    }

    @PostMapping("/createProduct")
    @Operation(summary = "Create a new product", description = "Receives a product creation payload and registers it in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public SuccessResponse<Product> createProduct(@Valid @RequestBody CreateProductRequest request) {
        controllerLog.info("Start createProductUseCase::ProductController");

        Product productMapped = productMapper.toProduct(request);
        Product response = createProductUseCase.create(productMapped);

        controllerLog.info("Done createProductUseCase::ProductController");
        return SuccessResponse.of(HttpStatus.CREATED.value(), response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find product by ID", description = "Retrieves a product based on the provided ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product found successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public SuccessResponse<Product> findProductById(@PathVariable UUID id) {
        controllerLog.info("Start findProductByIdUseCase::ProductController");

        Product product = findProductByIdUseCase.findById(id);

        controllerLog.info("Done findProductByIdUseCase::ProductController");
        return SuccessResponse.of(HttpStatus.OK.value(), product);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product by ID", description = "Deletes a product based on the provided ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Product successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public SuccessResponse<Boolean> deleteProductById(@PathVariable UUID id) {
        controllerLog.info("Start deleteProductByIdUseCase::ProductController");

        boolean result = deleteProductByIdUseCase.deleteProductById(id);

        controllerLog.info("Done deleteProductByIdUseCase::ProductController");
        return SuccessResponse.of(HttpStatus.OK.value(), result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update product by ID", description = "Updates an existing product based on the provided ID and request payload.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    })
    public SuccessResponse<Product> updateProductById(@PathVariable UUID id, @Valid @RequestBody UpdateProductRequest request) {
        controllerLog.info("Start updateProductByIdUseCase::ProductController");

        Product productToUpdate = productMapper.toProduct(request);
        Product updatedProduct = updateProductByIdUseCase.updateById(id, productToUpdate);

        controllerLog.info("Done updateProductByIdUseCase::ProductController");
        return SuccessResponse.of(HttpStatus.OK.value(), updatedProduct);
    }

    @GetMapping("/search")
    @Operation(
            summary = "Search products with filters",
            description = "Fetches a paginated list of products based on optional filters such as category, price range, and sorting options."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Products retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public SuccessResponse<List<Product>> searchProductsByFilters(
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int pageSize) {

        controllerLog.info("Start searchProductsByFilters::ProductController");

        List<Product> products = searchProductsByFiltersUseCase.search(
                categoryId, minPrice, maxPrice, sortBy, order, page, pageSize);

        controllerLog.info("Done searchProductsByFilters::ProductController");
        return SuccessResponse.of(HttpStatus.OK.value(), products);
    }
}
