package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.ProductInventoryServicePort;
import com.riwi.artemisa.domain.models.ProductInventoryModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.ProductInventoryCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.ProductInventoryResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.ProductResponseAdmin;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.ProductInventoryRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("productInventory")
@RequiredArgsConstructor
public class ProductInventoryController {

    private final ProductInventoryServicePort servicePort;
    private final ProductInventoryRestMapper mapper;

    //Admin

    @PostMapping("admin/save")
    public ProductResponseAdmin save (@RequestBody ProductInventoryCreateRequest productInventoryCreateRequest){
        ProductInventoryModel model =  servicePort.save(mapper.toProductInventory(productInventoryCreateRequest));
        return mapper.toProductInventoryResponseAmin(model);
    }

    @PutMapping("admin/update/{id}")
    public ProductResponseAdmin update(@RequestBody ProductInventoryCreateRequest productInventoryUpdateRequest,@PathVariable Long id){
        ProductInventoryModel inventoryModel = servicePort.update(id,mapper.toProductInventory(productInventoryUpdateRequest));
        return mapper.toProductInventoryResponseAmin(inventoryModel);
    }


    @DeleteMapping("admin/delete/{id}")
    public String updateStatusProduct(@PathVariable Long id){
        return servicePort.updateStatusProduct(id);
    }

    @GetMapping("admin/readAll")
    public List<ProductResponseAdmin> readAll(){
        return mapper.toProductInventoryResponseAdminList(servicePort.findAll());
    }

    @GetMapping("admin/read/{id}")
    public ProductResponseAdmin readById(@PathVariable Long id){
        return mapper.toProductInventoryResponseAmin(servicePort.readById(id));
    }

    @GetMapping("admin/readAllCategory/{id}")
    public List<ProductResponseAdmin> readAllByCategory(@PathVariable Long id){
        return mapper.toProductInventoryResponseAdminList(servicePort.readAllCategory(id));
    }

    @GetMapping("admin/readAllProductName")
    public List<ProductResponseAdmin> readAllByName(@RequestParam String name){
        return mapper.toProductInventoryResponseAdminList(servicePort.findAllByName(name));
    }

    @GetMapping("admin/updateStock")
    public String updateStock(@RequestParam int stock, @RequestParam Long id){
        return servicePort.updateStock(stock, id);
    }

    @GetMapping("admin/readAllProductsByStock")
    public List<ProductResponseAdmin> readAllByStock(@RequestParam int stock){
        return mapper.toProductInventoryResponseAdminList(servicePort.readAllProductStock(stock));
    }

    //Users

    @GetMapping("user/readById/{id}")
    public ProductInventoryResponse readByIdByuser(@PathVariable Long id){
        return mapper.toProductInventoryResponse(servicePort.readById(id));
    }

    @GetMapping("user/readAllProductName")
    public List<ProductInventoryResponse> readAllByNameByUser(@RequestParam String name){
        return mapper.toProductResponseList(servicePort.findAllByName(name));
    }

    @GetMapping("user/readAllCategory/{id}")
    public List<ProductInventoryResponse> readAllByCategoryByUser(@PathVariable Long id){
        return mapper.toProductResponseList(servicePort.readAllCategory(id));
    }

    @GetMapping("user/readAllProductAvailable")
    public List<ProductInventoryResponse> readAllAviable(){
        return mapper.toProductResponseList(servicePort.readAllIfAvailable());
    }

}
