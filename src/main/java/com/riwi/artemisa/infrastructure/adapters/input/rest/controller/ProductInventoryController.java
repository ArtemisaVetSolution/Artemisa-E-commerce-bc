package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.ProductInventoryServicePort;
import com.riwi.artemisa.domain.models.ProductInventoryModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.ProductInventoryCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.ProductInventoryResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.ProductResponseAdmin;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.ProductInventoryRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Create a inventory product",
            description = "create a inventory medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "create product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ProductResponseAdmin save (@RequestBody ProductInventoryCreateRequest productInventoryCreateRequest){
        ProductInventoryModel model =  servicePort.save(mapper.toProductInventory(productInventoryCreateRequest));
        return mapper.toProductInventoryResponseAmin(model);
    }

    @PutMapping("admin/update/{id}")
    @Operation(summary = "update a inventory product",
            description = "update a inventory medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ProductResponseAdmin update(@RequestBody ProductInventoryCreateRequest productInventoryUpdateRequest,@PathVariable Long id){
        ProductInventoryModel inventoryModel = servicePort.update(id,mapper.toProductInventory(productInventoryUpdateRequest));
        return mapper.toProductInventoryResponseAmin(inventoryModel);
    }


    @DeleteMapping("admin/delete/{id}")
    @Operation(summary = "delete a inventory product",
            description = "delete a inventory medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public String updateStatusProduct(@PathVariable Long id){
        return servicePort.updateStatusProduct(id);
    }

    @GetMapping("admin/readAll")
    @Operation(summary = "read all inventory products",
            description = "read all inventory medications in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read all product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public List<ProductResponseAdmin> readAll(){
        return mapper.toProductInventoryResponseAdminList(servicePort.findAll());
    }

    @GetMapping("admin/read/{id}")
    @Operation(summary = "read a inventory product by id",
            description = "read a inventory medication by id in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ProductResponseAdmin readById(@PathVariable Long id){
        return mapper.toProductInventoryResponseAmin(servicePort.readById(id));
    }

    @GetMapping("admin/readAllCategory/{id}")
    @Operation(summary = "read all inventory products by category",
            description = "read all inventory medications by category in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read all product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public List<ProductResponseAdmin> readAllByCategory(@PathVariable Long id){
        return mapper.toProductInventoryResponseAdminList(servicePort.readAllCategory(id));
    }

    @GetMapping("admin/readAllProductName")
    @Operation(summary = "Read all inventory product by name",
            description = "read all inventory product by name in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read product inventory by name successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public List<ProductResponseAdmin> readAllByName(@RequestParam String name){
        return mapper.toProductInventoryResponseAdminList(servicePort.findAllByName(name));
    }

    @GetMapping("admin/updateStock")
    @Operation(summary = "update stock of a inventory product",
            description = "update stock of a inventory medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update stock product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User access required")
    })
    public String updateStock(@RequestParam int stock, @RequestParam Long id){
        return servicePort.updateStock(stock, id);
    }

    @GetMapping("admin/readAllProductsByStock")
    @Operation(summary = "read all inventory products by stock",
            description = "read all inventory medications by stock in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read product inventory by stock successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User access required")
    })
    public List<ProductResponseAdmin> readAllByStock(@RequestParam int stock){
        return mapper.toProductInventoryResponseAdminList(servicePort.readAllProductStock(stock));
    }

    //Users

    @GetMapping("user/readById/{id}")
    @Operation(summary = "read a inventory product by id",
            description = "read a inventory medication by id in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User access required")
    })
    public ProductInventoryResponse readByIdByuser(@PathVariable Long id){
        return mapper.toProductInventoryResponse(servicePort.readById(id));
    }

    @GetMapping("user/readAllProductName")
    @Operation(summary = "read all inventory product by name",
            description = "read all inventory medication by name in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User access required")
    })
    public List<ProductInventoryResponse> readAllByNameByUser(@RequestParam String name){
        return mapper.toProductResponseList(servicePort.findAllByName(name));
    }

    @GetMapping("user/readAllCategory/{id}")
    @Operation(summary = "read all inventory products by category",
            description = "read all inventory medications by category in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User access required")
    })
    public List<ProductInventoryResponse> readAllByCategoryByUser(@PathVariable Long id){
        return mapper.toProductResponseList(servicePort.readAllCategory(id));
    }

    @GetMapping("user/readAllProductAvailable")
    @Operation(summary = "read all inventory products available",
            description = "read all inventory medications available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User access required")
    })
    public List<ProductInventoryResponse> readAllAviable(){
        return mapper.toProductResponseList(servicePort.readAllIfAvailable());
    }

}
