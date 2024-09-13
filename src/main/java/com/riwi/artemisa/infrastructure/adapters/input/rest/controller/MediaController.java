package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.MediaServicePort;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.MediaCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MediaResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.MediaRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/media")
public class MediaController {

    private final MediaServicePort servicePort;
    private final MediaRestMapper restMapper;

    @GetMapping("/v1/api/all")
    public List<MediaResponse> findAll(){
        return restMapper.toMediaResponseList(servicePort.findAll());
    }

    @GetMapping("/v1/api/search/{id}")
    public MediaResponse findById(@PathVariable Long id){
        return restMapper.toMediaResponse(servicePort.findById(id));
    }

    @PostMapping("/v1/api/create")
    public ResponseEntity<MediaResponse> save(@Valid @RequestBody MediaCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(restMapper.toMediaResponse(
                        servicePort.save(restMapper.toMediaModel(request))));
    }

    @DeleteMapping("/v1/api/delete/{id}")
    public void delete(@PathVariable Long id){
        servicePort.deletebyId(id);
    }

}

