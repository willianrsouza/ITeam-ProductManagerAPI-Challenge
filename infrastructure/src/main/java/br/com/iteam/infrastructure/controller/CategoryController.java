package br.com.iteam.infrastructure.controller;

import static br.com.iteam.infrastructure.utils.Utilities.controllerLog;
import br.com.iteam.core.domain.entity.Category;
import br.com.iteam.infrastructure.dto.response.SuccessResponse;
import br.com.iteam.usecase.Category.FindAllCategories;
import br.com.iteam.usecase.Category.FindCategoryById;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/categories")
@Tag(name = "Category", description = "Endpoints for categories management")
public class CategoryController {
    private final FindAllCategories findAllCategoriesUseCase;
    private final FindCategoryById findCategoryByIdUseCase;

    public CategoryController(FindAllCategories findAllCategoriesUseCase, FindCategoryById findCategoryByIdUseCase) {
        this.findAllCategoriesUseCase = findAllCategoriesUseCase;
        this.findCategoryByIdUseCase = findCategoryByIdUseCase;
    }

    @GetMapping
    @Operation(summary = "Retrieve all categories", description = "Returns a list of all categories available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No categories found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public SuccessResponse<List<Category>> getAllCategories() {
        controllerLog.info("Start findAllCategoriesUseCase::CategoryController");
        List<Category> categories = findAllCategoriesUseCase.findAll();
        controllerLog.info("Done findAllCategoriesUseCase::CategoryController");

        return SuccessResponse.of(HttpStatus.OK.value(), categories);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve category by ID", description = "Returns a specific category based on the provided ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public SuccessResponse<Category> findCategoryById(@PathVariable("id") UUID id) {
        controllerLog.info("Start findCategoryByIdUseCase::CategoryController");
        Category category = findCategoryByIdUseCase.findById(id);
        controllerLog.info("Start findCategoryByIdUseCase::CategoryController");

        return SuccessResponse.of(HttpStatus.NOT_FOUND.value(), category);
    }
}
