package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.MedicationServicePort;
import com.riwi.artemisa.domain.models.MedicationModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.MedicationCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MedicationResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MedicationResponseAdmin;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.MedicationRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("medication")
public class MedicationController {

    private final MedicationServicePort servicePort;
    private final MedicationRestMapper restMapper;

    //Controllers admin --------------------
    @PostMapping("admin/create")
    public MedicationResponseAdmin save(@RequestBody MedicationCreateRequest request){
        MedicationModel saveMedication = servicePort.save(restMapper.toMedication(request));
        return restMapper.toMedicationResponseAdmin(saveMedication);
    }

    @PutMapping("admin/update/{id}")
    public MedicationResponseAdmin update(
            @RequestBody MedicationCreateRequest request,@PathVariable Long id)
    {
        MedicationModel medicationModel = restMapper.toMedication(request);
        MedicationModel updateMedication = servicePort.update(id, medicationModel);
        return restMapper.toMedicationResponseAdmin(updateMedication);
    }

    @GetMapping("admin/read/{name}")
    public MedicationResponseAdmin readByName(@PathVariable String name){
        MedicationModel medicationModel = servicePort.readByName(name);
        return restMapper.toMedicationResponseAdmin(medicationModel);
    }

    @GetMapping("admin/readAll")
    public List<MedicationResponseAdmin> findAll() {
        List<MedicationModel> medicationList = servicePort.findAll();
        return restMapper.toMediaResponseAdminList(medicationList);
    }

    @DeleteMapping("admin/delete/{name}")
    public void deleteById(@PathVariable String name){
        servicePort.deletebyId(name);
    }

    //Controllers user------------------------
    @GetMapping("user/read/{name}")
    public MedicationResponse readByNameUser(@PathVariable String name){
        MedicationModel medicationModel = servicePort.readByName(name);
        return restMapper.toMedicationResponse(medicationModel);
    }

    @GetMapping("user/readAll")
    public List<MedicationResponse> readAllByName(){
        List<MedicationModel> medicationList = servicePort.findAll();
        return restMapper.toMedicationResponseList(medicationList);
    }

}
