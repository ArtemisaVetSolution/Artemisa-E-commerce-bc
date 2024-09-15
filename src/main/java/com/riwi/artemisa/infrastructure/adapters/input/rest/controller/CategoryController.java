package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.CategoryServicePort;
import com.riwi.artemisa.domain.models.CategoryModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.CategoryCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.CategoryResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.CategoryResponseAdmin;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.CategoryRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryServicePort servicePort;
    private final CategoryRestMapper restMapper;

    //Controllers admin------------------------

    @PostMapping("v1/api/admin/create")
    public CategoryResponseAdmin save(@RequestBody CategoryCreateRequest request){
        CategoryModel savedCategory = servicePort.save(restMapper.toCategory(request));
        return restMapper.toCategoryResponseAdmin(savedCategory);
    }

    @PutMapping("v1/api/admin/update/{name}")
    public CategoryResponseAdmin update(
            @RequestBody CategoryCreateRequest request,@PathVariable String name){
        //mapeamos el request a un modelo
        CategoryModel categoryModel = restMapper.toCategory(request);
        //llamo al servicio
        CategoryModel updateCategory = servicePort.update(name,categoryModel);
        //mapeo el nuevo servicio a un responseAdmin
        return restMapper.toCategoryResponseAdmin(updateCategory);
    }

    @GetMapping("v1/api/admin/read/{name}")
    public CategoryResponseAdmin readByName(@PathVariable String name){
        CategoryModel categoryModel = servicePort.readByName(name);
        return restMapper.toCategoryResponseAdmin(categoryModel);
    }

    @GetMapping("v1/api/admin/read/all")
    public List<CategoryResponseAdmin> findAll() {
        List<CategoryModel> categoryList = servicePort.findAll();
        return restMapper.toCategoryResponseAdminList(categoryList);
    }

    @DeleteMapping("v1/api/admin/delete/{name}")
    public void deleteByName(@PathVariable String name){
        servicePort.updateStatusProduct(name);
    }

    //Controllers user------------------------

    @GetMapping("v1/api/read/{name}")
    public CategoryResponse readByNameUser(@PathVariable String name){
        CategoryModel categoryModel = servicePort.readByName(name);
        return restMapper.toCategoryResponse(categoryModel);
    }

    @GetMapping("v1/api/read/all")
    public List<CategoryResponse> readAllByName(){
        List<CategoryModel> categoryList = servicePort.findAll();
        return restMapper.toCategoryResponseList(categoryList);
    }

}

