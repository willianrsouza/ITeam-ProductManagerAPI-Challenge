package br.com.iteam.infrastructure.controller;

import static br.com.iteam.infrastructure.utils.Utilities.controllerLog;
import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.infrastructure.dto.response.SuccessResponse;
import br.com.iteam.usecase.Category.FindAllCategories;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
@Tag(name = "Category", description = "Endpoints for categories management")
public class CategoryController {
    private final FindAllCategories findAllCategoriesUseCase;

    public CategoryController(FindAllCategories findAllCategoriesUseCase) {
        this.findAllCategoriesUseCase = findAllCategoriesUseCase;
    }

    @GetMapping
    @Operation(summary = "Retrieve all categories", description = "Returns a list of all categories available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Not Found Categories"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public SuccessResponse<List<Category>> getAllCategories() {
        controllerLog.info("Fetching all categories");

        List<Category> categories = findAllCategoriesUseCase.findAll();

        return SuccessResponse.of(HttpStatus.OK.value(), categories);
    }
}