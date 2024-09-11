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

    @GetMapping("/v1/api")
    public List<CategoryResponse> findAll(){
        return restMapper.toCategoryResponseList(servicePort.findAll());
    }

    @GetMapping("/v1/api/{name}")
    public CategoryResponse findByName(@PathVariable String name){
        return restMapper.toCategoryResponse(servicePort.findByName(name));
    }

    @PostMapping("/v1/api")
    public ResponseEntity<CategoryResponse> save(@Valid @RequestBody CategoryCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(restMapper.toCategoryResponse(
                        servicePort.save(restMapper.toCategory(request))));
    }


    @PutMapping("/v1/api/{name}")
    public CategoryResponse update(@PathVariable String name, @Valid @RequestBody CategoryCreateRequest request){
        return restMapper.toCategoryResponse(
                servicePort.update(name,restMapper.toCategory(request)));
    }

    @DeleteMapping("/v1/api/{name}")
    public void delete(@PathVariable String name){
        servicePort.deleteByName(name);
    }


}

