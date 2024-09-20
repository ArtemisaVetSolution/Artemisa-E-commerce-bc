package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.OrderServicePort;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.OrderCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.OrderResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.OrderRestMapper;
import com.riwi.artemisa.utils.enums.StatesOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServicePort servicePort;
    private final OrderRestMapper orderRestMapper;

    @PostMapping("/user/create")
    public ResponseEntity<OrderResponse> save(@RequestBody OrderCreateRequest orderCreateRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderRestMapper.orderModelToOrderResponse(servicePort.save(orderRestMapper.orderCreateRequestToOrderModel(orderCreateRequest))));
    }

    @GetMapping("/admin/findByUserIdAndDate/{id}/{date}")
    public ResponseEntity<OrderResponse> readAll(@PathVariable Long id, @PathVariable LocalDate date){
        return ResponseEntity.ok(orderRestMapper.orderModelToOrderResponse(servicePort.readByIdUserAndOrderDate(id, date)));
    }

    @GetMapping("/admin/findAll")
    public ResponseEntity<List<OrderResponse>> readAll(){
        return ResponseEntity.ok(orderRestMapper.orderModelToOrderResponseList(servicePort.findAll()));
    }

    @GetMapping("/admin/findbyId/{id}")
    public ResponseEntity<OrderResponse> readById(@PathVariable Long id){
        return ResponseEntity.ok(orderRestMapper.orderModelToOrderResponse(servicePort.readById(id)));
    }

    @PatchMapping("/admin/updateStatusOrder/{id}/{status}")
    public ResponseEntity<String> updateStatusOrder(@PathVariable Long id, @PathVariable StatesOrder status){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicePort.updateStatesOrder(id, status));
    }

    @DeleteMapping("/user" +
            "/delete/{idOrder}/{idOrderDetails}")
    public ResponseEntity<String> delete(@PathVariable Long idOrder, @PathVariable Long idOrderDetails) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicePort.deleteOrderDetails(idOrder, idOrderDetails));
    }

}
