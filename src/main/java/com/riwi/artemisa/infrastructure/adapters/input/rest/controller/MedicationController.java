package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.MedicationServicePort;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.MedicationCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MedicationResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.MedicationRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medications")
public class MedicationController {

    private final MedicationServicePort servicePort;
    private final MedicationRestMapper restMapper;

    @GetMapping("/v1/api")
    public List<MedicationResponse> finAll(){
        return restMapper.toMedicationResponseList(servicePort.findAll());
    }

    @GetMapping("/v1/api/{name}")
    public MedicationResponse findByName(@PathVariable String name){
        return restMapper.toMedicationResponse(servicePort.findByName(name));
    }

    @PostMapping("/v1/api")
    public ResponseEntity<MedicationResponse> save(@RequestBody MedicationCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(restMapper.toMedicationResponse(
                        servicePort.save(restMapper.toMedication(request))));
    }





}
