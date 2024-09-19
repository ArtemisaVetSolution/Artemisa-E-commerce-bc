package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.MedicationInventoryServicePort;
import com.riwi.artemisa.domain.models.MedicationInventoryModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.MedicationInventoryCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MedicationInventoryResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MedicationInventoryResponseAdmin;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.MedicationInventoryRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("medicationInventory")
@RequiredArgsConstructor
public class MedicationIventoryController {

    private final MedicationInventoryServicePort servicePort;
    private final MedicationInventoryRestMapper restMapper;

    //Admin controllers ----------------------

    @PostMapping("admin/create")
    @Operation(summary = "Create a inventory medication",
            description = "create a inventory medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "create medication inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public MedicationInventoryResponseAdmin save(@RequestBody MedicationInventoryCreateRequest request){
        MedicationInventoryModel inventoryModel = servicePort.save(restMapper.toMedicationInventroy(request));
        return restMapper.toMedicationInventoryAdmin(inventoryModel);
    }

    @PutMapping("admin/update/{id}")
    @Operation(summary = "Update a inventory medication",
            description = "Update a inventory medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update medication inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User access required")
    })
    public MedicationInventoryResponseAdmin update(
            @RequestBody MedicationInventoryCreateRequest request,@PathVariable Long id){
        MedicationInventoryModel inventoryModel = servicePort.update(id, restMapper.toMedicationInventroy(request));
        return restMapper.toMedicationInventoryAdmin(inventoryModel);
    }

    @DeleteMapping("admin/delete/{id}")
    @Operation(summary = "Delete a inventory medication",
            description = "Delete a inventory medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete medication inventory successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public String delete(@PathVariable Long id){
        return servicePort.updateStatusProduct(id);
    }

    @GetMapping("admin/readAll")
    @Operation(summary = "Read all inventory medications",
            description = "Read all inventory medications in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read all medication inventories successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required"),
            @ApiResponse(responseCode = "403", description = "Forbidden - User access required")
    })
    public List<MedicationInventoryResponseAdmin> getAllAdmin(){
        return restMapper.toMedicationInventoryResponseAdminList(servicePort.findAll());
    }

    @GetMapping("admin/read/{id}")
    @Operation(summary = "Read a inventory medication",
            description = "Read a inventory medication by id in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read medication inventory successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User access required"),
            @ApiResponse(responseCode = "403", description = "Forbidden - User access required")
    })
    public MedicationInventoryResponseAdmin getMedicationInventory(@PathVariable Long id){
        return restMapper.toMedicationInventoryAdmin(servicePort.readById(id));
    }

    @GetMapping("admin/readAllCategory")
    @Operation(summary = "Read all inventory medications by category",
            description = "Read all inventory medications by category in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read all medication inventories successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User access required")
    })
    public List<MedicationInventoryResponseAdmin> getAllByCategory(@RequestParam Long categoryId){
        return restMapper.toMedicationInventoryResponseAdminList(servicePort.readAllCategory(categoryId));
    }

    @GetMapping("admin/readAllProductName")
    @Operation(summary = "Read all product name",
            description = "read all product name in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public List<MedicationInventoryResponseAdmin> getAllByName(@RequestParam String name){
        return restMapper.toMedicationInventoryResponseAdminList(servicePort.findAllByName(name));
    }

    @GetMapping("admin/readAllProductStock")
    @Operation(summary = "Read all Stock",
            description = "read all stock in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public List<MedicationInventoryResponseAdmin> getAllByStock(@RequestParam int stock){
        return restMapper.toMedicationInventoryResponseAdminList(servicePort.readAllProductStock(stock));
    }

    //User Controllers -----------------------

    @GetMapping("user/readAll")
    @Operation(summary = "Read all inventory medication",
            description = "read all inventory medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public List<MedicationInventoryResponse> getAll(){
           return restMapper.toMedicationResponseList(servicePort.readAllIfAvailable());
    }

    @GetMapping("user/readAvailable")
    @Operation(summary = "Read all available inventory medication",
            description = "read all available inventory medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public List<MedicationInventoryResponse> getAllAvailable(){
        return restMapper.toMedicationResponseList(servicePort.readAllIfAvailable());
    }
}
