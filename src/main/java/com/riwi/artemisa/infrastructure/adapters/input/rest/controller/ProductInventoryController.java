package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.ProductInventoryServicePort;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.ProductInventoryCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.ProductInventoryResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.ProductInventoryRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prductInventory")
@RequiredArgsConstructor
public class ProductInventoryController {

    private final ProductInventoryServicePort servicePort;
    private final ProductInventoryRestMapper mapper;

    @PostMapping("/save")
    public ProductInventoryResponse save (@RequestBody ProductInventoryCreateRequest productInventoryCreateRequest){

        return mapper.toProductInventoryResponse(servicePort.save(mapper.toProductInventoryModel(productInventoryCreateRequest)));

    }
}
