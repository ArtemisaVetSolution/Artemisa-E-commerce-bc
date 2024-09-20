package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.PetshopPaymentsServicePort;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.PetshopPaymentsRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.PetshopPaymentsResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.PetshopPaymentsRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/petshopPayments")
@RequiredArgsConstructor
public class PetshopPaymentsController {

    private final PetshopPaymentsServicePort servicePort;
    private final PetshopPaymentsRestMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<PetshopPaymentsResponse> createPetshopPayments(@RequestBody PetshopPaymentsRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toPetshopPaymentsResponse(servicePort.save(mapper.toPetshopPaymentsModel(request))));
    }

    @GetMapping("/admin/readAll")
    public ResponseEntity<List<PetshopPaymentsResponse>> readAll() {
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPetshopPaymentsResponseList(servicePort.findAll()));
    }

    @GetMapping("/admin/readByDate/{date}")
    public ResponseEntity<List<PetshopPaymentsResponse>> readByDate(@PathVariable LocalDate date){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPetshopPaymentsResponseList(servicePort.findByDate(date)));
    }

    @GetMapping("/admin/readByUserId/{idUser}")
    public ResponseEntity<List<PetshopPaymentsResponse>> readByPetshop(@PathVariable Long idUser){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPetshopPaymentsResponseList(servicePort.findPetshopPaymentsByUserId(idUser)));
    }

}
