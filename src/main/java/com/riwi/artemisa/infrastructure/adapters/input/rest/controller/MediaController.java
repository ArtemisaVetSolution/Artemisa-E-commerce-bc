package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.MediaServicePort;
import com.riwi.artemisa.domain.models.MediaModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.MediaCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MediaResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MediaResponseAdmin;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.MediaRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("media")
public class MediaController {

    private final MediaServicePort servicePort;
    private final MediaRestMapper restMapper;


    //Admin controllers ---------------------------

    @PostMapping("admin/create")
    @Operation(summary = "create a media",
            description = "create a media in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "create media successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public MediaResponseAdmin save(@Valid @RequestBody MediaCreateRequest request){
        MediaModel savedMedia = servicePort.save(restMapper.toMedia(request));
        return restMapper.toMediaResponseAdmin(savedMedia);
    }

    @PutMapping("admin/update/{id}")
    @Operation(summary = "update a media",
            description = "update a media in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public MediaResponseAdmin update(
            @Valid @RequestBody MediaCreateRequest request,@PathVariable Long id){
        MediaModel updatedMedia = servicePort.update(id, restMapper.toMedia(request));
        return restMapper.toMediaResponseAdmin(updatedMedia);
    }


    @GetMapping("admin/read/{id}")
    @Operation(summary = "Read a media",
            description = "read a media in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public MediaResponseAdmin readById(@PathVariable Long id){
        MediaModel mediaModel = servicePort.findById(id);
        return restMapper.toMediaResponseAdmin(mediaModel);
    }


    @GetMapping("admin/readAll")
    @Operation(summary = "Read All media",
            description = "read All media in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public List<MediaResponseAdmin> findAll(){
        return restMapper.toMediaResponseAdminList(servicePort.findAll());
    }


    @DeleteMapping("admin/delete/{id}")
    @Operation(summary = "Delete a media",
            description = "delete a media in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "deleted media successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public void delete(@PathVariable Long id){
        servicePort.deletebyId(id);
    }

    //User controllers ---------------------------

    @GetMapping("user/read/{id}")
    @Operation(summary = "Read a media",
            description = "read a media in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public MediaResponse readByIdUser(@PathVariable Long id){
        MediaModel mediaModel = servicePort.findById(id);
        return restMapper.toMediaResponse(mediaModel);
    }

    @GetMapping("user/readAll")
    @Operation(summary = "Read All media",
            description = "read All media in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public List<MediaResponse> findAllUser(){
        return restMapper.toMediaResponseList(servicePort.findAll());
    }

}

