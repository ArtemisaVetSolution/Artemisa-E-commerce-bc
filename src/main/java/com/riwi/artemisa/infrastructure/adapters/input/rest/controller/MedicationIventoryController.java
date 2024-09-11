package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.MedicationInventoryServicePort;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.MedicationInventoryCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MedicationInventoryResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.MedicationInventoryRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("medicationInventory")
@RequiredArgsConstructor
public class MedicationIventoryController {

    private final MedicationInventoryServicePort servicePort;
    private final MedicationInventoryRestMapper restMapper;

    @PostMapping("/v1/api")
    public MedicationInventoryResponse save(@RequestBody MedicationInventoryCreateRequest request){
        return restMapper.toMedicationInventoryResponse(servicePort
                .save(restMapper.toMedicationInventoryModel(request)));
    }
}
