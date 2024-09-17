package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.MediaServicePort;
import com.riwi.artemisa.domain.models.MediaModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.MediaCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MediaResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MediaResponseAdmin;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.MediaRestMapper;
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
    public MediaResponseAdmin save(@Valid @RequestBody MediaCreateRequest request){
        MediaModel savedMedia = servicePort.save(restMapper.toMedia(request));
        return restMapper.toMediaResponseAdmin(savedMedia);
    }

    @PutMapping("admin/update/{id}")
    public MediaResponseAdmin update(
            @Valid @RequestBody MediaCreateRequest request,@PathVariable Long id){
        MediaModel updatedMedia = servicePort.update(id, restMapper.toMedia(request));
        return restMapper.toMediaResponseAdmin(updatedMedia);
    }


    @GetMapping("admin/read/{id}")
    public MediaResponseAdmin readById(@PathVariable Long id){
        MediaModel mediaModel = servicePort.findById(id);
        return restMapper.toMediaResponseAdmin(mediaModel);
    }


    @GetMapping("admin/readAll")
    public List<MediaResponseAdmin> findAll(){
        return restMapper.toMediaResponseAdminList(servicePort.findAll());
    }


    @DeleteMapping("admin/delete/{id}")
    public void delete(@PathVariable Long id){
        servicePort.deletebyId(id);
    }

    //User controllers ---------------------------

    @GetMapping("user/read/{id}")
    public MediaResponse readByIdUser(@PathVariable Long id){
        MediaModel mediaModel = servicePort.findById(id);
        return restMapper.toMediaResponse(mediaModel);
    }

    @GetMapping("user/readAll")
    public List<MediaResponse> findAllUser(){
        return restMapper.toMediaResponseList(servicePort.findAll());
    }

}

