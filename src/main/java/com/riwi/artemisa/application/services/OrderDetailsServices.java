package com.riwi.artemisa.application.services;

import com.riwi.artemisa.application.ports.input.OrderDetailsServicePort;
import com.riwi.artemisa.application.ports.out.OrderDetailsPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailsServices implements OrderDetailsServicePort {

    private final OrderDetailsPersistencePort orderDetails;

    @Override
    public String deletebyId(Long id) {
        return orderDetails.deletebyId(id);
    }
}
