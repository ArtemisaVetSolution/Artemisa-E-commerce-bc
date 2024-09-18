package com.riwi.artemisa.application.services;

import com.riwi.artemisa.application.ports.input.OrderServicePort;
import com.riwi.artemisa.application.ports.out.OrderPersistencePort;
import com.riwi.artemisa.domain.models.OrderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServicePort {

    private final OrderPersistencePort order;

    @Override
    public OrderModel save(OrderModel orderModel) {
        return order.save(orderModel);
    }

}
