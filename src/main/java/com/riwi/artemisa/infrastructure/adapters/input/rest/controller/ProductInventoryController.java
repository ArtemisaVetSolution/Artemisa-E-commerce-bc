package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.ProductInventoryServicePort;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.ProductInventoryCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.ProductInventoryResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.ProductInventoryRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productInventory")
@RequiredArgsConstructor
public class ProductInventoryController {

    private final ProductInventoryServicePort servicePort;
    private final ProductInventoryRestMapper mapper;

    //Admin

    @PostMapping("/admin/save")
    public ProductInventoryCreateRequest save (@RequestBody ProductInventoryCreateRequest productInventoryCreateRequest){
        return mapper.toProductInventoryCreateRequest(servicePort.save(mapper.toProductInventoryModel(productInventoryCreateRequest)));
    }

    @PutMapping("/admin/update/{id}")
    public ProductInventoryCreateRequest update(@RequestBody ProductInventoryCreateRequest productInventoryUpdateRequest,@PathVariable Long id){
        return mapper.toProductInventoryCreateRequest(servicePort.update(id, mapper.toProductInventoryModel(productInventoryUpdateRequest)));
    }

    @PutMapping("/admin/updateStatusProduct/{id}")
    public String updateStatusProduct(@PathVariable Long id){
        return servicePort.updateStatusProduct(id);
    }

    @GetMapping("/admin/readAll")
    public List<ProductInventoryCreateRequest> readAll(){
        return mapper.toProductInventoryCreateRequestList(servicePort.findAll());
    }

    @GetMapping("/admin/readById/{id}")
    public ProductInventoryCreateRequest readById(@PathVariable Long id){
        return mapper.toProductInventoryCreateRequest(servicePort.readById(id));
    }

    @GetMapping("/admin/readAllCategory/{id}")
    public List<ProductInventoryCreateRequest> readAllByCategory(@PathVariable Long id){
        return mapper.toProductInventoryCreateRequestList(servicePort.readAllCategory(id));
    }

    @GetMapping("/admin/readAllProductName")
    public List<ProductInventoryCreateRequest> readAllByName(@RequestParam String name){
        return mapper.toProductInventoryCreateRequestList(servicePort.findAllByName(name));
    }

    @GetMapping("/admin/updateStock")
    public String updateStock(@RequestParam int stock, @RequestParam Long id){
        return servicePort.updateStock(stock, id);
    }

    @GetMapping("/admin/readAllProductsByStock")
    public List<ProductInventoryCreateRequest> readAllByStock(@RequestParam int stock){
        return mapper.toProductInventoryCreateRequestList(servicePort.readAllProductStock(stock));
    }

    //Users

    @GetMapping("/user/readById/{id}")
    public ProductInventoryResponse readByIdByuser(@PathVariable Long id){
        return mapper.toProductInventoryResponse(servicePort.readById(id));
    }

    @GetMapping("/user/readAllProductName")
    public List<ProductInventoryResponse> readAllByNameByUser(@RequestParam String name){
        return mapper.toProductInventoryResponseList(servicePort.findAllByName(name));
    }

    @GetMapping("/user/readAllCategory/{id}")
    public List<ProductInventoryResponse> readAllByCategoryByUser(@PathVariable Long id){
        return mapper.toProductInventoryResponseList(servicePort.readAllCategory(id));
    }

    @GetMapping("/user/readAllProductAviable")
    public List<ProductInventoryResponse> readAllAviable(){
        return mapper.toProductInventoryResponseList(servicePort.readAllIfAvailable());
    }

}
