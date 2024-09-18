package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.OrderServicePort;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.OrderCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.OrderResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.OrderRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServicePort servicePort;
    private final OrderRestMapper orderRestMapper;

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> save(@RequestBody OrderCreateRequest orderCreateRequest){
        OrderResponse orderResponse = orderRestMapper.orderModelToOrderResponse(servicePort.save(orderRestMapper.orderCreateRequestToOrderModel(orderCreateRequest)));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

}
