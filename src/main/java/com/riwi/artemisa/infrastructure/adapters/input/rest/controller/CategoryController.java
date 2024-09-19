package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.CategoryServicePort;
import com.riwi.artemisa.domain.models.CategoryModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.CategoryCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.CategoryResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.CategoryResponseAdmin;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.CategoryRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("category")
public class CategoryController {

    private final CategoryServicePort servicePort;
    private final CategoryRestMapper restMapper;

    //Controllers admin------------------------

    @PostMapping("/admin/create")
    @Operation(summary = "Create a new category",
            description = "Creates a new category in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public CategoryResponseAdmin save(@RequestBody CategoryCreateRequest request){
        CategoryModel savedCategory = servicePort.save(restMapper.toCategory(request));
        return restMapper.toCategoryResponseAdmin(savedCategory);
    }

    @PutMapping("admin/update/{name}")
    @Operation(summary = "Update a category",
            description = "Update a category in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category update successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public CategoryResponseAdmin update(
            @RequestBody CategoryCreateRequest request,@PathVariable String name){
        //mapeamos el request a un modelo
        CategoryModel categoryModel = restMapper.toCategory(request);
        //llamo al servicio
        CategoryModel updateCategory = servicePort.update(name,categoryModel);
        //mapeo el nuevo servicio a un responseAdmin
        return restMapper.toCategoryResponseAdmin(updateCategory);
    }

    @GetMapping("admin/read/{name}")
    @Operation(summary = "Read a category",
            description = "read a category in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public CategoryResponseAdmin readByName(@PathVariable String name){
        CategoryModel categoryModel = servicePort.readByName(name);
        return restMapper.toCategoryResponseAdmin(categoryModel);
    }

    @GetMapping("admin/readAll")
    @Operation(summary = "Read All category",
            description = "read All category in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public List<CategoryResponseAdmin> findAll() {
        List<CategoryModel> categoryList = servicePort.findAll();
        return restMapper.toCategoryResponseAdminList(categoryList);
    }

    @DeleteMapping("admin/delete/{name}")
    @Operation(summary = "Delete a category",
            description = "Delete a category in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public void deleteByName(@PathVariable String name){
        servicePort.updateStatusProduct(name);
    }

    //Controllers user------------------------

    @GetMapping("user/read/{name}")
    @Operation(summary = "Read a category",
            description = "read a category in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public CategoryResponse readByNameUser(@PathVariable String name){
        CategoryModel categoryModel = servicePort.readByName(name);
        return restMapper.toCategoryResponse(categoryModel);
    }

    @GetMapping("user/readAll")
    @Operation(summary = "Read All category",
            description = "read All category in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public List<CategoryResponse> readAllByName(){
        List<CategoryModel> categoryList = servicePort.findAll();
        return restMapper.toCategoryResponseList(categoryList);
    }

}

