package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.MedicationServicePort;
import com.riwi.artemisa.domain.models.MedicationModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.MedicationCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MedicationResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MedicationResponseAdmin;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.MedicationRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Create a medication",
            description = "create a medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medication created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public MedicationResponseAdmin save(@RequestBody MedicationCreateRequest request){
        MedicationModel saveMedication = servicePort.save(restMapper.toMedication(request));
        return restMapper.toMedicationResponseAdmin(saveMedication);
    }

    @PutMapping("admin/update/{id}")
    @Operation(summary = "Update a medication",
            description = "update a medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medication updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public MedicationResponseAdmin update(
            @RequestBody MedicationCreateRequest request,@PathVariable Long id)
    {
        MedicationModel medicationModel = restMapper.toMedication(request);
        MedicationModel updateMedication = servicePort.update(id, medicationModel);
        return restMapper.toMedicationResponseAdmin(updateMedication);
    }

    @GetMapping("admin/read/{name}")
    @Operation(summary = "Read a medication",
            description = "read a medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public MedicationResponseAdmin readByName(@PathVariable String name){
        MedicationModel medicationModel = servicePort.readByName(name);
        return restMapper.toMedicationResponseAdmin(medicationModel);
    }

    @GetMapping("admin/readAll")
    @Operation(summary = "Read All medication",
            description = "read all medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public List<MedicationResponseAdmin> findAll() {
        List<MedicationModel> medicationList = servicePort.findAll();
        return restMapper.toMediaResponseAdminList(medicationList);
    }

    @DeleteMapping("admin/delete/{name}")
    @Operation(summary = "Delete a medication",
            description = "Delete a medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted medication successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public void deleteById(@PathVariable String name){
        servicePort.deletebyId(name);
    }

    //Controllers user------------------------
    @GetMapping("user/read/{name}")
    @Operation(summary = "Read a medication",
            description = "read a medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public MedicationResponse readByNameUser(@PathVariable String name){
        MedicationModel medicationModel = servicePort.readByName(name);
        return restMapper.toMedicationResponse(medicationModel);
    }

    @GetMapping("user/readAll")
    @Operation(summary = "Read All medication",
            description = "read all medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public List<MedicationResponse> readAllByName(){
        List<MedicationModel> medicationList = servicePort.findAll();
        return restMapper.toMedicationResponseList(medicationList);
    }

}
