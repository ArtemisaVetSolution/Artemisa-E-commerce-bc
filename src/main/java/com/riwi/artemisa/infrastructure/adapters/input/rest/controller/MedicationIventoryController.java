package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.MedicationInventoryServicePort;
import com.riwi.artemisa.domain.models.MedicationInventoryModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.MedicationInventoryCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MedicationInventoryResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MedicationInventoryResponseAdmin;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.MedicationInventoryRestMapper;
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
    public MedicationInventoryResponseAdmin save(@RequestBody MedicationInventoryCreateRequest request){
        MedicationInventoryModel inventoryModel = servicePort.save(restMapper.toMedicationInventroy(request));
        return restMapper.toMedicationInventoryAdmin(inventoryModel);
    }

    @PutMapping("admin/update/{id}")
    public MedicationInventoryResponseAdmin update(
            @RequestBody MedicationInventoryCreateRequest request,@PathVariable Long id){
        MedicationInventoryModel inventoryModel = servicePort.update(id, restMapper.toMedicationInventroy(request));
        return restMapper.toMedicationInventoryAdmin(inventoryModel);
    }

    @DeleteMapping("admin/delete/{id}")
    public String delete(@PathVariable Long id){
        return servicePort.updateStatusProduct(id);
    }

    @GetMapping("admin/readAll")
    public List<MedicationInventoryResponseAdmin> getAllAdmin(){
        return restMapper.toMedicationInventoryResponseAdminList(servicePort.findAll());
    }

    @GetMapping("admin/read/{id}")
    public MedicationInventoryResponseAdmin getMedicationInventory(@PathVariable Long id){
        return restMapper.toMedicationInventoryAdmin(servicePort.readById(id));
    }

    @GetMapping("admin/readAllCategory")
    public List<MedicationInventoryResponseAdmin> getAllByCategory(@RequestParam Long categoryId){
        return restMapper.toMedicationInventoryResponseAdminList(servicePort.readAllCategory(categoryId));
    }

    @GetMapping("admin/readAllProductName")
    public List<MedicationInventoryResponseAdmin> getAllByName(@RequestParam String name){
        return restMapper.toMedicationInventoryResponseAdminList(servicePort.findAllByName(name));
    }

    @GetMapping("admin/readAllProductStock")
    public List<MedicationInventoryResponseAdmin> getAllByStock(@RequestParam int stock){
        return restMapper.toMedicationInventoryResponseAdminList(servicePort.readAllProductStock(stock));
    }

    //User Controllers -----------------------

    @GetMapping("user/readAll")
    public List<MedicationInventoryResponse> getAll(){
           return restMapper.toMedicationResponseList(servicePort.readAllIfAvailable());
    }

    @GetMapping("user/readAvailable")
    public List<MedicationInventoryResponse> getAllAvailable(){
        return restMapper.toMedicationResponseList(servicePort.readAllIfAvailable());
    }
}
