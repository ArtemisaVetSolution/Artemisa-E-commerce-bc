package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.CategoryServicePort;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.CategoryCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.CategoryResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.CategoryRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryServicePort servicePort;
    private final CategoryRestMapper restMapper;

    @GetMapping("/v1/api/all")
    public List<CategoryResponse> findAll(){
        return restMapper.toCategoryResponseList(servicePort.getAllCategories());
    }

    @GetMapping("/v1/api/search/{id}")
    public CategoryResponse findByName(@PathVariable Long id){
        return restMapper.toCategoryResponse(servicePort.getCategoryById(id));
    }

    @PostMapping("/v1/api/create")
    public ResponseEntity<CategoryResponse> save(@Valid @RequestBody CategoryCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(restMapper.toCategoryResponse(
                        servicePort.createCategory(restMapper.toCategory(request))));
    }


    @PutMapping("/v1/api/update/{id}")
    public CategoryResponse update(@PathVariable Long id, @Valid @RequestBody CategoryCreateRequest request){
        return restMapper.toCategoryResponse(
                servicePort.editCategory(id,restMapper.toCategory(request)));
    }

    @DeleteMapping("/v1/api/delete/{id}")
    public void delete(@PathVariable Long id){
        servicePort.deleteCategory(id);
    }


}

