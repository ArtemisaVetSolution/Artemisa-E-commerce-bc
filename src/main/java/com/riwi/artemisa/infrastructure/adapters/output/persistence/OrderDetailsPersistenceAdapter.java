package com.riwi.artemisa.infrastructure.adapters.output.persistence;

import com.riwi.artemisa.application.ports.out.OrderDetailsPersistencePort;
import com.riwi.artemisa.domain.models.OrderModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.OrderDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDetailsPersistenceAdapter implements OrderDetailsPersistencePort {

    private final OrderDetailsRepository orderDetailsRepository;

    @Override
    public String deletebyId(Long id) {
        orderDetailsRepository.deleteById(id);
        return "Tha order details has been deleted";
    }
}
